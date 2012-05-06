package pl.marchwicki.jee.stockapp.getquote.ejb;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

public class StockQuoteWrapperTest {

	StockQuoteWrapper wrapper = new StockQuoteWrapper();
	
	@Before
	public void setup() {
		wrapper.converter = new QuotationConverter();
	}
	
	@Test
	public void webserviceResponse() {
		Quotation quotation = wrapper.getQuotation("IBM");
		assertNotNull(quotation);
	}
	
}
