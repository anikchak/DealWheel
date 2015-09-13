package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the bookingshistory database table.
 * 
 */
@Entity
@NamedQuery(name="Bookingshistory.findAll", query="SELECT b FROM Bookingshistory b")
public class Bookingshistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BOOKINGSHISTORY_BKNGSEQ_GENERATOR", sequenceName="KEYSEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOOKINGSHISTORY_BKNGSEQ_GENERATOR")
	@Column(name="BKNG_SEQ")
	private String bkngSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BKNG_CREATION_DATE")
	private Date bkngCreationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BKNG_FROM_DATE")
	private Date bkngFromDate;

	@Column(name="BKNG_NUMBER")
	private String bkngNumber;

	@Column(name="BKNG_PAYMENT_ID")
	private BigInteger bkngPaymentId;

	@Column(name="BKNG_STATUS")
	private String bkngStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BKNG_TO_DATE")
	private Date bkngToDate;

	@Column(name="BKNG_VEHICLE")
	private BigInteger bkngVehicle;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED")
	private Date lastUpdated;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	public Bookingshistory() {
	}

	public String getBkngSeq() {
		return this.bkngSeq;
	}

	public void setBkngSeq(String bkngSeq) {
		this.bkngSeq = bkngSeq;
	}

	public Date getBkngCreationDate() {
		return this.bkngCreationDate;
	}

	public void setBkngCreationDate(Date bkngCreationDate) {
		this.bkngCreationDate = bkngCreationDate;
	}

	public Date getBkngFromDate() {
		return this.bkngFromDate;
	}

	public void setBkngFromDate(Date bkngFromDate) {
		this.bkngFromDate = bkngFromDate;
	}

	public String getBkngNumber() {
		return this.bkngNumber;
	}

	public void setBkngNumber(String bkngNumber) {
		this.bkngNumber = bkngNumber;
	}

	public BigInteger getBkngPaymentId() {
		return this.bkngPaymentId;
	}

	public void setBkngPaymentId(BigInteger bkngPaymentId) {
		this.bkngPaymentId = bkngPaymentId;
	}

	public String getBkngStatus() {
		return this.bkngStatus;
	}

	public void setBkngStatus(String bkngStatus) {
		this.bkngStatus = bkngStatus;
	}

	public Date getBkngToDate() {
		return this.bkngToDate;
	}

	public void setBkngToDate(Date bkngToDate) {
		this.bkngToDate = bkngToDate;
	}

	public BigInteger getBkngVehicle() {
		return this.bkngVehicle;
	}

	public void setBkngVehicle(BigInteger bkngVehicle) {
		this.bkngVehicle = bkngVehicle;
	}

	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

}