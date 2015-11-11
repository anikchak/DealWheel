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
@Table(name="listed_vehicle")
@NamedQueries({
	@NamedQuery(name=LISTED_VEHICLE_GET_DETAILS, query="SELECT lv FROM ListedVehicle lv where lv.lvclName= :vehicleName "
			+ "AND lv.lvclMake = :makerName AND lv.lvclType = :vehicleType"),
	@NamedQuery(name=LISTED_VEHICLE_GET_NAMES, query="SELECT lv.lvclName FROM ListedVehicle lv where lv.lvclMake = :makerName"),
	@NamedQuery(name=LISTED_VEHICLE_GET_MAKES, query="SELECT DISTINCT lv.lvclMake FROM ListedVehicle lv")
	})
public class ListedVehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LISTED_VEHICLES_VHCLID_GENERATOR", sequenceName="KEYSEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LISTED_VEHICLES_VHCLID_GENERATOR")
	@Column(name="LVCL_ID")
	private BigInteger lvclId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED")
	private Date lastUpdated;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name="LVCL_NAME")
	private String lvclName;

	@Column(name="LVCL_MAKE")
	private String lvclMake;

	@Column(name="LVCL_TYPE")
	private String lvclType;

	public BigInteger getLvclId() {
		return lvclId;
	}

	public void setLvclId(BigInteger lvclId) {
		this.lvclId = lvclId;
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

	public String getLvclName() {
		return lvclName;
	}

	public void setLvclName(String lvclName) {
		this.lvclName = lvclName;
	}

	public String getLvclMake() {
		return lvclMake;
	}

	public void setLvclMake(String lvclMake) {
		this.lvclMake = lvclMake;
	}

	public String getLvclType() {
		return lvclType;
	}

	public void setLvclType(String lvclType) {
		this.lvclType = lvclType;
	}
	
}