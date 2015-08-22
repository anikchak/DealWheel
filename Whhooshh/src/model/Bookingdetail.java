package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the bookingdetails database table.
 * 
 */
@Entity
@Table(name="bookingdetails")
@NamedQuery(name="Bookingdetail.findAll", query="SELECT b FROM Bookingdetail b")
public class Bookingdetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private int bookingSeq;
	private String allottedVehicleNo;
	private Date bookingFromDate;
	private String bookingId;
	private String bookingStatus;
	private Date bookingToDate;
	private int personId;

	public Bookingdetail() {
	}


	@Id
	@SequenceGenerator(name="BOOKINGDETAILS_BOOKINGSEQ_GENERATOR", sequenceName="KEYGEN")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOOKINGDETAILS_BOOKINGSEQ_GENERATOR")
	public int getBookingSeq() {
		return this.bookingSeq;
	}

	public void setBookingSeq(int bookingSeq) {
		this.bookingSeq = bookingSeq;
	}


	public String getAllottedVehicleNo() {
		return this.allottedVehicleNo;
	}

	public void setAllottedVehicleNo(String allottedVehicleNo) {
		this.allottedVehicleNo = allottedVehicleNo;
	}


	@Temporal(TemporalType.DATE)
	public Date getBookingFromDate() {
		return this.bookingFromDate;
	}

	public void setBookingFromDate(Date bookingFromDate) {
		this.bookingFromDate = bookingFromDate;
	}


	public String getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}


	public String getBookingStatus() {
		return this.bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}


	@Temporal(TemporalType.DATE)
	public Date getBookingToDate() {
		return this.bookingToDate;
	}

	public void setBookingToDate(Date bookingToDate) {
		this.bookingToDate = bookingToDate;
	}


	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

}