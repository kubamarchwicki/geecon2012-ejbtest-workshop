package pl.marchwicki.jee.stockapp.getquote.basetypes;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Simple object representation of webserviceX stock XML GetQuoteResponse
 * <StockQuotes> <Stock> <Symbol>GOOG</Symbol> <Last>596.97</Last>
 * <Date>5/4/2012</Date> <Time>4:00pm</Time> <Change>-14.05</Change>
 * <Open>605.92</Open> <High>607.8885</High> <Low>596.81</Low>
 * <Volume>2207360</Volume> <MktCap>194.6B</MktCap>
 * <PreviousClose>611.02</PreviousClose>
 * <PercentageChange>-2.30%</PercentageChange> <AnnRange>473.02 -
 * 670.25</AnnRange> <Earns>32.998</Earns>
 * <P-E>
 * 18.52
 * </P-E>
 * <Name>Google Inc.</Name> </Stock> </StockQuotes>
 * 
 */
public class Quotation {

	private String symbol;
	private String name;
	private BigDecimal last;
	private Date date;
	private BigDecimal change;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private Integer volume;

	public Quotation() {
		
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public BigDecimal getLast() {
		return last;
	}

	public void setLast(BigDecimal last) {
		this.last = last;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((change == null) ? 0 : change.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((high == null) ? 0 : high.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((low == null) ? 0 : low.hashCode());
		result = prime * result + ((open == null) ? 0 : open.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Quotation other = (Quotation) obj;
		if (change == null) {
			if (other.change != null) {
				return false;
			}
		} else if (!change.equals(other.change)) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (high == null) {
			if (other.high != null) {
				return false;
			}
		} else if (!high.equals(other.high)) {
			return false;
		}
		if (last == null) {
			if (other.last != null) {
				return false;
			}
		} else if (!last.equals(other.last)) {
			return false;
		}
		if (low == null) {
			if (other.low != null) {
				return false;
			}
		} else if (!low.equals(other.low)) {
			return false;
		}
		if (open == null) {
			if (other.open != null) {
				return false;
			}
		} else if (!open.equals(other.open)) {
			return false;
		}
		if (symbol == null) {
			if (other.symbol != null) {
				return false;
			}
		} else if (!symbol.equals(other.symbol)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}		
		if (volume == null) {
			if (other.volume != null) {
				return false;
			}
		} else if (!volume.equals(other.volume)) {
			return false;
		}
		return true;
	}

	private Quotation(Builder builder) {
		this.symbol = builder.symbol;
		this.name = builder.name;
		this.last = builder.last;
		this.date = builder.date;
		this.change = builder.change;
		this.open = builder.open;
		this.high = builder.high;
		this.low = builder.low;
		this.volume = builder.volume;
	}

	public static class Builder {
		private String symbol;
		private String name;
		private BigDecimal last;
		private Date date;
		private BigDecimal change;
		private BigDecimal open;
		private BigDecimal high;
		private BigDecimal low;
		private Integer volume;

		public Builder withSymbol(String symbol) {
			this.symbol = symbol;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder withLast(BigDecimal last) {
			this.last = last;
			return this;
		}

		public Builder withLast(String last) {
			this.last = new BigDecimal(last);
			return this;
		}

		public Builder withDate(Date date) {
			this.date = date;
			return this;
		}
		
		public Builder withChange(BigDecimal change) {
			this.change = change;
			return this;
		}

		public Builder withChange(String change) {
			this.change = new BigDecimal(change);
			return this;
		}

		public Builder withOpen(BigDecimal open) {
			this.open = open;
			return this;
		}
		
		public Builder withOpen(String open) {
			this.open = new BigDecimal(open);
			return this;
		}		

		public Builder withHigh(BigDecimal high) {
			this.high = high;
			return this;
		}

		public Builder withHigh(String high) {
			this.high = new BigDecimal(high);
			return this;
		}
		
		public Builder withLow(BigDecimal low) {
			this.low = low;
			return this;
		}
		
		public Builder withLow(String low) {
			this.low = new BigDecimal(low);
			return this;
		}		

		public Builder withVolume(Integer volume) {
			this.volume = volume;
			return this;
		}

		public Quotation build() {
			return new Quotation(this);
		}
	}

	@Override
	public String toString() {
		return "Quotation [change=" + change + ", date=" + date + ", high="
				+ high + ", last=" + last + ", low=" + low + ", open=" + open
				+ ", symbol=" + symbol + ", volume=" + volume + "]";
	}

}
