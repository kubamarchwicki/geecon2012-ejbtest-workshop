package pl.marchwicki.jee.stockapp.ejb.logging.entity;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Callable;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.StatelessBean;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.junit.Configuration;
import org.apache.openejb.junit.Module;
import org.hibernate.ejb.HibernatePersistence;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("unchecked")
@RunWith(ApplicationComposer.class)
public class AuditingDatabaseIntegrationTest {
	
	@Resource
	DataSource dataSource;
	
	@PersistenceContext
	EntityManager em;

	@EJB
	Caller transactionCaller;
	
	@Test
	public void findById() throws Exception {
		transactionCaller.call(new Callable() {

			public Object call() throws Exception {
				Connection connection = dataSource.getConnection();
				connection.prepareStatement("insert into AuditLog values (1, now, 'Hello World!')")
					.execute();
				return null;
			}
			
		});

		transactionCaller.call(new Callable() {
			
			public Object call() throws Exception {
				assertNull(em.find(AuditLog.class, 0l));
				AuditLog log = em.getReference(AuditLog.class, 1l);
				assertEquals("Hello World!", log.getMessage());		
				return null;
			}
			
		});
	}
	
	@After
	public void cleanup() throws SQLException {
		Connection connection = dataSource.getConnection();
		connection.prepareStatement("delete from auditlog")
			.execute();
	}
	
	
	@Configuration
	public Properties config() throws Exception {
		Properties p = new Properties();
		p.put("DefaultDS", "new://Resource?type=DataSource");
		p.put("DefaultDS.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("DefaultDS.JdbcUrl", "jdbc:hsqldb:mem:testdb");
		return p;
	}	
	
	@Module
	public PersistenceUnit persistence() {
		PersistenceUnit unit = new PersistenceUnit("stockapp-audit");
		unit.setJtaDataSource("DefaultDS");
		unit.setProvider(HibernatePersistence.class);
		unit.addClass(AuditLog.class);
		
		Properties dbProperties = new Properties();
		dbProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		dbProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		dbProperties.setProperty("hibernate.use_sql_comments", "true");
		dbProperties.setProperty("hibernate.show_sql", "true");
		dbProperties.setProperty("hibernate.format_sql", "true");
		
		unit.setProperties(dbProperties);
		return unit;
	}	
	
	@Module
	public EjbJar module() {
		final EjbJar ejbJar = new EjbJar();
		ejbJar.addEnterpriseBean(new StatelessBean(TransactionBean.class));
		return ejbJar;
	}
	
	/**
	 * This little bit of magic allows our test code to execute in
	 * the scope of a container controlled transaction.
	 */
    public static interface Caller {
        public <V> V call(Callable<V> callable) throws Exception;
    }

    @Stateless
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public static class TransactionBean implements Caller {

        public <V> V call(Callable<V> callable) throws Exception {
            return callable.call();
        }
    }	
}
