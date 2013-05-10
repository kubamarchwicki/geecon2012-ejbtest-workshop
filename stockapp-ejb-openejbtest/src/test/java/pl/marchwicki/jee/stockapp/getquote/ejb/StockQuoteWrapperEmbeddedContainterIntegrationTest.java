package pl.marchwicki.jee.stockapp.getquote.ejb;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

public class StockQuoteWrapperEmbeddedContainterIntegrationTest {

	private static EJBContainer container;
	
	@EJB
	StockQuoteWrapperLocal wrapper;
	
	@Test
	public void externalServiceWrapperTest() {
		Quotation q = wrapper.getQuotation("YHOO");
		assertNotNull(q);
		assertEquals("YHOO", q.getSymbol());
		assertEquals("Yahoo! Inc.", q.getName());
	}
	
	@BeforeClass
	public static void start() {
	    container = EJBContainer.createEJBContainer();
	}

	@Before
	public void inject() throws NamingException {
	    container.getContext().bind("inject", this);
	}

	@AfterClass
	public static void stop() {
	    container.close();
	}	
}
