package pl.marchwicki.jee.stockapp.getquote.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.marchwicki.jee.stockapp.ejb.logging.AuditMessageProcessingLocal;
import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;
import pl.marchwicki.jee.stockapp.webservicex.stockquote.client.StockQuote;
import pl.marchwicki.jee.stockapp.webservicex.stockquote.client.StockQuoteSoap;

@Stateless
public class StockQuoteWrapper implements StockQuoteWrapperLocal {

	@EJB
	QuotationConverterLocal converter;
	
	@EJB
	AuditMessageProcessingLocal audit;
	
	public Quotation getQuotation(String symbol) {
		
		StockQuote service = new StockQuote();
		StockQuoteSoap soap = service.getStockQuoteSoap();
		String quote = soap.getQuote(symbol);
		
		audit.auditLog(quote);
		return converter.convert(quote);
	}

}
