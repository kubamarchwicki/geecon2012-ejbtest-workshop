package pl.marchwicki.jee.stockapp.ejb.logging;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;

@Stateless
public class AuditMessageProcessing implements AuditMessageProcessingLocal {

	private static Logger logger = Logger.getLogger(AuditMessageProcessing.class);
	
	@Resource(name = "ConnectionFactory", mappedName = "ConnectionFactory")
	ConnectionFactory connectionFactory;

	@Resource(name = "queue/audit", mappedName = "queue/audit")
	Queue queue;	
	
	public void auditLog(String msg) {
		Connection connection = null;
		Session session = null;
		MessageProducer messageProducer = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			connection.start();
			messageProducer = session.createProducer(queue);
			TextMessage message = session.createTextMessage(msg);
			messageProducer.send(message);
		} catch (JMSException e) {
			logger.error("JMS submittion error: ", e);
		} finally {
			try {
				if (messageProducer != null) {
					messageProducer.close();
				}
				if (session != null) {
					session.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (JMSException e) {
				logger.error("Closing JMS connection error: ", e);
			}
		}				
	}

	public void serializeAndAuditLog(Object obj) {
		XStream xstream = new XStream();
		String xml = xstream.toXML(obj);
		auditLog(xml);
	}

}
