package pl.marchwicki.jee.stockquote.getquote.ejb;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.marchwicki.jee.stockapp.ejb.logging.AuditMessageProcessingLocal;
import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;
import pl.marchwicki.jee.stockapp.getquote.ejb.QuotationConverter;
import pl.marchwicki.jee.stockapp.getquote.ejb.QuotationConverterLocal;
import pl.marchwicki.jee.stockapp.getquote.ejb.StockQuoteWrapper;
import pl.marchwicki.jee.stockapp.getquote.ejb.StockQuoteWrapperLocal;

@RunWith(Arquillian.class)
public class StockQuoteWrapperIntegrationTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addClasses(AuditMessageProcessingLocal.class, AuditMessageProcessingStub.class)
				.addClasses(QuotationConverterLocal.class, QuotationConverter.class)
				.addClasses(StockQuoteWrapperLocal.class, StockQuoteWrapper.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	StockQuoteWrapperLocal wrapper;
	
	@Test
	public void externalServiceWrapperTest() {
		Quotation q = wrapper.getQuotation("YHOO");
		assertNotNull(q);
		assertEquals("YHOO", q.getSymbol());
		assertEquals("Yahoo! Inc.", q.getName());
	}

	@Stateless
	public static class AuditMessageProcessingStub implements AuditMessageProcessingLocal {

		public void auditLog(String message) {
			System.out.println("I'm just a mock - I do nothing");
		}

		public void serializeAndAuditLog(Object obj) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
