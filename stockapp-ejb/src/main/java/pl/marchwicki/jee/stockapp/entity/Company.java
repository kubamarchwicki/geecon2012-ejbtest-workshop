package pl.marchwicki.jee.stockapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY_TB")
public class Company {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "COMPANY_ID")
	private long id;
	
	@Column(name = "ABBRV")
	private String abbrevation;
	
}
