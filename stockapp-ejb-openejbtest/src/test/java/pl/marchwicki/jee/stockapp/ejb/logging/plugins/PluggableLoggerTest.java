package pl.marchwicki.jee.stockapp.ejb.logging.plugins;

import javax.ejb.EJB;

import org.apache.log4j.BasicConfigurator;
import org.apache.openejb.config.EjbModule;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.StatefulBean;
import org.apache.openejb.jee.StatelessBean;
import org.apache.openejb.jee.oejb3.EjbDeployment;
import org.apache.openejb.jee.oejb3.Jndi;
import org.apache.openejb.jee.oejb3.OpenejbJar;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.junit.Module;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ApplicationComposer.class)
public class PluggableLoggerTest {

	@EJB
	PluggableAuditProcessingLocal processor;
	
	@Test
	public void test() {
		processor.auditLog("Hello world!");
	}
	
	@Module
	public EjbJar module() {
		final EjbJar ejbJar = new EjbJar();
		ejbJar.addEnterpriseBean(new StatelessBean(Log4jLoggerPlugin.class));
		ejbJar.addEnterpriseBean(new StatelessBean(PluggableAuditProcessing.class));
		
		return ejbJar;
	}	
	
//	@Module
//	public EjbModule module() {
//		EjbJar ejbJar = new EjbJar("application");
//		ejbJar.addEnterpriseBean(new StatelessBean(Log4jLoggerPlugin.class));
//		ejbJar.addEnterpriseBean(new StatelessBean(PluggableAuditProcessing.class));
//		
//		EjbModule ejbModule = new EjbModule(ejbJar, new OpenejbJar());
//		EjbDeployment deployment = new EjbDeployment(new StatefulBean(Log4jLoggerPlugin.class));
//		deployment.getJndi().add(new Jndi("stockapp/Log4jLoggerPlugin", "Local"));
//		ejbModule.getOpenejbJar().addEjbDeployment(deployment);
//		
//		return ejbModule;
//	} 	
	
	@BeforeClass
	public static void setup() {
		BasicConfigurator.configure();
	}
	
}
