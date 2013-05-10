package pl.marchwicki.jee.stockapp.ejb;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.marchwicki.jee.stockapp.basetypes.StockQuoteCompanyName;
import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

public class DozerQuotationConverterTest {

//	@Test
//	public void convertionTest() {
//		//given
//		DozerQuotationConverter converter = new DozerQuotationConverter();
//		
//		Quotation q = new Quotation.Builder()
//			.withName("companyName")
//			.withSymbol("companySymbol")
//			.build();
//		
//		//when 
//		StockQuoteCompanyName convert = converter.convert(q);
//		
//		//then
//		assertEquals(convert.getCompanyName(), q.getName());
//		assertEquals(convert.getCompanySymbol(), q.getSymbol());
//	}
	
	@Test
	public void convertionTest() {
		//given
		DozerQuotationConverter converter = new DozerQuotationConverter();
		DozerConverterCache cache = new DozerConverterCache();
		cache.setup();
		converter.dozerCache = cache;
		
		Quotation q = new Quotation.Builder()
			.withName("companyName")
			.withSymbol("companySymbol")
			.build();
		
		//when 
		StockQuoteCompanyName convert = converter.convert(q);
		
		//then
		assertEquals(convert.getCompanyName(), q.getName());
		assertEquals(convert.getCompanySymbol(), q.getSymbol());
	}
	
}
