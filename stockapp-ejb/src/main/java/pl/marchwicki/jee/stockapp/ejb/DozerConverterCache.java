package pl.marchwicki.jee.stockapp.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;

//@Service
//@Management(DozerConverterCacheLocal.class)

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class DozerConverterCache implements DozerConverterCacheLocal {

	private DozerBeanMapper mapper;
	
	@PostConstruct
	public void setup() {
		List<String> myMappingFiles = new ArrayList<String>();
		myMappingFiles.add("dozerBeanMapping.xml");
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		
	}
	
	public Mapper getMapper() {
		return mapper;
	}

}
