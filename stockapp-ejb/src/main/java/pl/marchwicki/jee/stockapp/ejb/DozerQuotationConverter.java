package pl.marchwicki.jee.stockapp.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapper;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;

import pl.marchwicki.jee.stockapp.basetypes.StockQuoteCompanyName;
import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;


//@Stateless
//public class DozerQuotationConverter implements DozerQuotationConverterLocal {
//
//	public StockQuoteCompanyName convert(Quotation quote) {
//		List<String> myMappingFiles = new ArrayList<String>();
//		myMappingFiles.add("dozerBeanMapping.xml");
//		DozerBeanMapper mapper = new DozerBeanMapper();
//		mapper.setMappingFiles(myMappingFiles);
//		
//		return mapper.map(quote,
//				StockQuoteCompanyName.class);
//	}
//
//}

@Stateless
public class DozerQuotationConverter implements DozerQuotationConverterLocal {

	@EJB
	DozerConverterCacheLocal dozerCache;
	
	public StockQuoteCompanyName convert(Quotation quote) {
		return dozerCache.getMapper().map(quote,
				StockQuoteCompanyName.class);
	}

}
