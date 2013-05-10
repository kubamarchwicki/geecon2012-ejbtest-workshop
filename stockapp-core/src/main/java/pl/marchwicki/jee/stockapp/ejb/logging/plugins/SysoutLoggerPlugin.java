package pl.marchwicki.jee.stockapp.ejb.logging.plugins;

public class SysoutLoggerPlugin implements LoggerPlugin {

	public void processLogMessage(String str) {
		System.out.println("Logger message: " + str);
	}

}
