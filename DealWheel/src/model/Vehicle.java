package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;
import static services.utility.GenericConstant.*;


/**
 * The persistent class for the vehicles database table.
 * 
 */
@Entity
@Table(name="vehicles")
@NamedQueries({
	@NamedQuery(name=VEHICLE_FIND_ALL, query="SELECT v FROM Vehicle v"),
	@NamedQuery(name=VEHICLE_GET_NAMES, query="SELECT v.vhclName FROM Vehicle v"),
	@NamedQuery(name=VEHICLE_GET_VEHICLE_DETAILS_FOR_USER, 
		query="SELECT v.vhclName, v.vhclMake, v.vhclRegistrationNo, v.vhclYearOfManufacture, v.vhclPerDayCost, v.vhclSecurityDeposit, "
				+ "a.addrLine1, a.addrLine2, a.addrLine3, a.addrLocality, a.addrCity, a.addrState, a.addrCountry, a.addrPinCode "
				+ "FROM Vehicle v JOIN Address a ON v.vhclAddressId = a.addrId JOIN User u ON a.userId = u.userId WHERE u.userId = :userId ")
})
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VEHICLES_VHCLID_GENERATOR", sequenceName="KEYSEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VEHICLES_VHCLID_GENERATOR")
	@Column(name="VHCL_ID")
	private String vhclId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED")
	private Date lastUpdated;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name="VHCL_MAKE")
	private String vhclMake;

	@Column(name="VHCL_NAME")
	private String vhclName;

	@Column(name="VHCL_PER_DAY_COST")
	private int vhclPerDayCost;

	@Column(name="VHCL_ADDRESS_ID")
	private BigInteger vhclAddressId;

	@Column(name="VHCL_REGISTRATION_NO")
	private String vhclRegistrationNo;

	@Column(name="VHCL_SECURITY_DEPOSIT")
	private int vhclSecurityDeposit;

	@Column(name="VHCL_TYPE")
	private String vhclType;

	@Column(name="VHCL_YEAR_OF_MANUFACTURE")
	private String vhclYearOfManufacture;
	
	public Vehicle() {
	}

	public String getVhclId() {
		return this.vhclId;
	}

	public void setVhclId(String vhclId) {
		this.vhclId = vhclId;
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

	public String getVhclMake() {
		return this.vhclMake;
	}

	public void setVhclMake(String vhclMake) {
		this.vhclMake = vhclMake;
	}

	public String getVhclName() {
		return this.vhclName;
	}

	public void setVhclName(String vhclName) {
		this.vhclName = vhclName;
	}

	public int getVhclPerDayCost() {
		return this.vhclPerDayCost;
	}

	public void setVhclPerDayCost(int vhclPerDayCost) {
		this.vhclPerDayCost = vhclPerDayCost;
	}

	public BigInteger getVhclAddressId() {
		return this.vhclAddressId;
	}

	public void setVhclAddressId(BigInteger vhclAddressId) {
		this.vhclAddressId = vhclAddressId;
	}

	public String getVhclRegistrationNo() {
		return this.vhclRegistrationNo;
	}

	public void setVhclRegistrationNo(String vhclRegistrationNo) {
		this.vhclRegistrationNo = vhclRegistrationNo;
	}

	public int getVhclSecurityDeposit() {
		return this.vhclSecurityDeposit;
	}

	public void setVhclSecurityDeposit(int vhclSecurityDeposit) {
		this.vhclSecurityDeposit = vhclSecurityDeposit;
	}

	public String getVhclType() {
		return this.vhclType;
	}

	public void setVhclType(String vhclType) {
		this.vhclType = vhclType;
	}

	public String getVhclYearOfManufacture() {
		return this.vhclYearOfManufacture;
	}

	public void setVhclYearOfManufacture(String vhclYearOfManufacture) {
		this.vhclYearOfManufacture = vhclYearOfManufacture;
	}

}