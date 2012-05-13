package pl.marchwicki.jee.stockapp.ejb.logging.plugins;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.jboss.annotation.ejb.LocalBinding;

@Stateless
@LocalBinding(jndiBinding = "stockapp/Log4jLoggerPlugin")
public class Log4jLoggerPlugin implements LoggerPlugin {

	private static Logger logger = Logger.getLogger(Log4jLoggerPlugin.class);
	
	public void processLogMessage(String str) {
		logger.info("Message: " + str);
	}

}
