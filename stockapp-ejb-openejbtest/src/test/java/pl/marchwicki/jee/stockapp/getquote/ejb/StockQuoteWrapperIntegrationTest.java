package pl.marchwicki.jee.stockapp.getquote.ejb;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.StatelessBean;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.junit.Module;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.marchwicki.jee.stockapp.getquote.basetypes.Quotation;

@RunWith(ApplicationComposer.class)
public class StockQuoteWrapperIntegrationTest {

	@EJB
	StockQuoteWrapperLocal wrapper;
	
	@Test
	public void externalServiceWrapperTest() {
		Quotation q = wrapper.getQuotation("YHOO");
		assertNotNull(q);
		assertEquals("YHOO", q.getSymbol());
		assertEquals("Yahoo! Inc.", q.getName());
	}
	
	@Module
	public EjbJar module() {
		final EjbJar ejbJar = new EjbJar();
		ejbJar.addEnterpriseBean(new StatelessBean(QuotationConverter.class));
		ejbJar.addEnterpriseBean(new StatelessBean(StockQuoteWrapper.class));
		
		return ejbJar;
	}
	
}
