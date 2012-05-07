package pl.marchwicki.jee.stockapp.ejb.logging;

import static org.mockito.Mockito.*;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.Test;

import pl.marchwicki.jee.stockapp.ejb.logging.entity.AuditLog;

public class AuditLoggerServiceTest {

	@Test
	public void createAuditLogElement() {
		//given
		final AuditLoggerService service = new AuditLoggerService();
		service.em = mock(EntityManager.class);
		
		final Date date = new Date();
		final String message = "Hello world!";
		
		//when
		service.saveAuditMessage(message, date);
		
		//then
		AuditLog e = new AuditLog();
		e.setMessage(message);
		e.setDate(date);
		
		verify(service.em).persist(e);
	}
	
}
