package pl.marchwicki.jee.stockapp.ejb.logging;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.marchwicki.jee.stockapp.ejb.logging.entity.AuditLog;

@Stateless
public class AuditLoggerService implements AuditLoggerServiceLocal {

	@PersistenceContext
	EntityManager em;
	
	public void saveAuditMessage(String str, Date date) {
		AuditLog log = new AuditLog.Builder()
			.withMessage(str)
			.withDate(date)
			.build();
		
		em.persist(log);
	}

}
