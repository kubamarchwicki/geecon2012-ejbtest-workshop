package pl.marchwicki.jee.stockapp.getquote.ejb;

import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

public interface QuotationConverterLocal {

	public Quotation convert(String getQuoteResponse);
	
}
