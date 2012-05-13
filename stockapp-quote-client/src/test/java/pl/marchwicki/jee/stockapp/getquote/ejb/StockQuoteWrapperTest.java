package pl.marchwicki.jee.stockapp.getquote.ejb;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.marchwicki.jee.stockapp.ejb.logging.AuditMessageProcessing;
import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

/**
 * More of an integration test - with two dependencies 
 * are manually resolved
 */
public class StockQuoteWrapperTest {

	StockQuoteWrapper wrapper = new StockQuoteWrapper();
	
//	@Test
//	public void webserviceSimpleResponse() {
//		//given
//		wrapper.converter = new QuotationConverter();
//		wrapper.audit = new AuditMessageProcessing();
//		
//		//when
//		Quotation quotation = wrapper.getQuotation("IBM");
//		
//		//then
//		assertNotNull(quotation);
//		assertEquals("IBM", quotation.getSymbol());
//		assertEquals("International Bus", quotation.getName());
//	}	
	
	@Test(expected = NullPointerException.class)
	public void webserviceNPEResponse() {
		//given
		wrapper.converter = new QuotationConverter();
		
		//when
		Quotation quotation = wrapper.getQuotation("IBM");
		
		//then
		assertNotNull(quotation);
		assertEquals("IBM", quotation.getSymbol());
		assertEquals("International Bus", quotation.getName());
	}
	
	@Test(expected = ClassFormatError.class)
	public void webserviceResponse() {
		//given
		wrapper.converter = new QuotationConverter();
		wrapper.audit = new AuditMessageProcessing();
		
		//when
		Quotation quotation = wrapper.getQuotation("IBM");
		
		//then
		assertNotNull(quotation);
		assertEquals("IBM", quotation.getSymbol());
		assertEquals("International Bus", quotation.getName());
	}	
	
}
