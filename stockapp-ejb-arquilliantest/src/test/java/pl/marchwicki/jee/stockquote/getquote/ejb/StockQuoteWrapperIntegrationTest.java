package pl.marchwicki.jee.stockquote.getquote.ejb;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;
import pl.marchwicki.jee.stockapp.getquote.ejb.QuotationConverter;
import pl.marchwicki.jee.stockapp.getquote.ejb.StockQuoteWrapper;
import pl.marchwicki.jee.stockapp.getquote.ejb.StockQuoteWrapperLocal;

@RunWith(Arquillian.class)
public class StockQuoteWrapperIntegrationTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
			.addClass(QuotationConverter.class)
			.addClass(StockQuoteWrapper.class);
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
	
}
