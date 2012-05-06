package pl.marchwicki.jee.stockapp.ejb.logging;

import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

@MessageDriven(messageListenerInterface = MessageListener.class, 
		activationConfig = { 
			@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/audit"),
			@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
			@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
		})
public class AuditingMessageListerner implements MessageListener {

	private static final Logger logger = Logger.getLogger(AuditingMessageListerner.class);
	
	@EJB
	AuditLoggerServiceLocal auditLogger;
	
	public void onMessage(Message msg) {
		try {
			TextMessage message = (TextMessage) msg;
			auditLogger.saveAuditMessage(message.getText(), new Date());
		} catch (Exception e) {
			logger.error("Unable to process message. e: ", e);
			throw new RuntimeException(e);
		} finally {
		}
	}

}
