package pl.marchwicki.jee.stockapp.ejb;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.SingletonBean;
import org.apache.openejb.jee.StatelessBean;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.junit.Module;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.marchwicki.jee.stockapp.basetypes.StockQuoteCompanyName;
import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

@RunWith(ApplicationComposer.class)
public class DozerQuotationConverterIntegrationTest {

	@EJB
	DozerQuotationConverterLocal converter;
	
	@Test
	public void convertionTest() {
		Quotation q = new Quotation.Builder().withName("companyName")
				.withSymbol("companySymbol").build();

		// when
		StockQuoteCompanyName convert = converter.convert(q);

		// then
		assertEquals(convert.getCompanyName(), q.getName());
		assertEquals(convert.getCompanySymbol(), q.getSymbol());
	}

	@Module
	public EjbJar module() {
		final EjbJar ejbJar = new EjbJar();
		ejbJar.addEnterpriseBean(new SingletonBean(DozerConverterCache.class));
		ejbJar.addEnterpriseBean(new StatelessBean(DozerQuotationConverter.class));
		
		return ejbJar;
	}	
}
