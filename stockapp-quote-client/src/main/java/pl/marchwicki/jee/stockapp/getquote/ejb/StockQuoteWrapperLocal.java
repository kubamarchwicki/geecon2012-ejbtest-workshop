package pl.marchwicki.jee.stockapp.getquote.ejb;

import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

public interface StockQuoteWrapperLocal {

	public Quotation getQuotation(String symbol);
	
}
