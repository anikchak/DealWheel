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

	@Id
	@SequenceGenerator(name="BOOKINGDETAILS_BOOKINGSEQ_GENERATOR", sequenceName="KEYSEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOOKINGDETAILS_BOOKINGSEQ_GENERATOR")
	private int bookingSeq;

	private String allottedVehicleNo;

	@Temporal(TemporalType.DATE)
	private Date bookingFromDate;

	private String bookingid;

	private String bookingStatus;

	@Temporal(TemporalType.DATE)
	private Date bookingToDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;

	private String pickupLocation;

	private String username;

	private int vehicleId;

	private int vehicleprovider;

	public Bookingdetail() {
	}

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

	public Date getBookingFromDate() {
		return this.bookingFromDate;
	}

	public void setBookingFromDate(Date bookingFromDate) {
		this.bookingFromDate = bookingFromDate;
	}

	public String getBookingid() {
		return this.bookingid;
	}

	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}

	public String getBookingStatus() {
		return this.bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Date getBookingToDate() {
		return this.bookingToDate;
	}

	public void setBookingToDate(Date bookingToDate) {
		this.bookingToDate = bookingToDate;
	}

	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getPickupLocation() {
		return this.pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getVehicleprovider() {
		return this.vehicleprovider;
	}

	public void setVehicleprovider(int vehicleprovider) {
		this.vehicleprovider = vehicleprovider;
	}

}