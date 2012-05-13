package pl.marchwicki.jee.stockapp.ejb.logging.plugins;

public interface PluggableAuditProcessingLocal {
	public void auditLog(String message);
}
