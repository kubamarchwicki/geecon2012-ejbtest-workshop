package pl.marchwicki.jee.stockapp.getquote.ejb;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Stateless;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

@Stateless
public class QuotationConverter implements QuotationConverterLocal {

	private static Logger log = Logger.getLogger(QuotationConverter.class);
	
	public Quotation convert(String getQuoteResponse) {
		XPath xPath = XPathFactory.newInstance().newXPath();
		
		Quotation.Builder builder = new Quotation.Builder();
		
		try {
			builder.withSymbol(xPath.evaluate("/StockQuotes/Stock/Symbol", new InputSource(new StringReader(getQuoteResponse))));
			builder.withName(xPath.evaluate("/StockQuotes/Stock/Name", new InputSource(new StringReader(getQuoteResponse))));
			builder.withLast(xPath.evaluate("/StockQuotes/Stock/Last", new InputSource(new StringReader(getQuoteResponse))));
			
			String sDate = xPath.evaluate("/StockQuotes/Stock/Date", new InputSource(new StringReader(getQuoteResponse)));
			String sTime = xPath.evaluate("/StockQuotes/Stock/Time", new InputSource(new StringReader(getQuoteResponse)));
			
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy K:mma");
			Date date = format.parse(sDate + " " + sTime);
			builder.withDate(date);
			
			builder.withChange(xPath.evaluate("/StockQuotes/Stock/Change", new InputSource(new StringReader(getQuoteResponse))));
			builder.withOpen(xPath.evaluate("/StockQuotes/Stock/Open", new InputSource(new StringReader(getQuoteResponse))));
			builder.withHigh(xPath.evaluate("/StockQuotes/Stock/High", new InputSource(new StringReader(getQuoteResponse))));
			builder.withLow(xPath.evaluate("/StockQuotes/Stock/Low", new InputSource(new StringReader(getQuoteResponse))));
			builder.withVolume(Integer.valueOf(xPath.evaluate("/StockQuotes/Stock/Volume", new InputSource(new StringReader(getQuoteResponse)))));
			
		} catch (Exception e) {
			log.warn("Convertion problem", e);
		}
		
		return builder.build();
	}

}
