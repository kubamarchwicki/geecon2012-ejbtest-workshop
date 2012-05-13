package pl.marchwicki.jee.stockapp.ejb;

import pl.marchwicki.jee.stockapp.basetypes.StockQuoteCompanyName;
import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

public interface DozerQuotationConverterLocal {

	public StockQuoteCompanyName convert(Quotation quote);
	
}
