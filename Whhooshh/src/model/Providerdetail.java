package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the providerdetails database table.
 * 
 */
@Entity
@Table(name="providerdetails")
@SecondaryTables({
    @SecondaryTable(name="bikelookup", 
        pkJoinColumns=@PrimaryKeyJoinColumn(name="bikeSeq"))
})
public class Providerdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROVIDERDETAILS_SEQ_GENERATOR", sequenceName="KEYSEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROVIDERDETAILS_SEQ_GENERATOR")
	private int seq;

	private String lockStatus;

	private String pickupLocation;

	private String providerAgencyName;

	private int providerId;

	private String providerName;

	private int vehicleId;

	private String vehicleNumber;

	private String vehicleRegistrationNumber;
	
	@Column(table="bikelookup")
	private int bikeSeq;
	@Column(table="bikelookup")
	private String bikeName;
	@Column(table="bikelookup")
	private String company;

	public Providerdetail() {
	}

	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getLockStatus() {
		return this.lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getPickupLocation() {
		return this.pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getProviderAgencyName() {
		return this.providerAgencyName;
	}

	public void setProviderAgencyName(String providerAgencyName) {
		this.providerAgencyName = providerAgencyName;
	}

	public int getProviderId() {
		return this.providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return this.providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public int getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleNumber() {
		return this.vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleRegistrationNumber() {
		return this.vehicleRegistrationNumber;
	}

	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}

	public int getBikeSeq() {
		return bikeSeq;
	}

	public void setBikeSeq(int bikeSeq) {
		this.bikeSeq = bikeSeq;
	}

	public String getBikeName() {
		return bikeName;
	}

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}