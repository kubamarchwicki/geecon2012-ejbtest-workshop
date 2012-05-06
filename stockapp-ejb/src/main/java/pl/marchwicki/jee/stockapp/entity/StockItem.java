package pl.marchwicki.jee.stockapp.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "STOCKITEMS")
public class StockItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STOCKITEM_ID")
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "COMPANY_FK")
	private Company company;
	
	@Column(name = "TRANS_VL")
	private Integer transactionVolume;
	
	@Column(name = "TRANS_PRICE")
	private BigDecimal unitPrice;
	
	@Column(name = "USER")
	private String user;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRANS_DT")
	private Date date;
	
	
	
}
