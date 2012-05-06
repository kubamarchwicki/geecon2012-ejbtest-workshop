package pl.marchwicki.jee.stockapp.ejb.logging;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.MessageDrivenBean;
import org.apache.openejb.jee.StatelessBean;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.junit.Configuration;
import org.apache.openejb.junit.Module;
import org.hibernate.ejb.HibernatePersistence;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.marchwicki.jee.stockapp.ejb.logging.entity.AuditLog;

@RunWith(ApplicationComposer.class)
public class AuditMessageReceiverTest {

	final String originalTestMessage = "Hello World!";
	
	@EJB
	AuditMessageProcessingLocal processor;
	
	@PersistenceContext
	EntityManager em;
	
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
		ejbJar.addEnterpriseBean(new StatelessBean(AuditMessageProcessing.class));
		ejbJar.addEnterpriseBean(new StatelessBean(AuditLoggerService.class));
		ejbJar.addEnterpriseBean(new MessageDrivenBean(AuditingMessageListerner.class));
		
		return ejbJar;
	}
	
	@Before
	public void sendMessage() {
		processor.auditLog(originalTestMessage);
	}
	
	@Test
	public void processQueue() throws JMSException {
	    List<AuditLog> list = em.createQuery("from AuditLog", AuditLog.class).getResultList();
	    assertNotNull(list);
	    assertEquals(1, list.size());
	    assertEquals(originalTestMessage, list.get(0).getMessage());
	    System.out.println(list.get(0));
	}
}
