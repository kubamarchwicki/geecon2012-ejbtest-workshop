package pl.marchwicki.jee.stockapp.ejb.logging.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

@SuppressWarnings("deprecation")
public class DataSourceMockUp {

	private EntityManager entityManager;

	public DataSourceMockUp(Builder builder) {

		//bootstrap hibernate
		Ejb3Configuration config = new Ejb3Configuration();
		config.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
			.setProperty("javax.persistence.transactionType", "RESOURCE_LOCAL")
			.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
			.setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:testdb")
			.setProperty("hibernate.connection.username", "sa")
			.setProperty("hibernate.connection.password", "")
			.setProperty("hibernate.connection.pool_size", "1")
			.setProperty("hibernate.connection.autocommit", "true")
			.setProperty("hibernate.hbm2ddl.auto", "create")
			.setProperty("hibernate.hbm2ddl.import_files", builder.importSqlFile);
			
		//add entities
		for (Class<?> clazz : builder.annotatedClass) {
			config.addAnnotatedClass(clazz);	
		}
		
		//set up services
		//even though the method is deprecated - this is one to be used
		//https://forum.hibernate.org/viewtopic.php?f=10&t=966871
		EntityManagerFactory entityManagerFactory = config.createEntityManagerFactory();
		setEntityManager(entityManagerFactory.createEntityManager());
		
		//import initial data
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		SchemaExport schemaExport = new SchemaExport(config.getHibernateConfiguration());
		schemaExport.create(true, true);
		tx.commit();
		
	}

	public static class Builder {
		
		private String importSqlFile = "/import.sql";
		private List<Class<?>> annotatedClass = new ArrayList<Class<?>>();
		
		public Builder fromSqlFile(String importSqlFile) {
			this.importSqlFile = importSqlFile;
			return this;
		}
		
		public Builder withAnnotatedClass(Class<?> clazz) {
			this.annotatedClass.add(clazz);
			return this;
		}
		
		public DataSourceMockUp build() {
			return new DataSourceMockUp(this);
		}
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}	
	
}
