package pl.marchwicki.jee.stockapp.ejb.logging;

import java.util.Date;

public interface AuditLoggerServiceLocal {

	public void saveAuditMessage(String str, Date date);
	
}
