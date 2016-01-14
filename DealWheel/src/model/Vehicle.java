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
	@NamedQuery(name=VEHICLE_GET_VEHICLE_DETAILS_FOR_USER, 
		query="SELECT lv.lvclName, lv.lvclMake, v.vhclRegistrationNo, v.vhclYearOfManufacture, v.vhclPerDayCost, v.vhclSecurityDeposit, "
				+ "a.addrLine1, a.addrLine2, a.addrLine3, a.addrLocality, a.addrCity, a.addrState, a.addrCountry, a.addrPinCode, v.vhclId "
				+ "FROM Vehicle v JOIN Address a ON v.vhclAddressId = a.addrId JOIN User u ON a.userId = u.userId JOIN ListedVehicle lv ON v.listedVhclId = lv.lvclId "
				+ "WHERE u.userId = :vendorId ")
})
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VEHICLES_VHCLID_GENERATOR", sequenceName="KEYSEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VEHICLES_VHCLID_GENERATOR")
	@Column(name="VHCL_ID")
	private BigInteger vhclId;
	
	@Column(name="VHCL_LVCL_ID")
	private BigInteger listedVhclId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED")
	private Date lastUpdated;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name="VHCL_PER_DAY_COST")
	private int vhclPerDayCost;

	@Column(name="VHCL_ADDRESS_ID")
	private BigInteger vhclAddressId;

	@Column(name="VHCL_REGISTRATION_NO")
	private String vhclRegistrationNo;

	@Column(name="VHCL_SECURITY_DEPOSIT")
	private int vhclSecurityDeposit;

	@Column(name="VHCL_YEAR_OF_MANUFACTURE")
	private String vhclYearOfManufacture;

	public BigInteger getVhclId() {
		return vhclId;
	}

	public void setVhclId(BigInteger vhclId) {
		this.vhclId = vhclId;
	}

	public BigInteger getListedVhclId() {
		return listedVhclId;
	}

	public void setListedVhclId(BigInteger listedVhclId) {
		this.listedVhclId = listedVhclId;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public int getVhclPerDayCost() {
		return vhclPerDayCost;
	}

	public void setVhclPerDayCost(int vhclPerDayCost) {
		this.vhclPerDayCost = vhclPerDayCost;
	}

	public BigInteger getVhclAddressId() {
		return vhclAddressId;
	}

	public void setVhclAddressId(BigInteger vhclAddressId) {
		this.vhclAddressId = vhclAddressId;
	}

	public String getVhclRegistrationNo() {
		return vhclRegistrationNo;
	}

	public void setVhclRegistrationNo(String vhclRegistrationNo) {
		this.vhclRegistrationNo = vhclRegistrationNo;
	}

	public int getVhclSecurityDeposit() {
		return vhclSecurityDeposit;
	}

	public void setVhclSecurityDeposit(int vhclSecurityDeposit) {
		this.vhclSecurityDeposit = vhclSecurityDeposit;
	}

	public String getVhclYearOfManufacture() {
		return vhclYearOfManufacture;
	}

	public void setVhclYearOfManufacture(String vhclYearOfManufacture) {
		this.vhclYearOfManufacture = vhclYearOfManufacture;
	}

}