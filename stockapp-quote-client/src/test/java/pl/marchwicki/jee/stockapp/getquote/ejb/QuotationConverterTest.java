package pl.marchwicki.jee.stockapp.getquote.ejb;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

/**
 * Traditional unit test
 *
 */
public class QuotationConverterTest {

	QuotationConverterLocal converter = new QuotationConverter();
	
	@Test
	public void convertCorrectString() {
		//given
		String quote = "<StockQuotes>" +
				"<Stock>" +
					"<Symbol>GOOG</Symbol><Last>596.97</Last>" +
					"<Date>5/4/2012</Date><Time>4:00pm</Time>" +
					"<Change>-14.05</Change><Open>605.92</Open>" +
					"<High>607.8885</High><Low>596.81</Low>" +
					"<Volume>2207360</Volume>" +
					"<MktCap>194.6B</MktCap>" +
					"<PreviousClose>611.02</PreviousClose>" +
					"<PercentageChange>-2.30%</PercentageChange>" +
					"<AnnRange>473.02 - 670.25</AnnRange>" +
					"<Earns>32.998</Earns><P-E>18.52</P-E>" +
					"<Name>Google Inc.</Name>" +
				"</Stock>" +
				"</StockQuotes>";

		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, 4);
		c.set(Calendar.DAY_OF_MONTH, 4);
		c.set(Calendar.YEAR, 2012);
		c.set(Calendar.AM_PM, Calendar.PM);
		c.set(Calendar.HOUR, 4);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		Quotation quotation = new Quotation.Builder()
			.withSymbol("GOOG")
			.withName("Google Inc.")
			.withLast("596.97")
			.withDate(c.getTime())
			.withChange("-14.05")
			.withOpen("605.92")
			.withHigh("607.8885")
			.withLow("596.81")
			.withVolume(2207360)
			.build();

		//when
		Quotation convertedQuotation = converter.convert(quote);
		
		//then
		assertEquals(quotation, convertedQuotation);
	}
	
}
