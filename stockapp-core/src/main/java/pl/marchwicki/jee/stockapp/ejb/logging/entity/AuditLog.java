package pl.marchwicki.jee.stockapp.ejb.logging.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import pl.marchwicki.jee.stockapp.ejb.logging.entity.AuditLog;

@Entity
public class AuditLog {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public AuditLog() {
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	private AuditLog(Builder builder) {
		this.id = builder.id;
		this.message = builder.message;
		this.date = builder.date;
	}

	public static class Builder {
		private long id;
		private String message;
		private Date date;

		public Builder withId(long id) {
			this.id = id;
			return this;
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder withDate(Date date) {
			this.date = date;
			return this;
		}

		public AuditLog build() {
			return new AuditLog(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		AuditLog other = (AuditLog) obj;
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AuditLog [date=" + date + ", id=" + id + ", message=" + message
				+ "]";
	}
	
}
