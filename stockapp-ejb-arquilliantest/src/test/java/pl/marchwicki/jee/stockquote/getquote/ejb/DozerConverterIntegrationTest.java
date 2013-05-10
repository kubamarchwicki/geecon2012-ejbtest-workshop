package pl.marchwicki.jee.stockquote.getquote.ejb;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.marchwicki.jee.stockapp.basetypes.StockQuoteCompanyName;
import pl.marchwicki.jee.stockapp.ejb.DozerConverterCache;
import pl.marchwicki.jee.stockapp.ejb.DozerConverterCacheLocal;
import pl.marchwicki.jee.stockapp.ejb.DozerQuotationConverter;
import pl.marchwicki.jee.stockapp.ejb.DozerQuotationConverterLocal;
import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

@RunWith(Arquillian.class)
public class DozerConverterIntegrationTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addClasses(DozerConverterCacheLocal.class, DozerConverterCache.class)
				.addClasses(DozerQuotationConverterLocal.class, DozerQuotationConverter.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	DozerQuotationConverterLocal converter;
	
	@Test
	public void externalServiceWrapperTest() {
		Quotation q = new Quotation.Builder().withName("companyName")
				.withSymbol("companySymbol").build();

		// when
		StockQuoteCompanyName convert = converter.convert(q);

		// then
		assertEquals(convert.getCompanyName(), q.getName());
		assertEquals(convert.getCompanySymbol(), q.getSymbol());		
	}

}
