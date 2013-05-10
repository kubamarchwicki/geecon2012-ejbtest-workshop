package pl.marchwicki.jee.stockapp.ejb;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.SingletonBean;
import org.apache.openejb.jee.StatelessBean;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.junit.MockInjector;
import org.apache.openejb.junit.Module;
import org.apache.openejb.mockito.MockitoInjector;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import pl.marchwicki.jee.stockapp.basetypes.StockQuoteCompanyName;
import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

//TODO: http://rmannibucau.wordpress.com/2012/09/13/use-mockito-with-openejb/

@RunWith(ApplicationComposer.class)
public class DozerQuotationConverterIntegrationTest {

	//... przygotowujemy kontener
	
	@Module
	public EjbJar module() {
		final EjbJar ejbJar = new EjbJar();
		ejbJar.addEnterpriseBean(new SingletonBean(
				DozerConverterCache.class));
		ejbJar.addEnterpriseBean(new StatelessBean(
				DozerQuotationConverter.class));

		return ejbJar;
	}
	
	//... wykonujemy test
	
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
	
}
