package model;

import java.io.Serializable;
import java.math.BigInteger;
import static services.utility.GenericConstant.*;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the listed_vehicle database table.
 * 
 */
@Entity
@Table(name="listed_vehicle")
@NamedQueries({
	@NamedQuery(name=LISTED_VEHICLE_GET_DETAILS, query="SELECT lv FROM ListedVehicle lv where lv.lvclName= :vehicleName "
			+ "AND lv.lvclMake = :makerName AND lv.lvclType = :vehicleType"),
	@NamedQuery(name=LISTED_VEHICLE_GET_NAMES, query="SELECT lv.lvclName FROM ListedVehicle lv where lv.lvclMake = :makerName"),
	@NamedQuery(name=LISTED_VEHICLE_GET_MAKES, query="SELECT DISTINCT lv.lvclMake FROM ListedVehicle lv"),
	@NamedQuery(name=LISTED_VEHICLE_FIND_ALL, query="SELECT l FROM ListedVehicle l")
	})
public class ListedVehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LISTED_VEHICLE_LVCLID_GENERATOR", sequenceName="KEYSEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LISTED_VEHICLE_LVCLID_GENERATOR")
	@Column(name="LVCL_ID")
	private BigInteger lvclId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED")
	private Date lastUpdated;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name="LVCL_IMG_URL")
	private String lvclImgUrl;

	@Column(name="LVCL_MAKE")
	private String lvclMake;

	@Column(name="LVCL_NAME")
	private String lvclName;

	@Column(name="LVCL_TYPE")
	private String lvclType;

	public ListedVehicle() {
	}

	public BigInteger getLvclId() {
		return this.lvclId;
	}

	public void setLvclId(BigInteger lvclId) {
		this.lvclId = lvclId;
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

	public String getLvclImgUrl() {
		return this.lvclImgUrl;
	}

	public void setLvclImgUrl(String lvclImgUrl) {
		this.lvclImgUrl = lvclImgUrl;
	}

	public String getLvclMake() {
		return this.lvclMake;
	}

	public void setLvclMake(String lvclMake) {
		this.lvclMake = lvclMake;
	}

	public String getLvclName() {
		return this.lvclName;
	}

	public void setLvclName(String lvclName) {
		this.lvclName = lvclName;
	}

	public String getLvclType() {
		return this.lvclType;
	}

	public void setLvclType(String lvclType) {
		this.lvclType = lvclType;
	}

}