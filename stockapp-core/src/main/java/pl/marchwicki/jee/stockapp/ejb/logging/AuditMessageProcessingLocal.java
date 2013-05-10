package pl.marchwicki.jee.stockapp.ejb.logging;

public interface AuditMessageProcessingLocal {

	public void auditLog(String message);
	public void serializeAndAuditLog(Object obj);
	
}
