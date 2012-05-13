package pl.marchwicki.jee.stockapp.ejb.logging.plugins;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import pl.marchwicki.jee.stockapp.ejb.logging.AuditMessageProcessingLocal;

@Stateless
public class PluggableAuditProcessing implements PluggableAuditProcessingLocal {

	@Inject
	Instance<LoggerPlugin> plugin;
	
	public void auditLog(String message) {
		for (LoggerPlugin p :plugin) {
			p.processLogMessage(message);
		}
		
	}

//	LoggerPlugin plugin;
//	
//	@PostConstruct
//	public void init() {
//		try {
//			InitialContext ctx = new InitialContext();
//			plugin = (LoggerPlugin) ctx.lookup("stockapp/Log4jLoggerPlugin");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public void auditLog(String message) {
//		if (plugin != null) {
//			plugin.processLogMessage(message);			
//		}
//	}

}
