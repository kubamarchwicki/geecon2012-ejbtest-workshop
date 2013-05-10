package pl.marchwicki.jee.stockapp.getquote.ejb;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import pl.marchwicki.jee.stockapp.ejb.logging.AuditMessageProcessingLocal;
import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

/**
 * Using Mockito to abstract not important layers - from this particular test
 * perspective
 * 
 */
public class StockQuoteWrapperMockTest {

	StockQuoteWrapper wrapper = new StockQuoteWrapper();

	@Before
	public void setup() {
		wrapper.converter = mock(QuotationConverterLocal.class);
		wrapper.audit = mock(AuditMessageProcessingLocal.class);
		when(wrapper.converter.convert(anyString()))
				.thenReturn(new Quotation());
	}

	@Test
	public void webserviceResponse() {
		wrapper.getQuotation("IBM");

		verify(wrapper.converter).convert(
				matches("<StockQuotes><Stock><Symbol>IBM</Symbol>.*</Stock></StockQuotes>"));
	}

}
