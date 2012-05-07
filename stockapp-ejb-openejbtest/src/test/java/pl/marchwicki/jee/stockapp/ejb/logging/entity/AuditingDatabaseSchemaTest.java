package pl.marchwicki.jee.stockapp.ejb.logging.entity;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuditingDatabaseSchemaTest {

	@BeforeClass
	public static void setup() {
		BasicConfigurator.configure();
	}
	
	@Test
	public void testSchema() {
		DataSourceMockUp mock = new DataSourceMockUp.Builder()
			.fromSqlFile("/pl/marchwicki/jee/stockapp/ejb/logging/entity/import.sql")
			.withAnnotatedClass(AuditLog.class)
			.build();
		
		
		EntityManager em = mock.getEntityManager();
		em.clear();
		
		assertNull(em.find(AuditLog.class, 0l));
		
		AuditLog log = em.find(AuditLog.class, 1l);
		assertEquals("Hello World!", log.getMessage());
	}

}
