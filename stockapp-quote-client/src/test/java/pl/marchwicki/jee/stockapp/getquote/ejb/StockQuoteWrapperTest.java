package pl.marchwicki.jee.stockapp.getquote.ejb;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

/**
 * More of an integration test - with two dependencies 
 * are manually resolved
 */
public class StockQuoteWrapperTest {

	StockQuoteWrapper wrapper = new StockQuoteWrapper();
	
	@Before
	public void setup() {
		wrapper.converter = new QuotationConverter();
//		wrapper.audit = new AuditMessageProcessing();
	}
	
	@Test(expected = NullPointerException.class)
	public void webserviceResponse() {
		Quotation quotation = wrapper.getQuotation("IBM");
		assertNotNull(quotation);
		assertEquals("IBM", quotation.getSymbol());
		assertEquals("International Bus", quotation.getName());
	}
	
}
