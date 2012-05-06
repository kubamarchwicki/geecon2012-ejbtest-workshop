package pl.marchwicki.jee.stockapp.ejb.logging;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.StatelessBean;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.junit.Module;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ApplicationComposer.class)
public class AuditMessageProcessorTest {

	@Resource(name = "ConnectionFactory", mappedName = "ConnectionFactory")
	ConnectionFactory connectionFactory;

	@Resource(name = "queue/audit", mappedName = "queue/audit")
	Queue queue;	

	@EJB
	AuditMessageProcessingLocal processor;
	
	final String originalTestMessage = "Hello World!";
	
	@Module
	public EjbJar module() {
		final EjbJar ejbJar = new EjbJar();
		ejbJar.addEnterpriseBean(new StatelessBean(AuditMessageProcessing.class));
		
		return ejbJar;
	}
	
	@Before
	public void prepareMessage() {
		processor.auditLog(originalTestMessage);		
	}
	
	@Test
	public void processQueue() throws JMSException {
        final Connection connection = connectionFactory.createConnection();
        connection.start();
        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        final MessageConsumer incoming = session.createConsumer(queue);
        Message receive = incoming.receive();
        
        assertThat(receive, new IsInstanceOf(TextMessage.class));
        assertEquals(originalTestMessage, ((TextMessage) receive).getText());
	}
}
