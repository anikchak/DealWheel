package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the listed_vehicle database table.
 * 
 */
@Entity
@Table(name="listed_vehicle")
@NamedQuery(name="ListedVehicle.findAll", query="SELECT l FROM ListedVehicle l")
public class ListedVehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LISTED_VEHICLE_LVCLID_GENERATOR", sequenceName="KEYSEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LISTED_VEHICLE_LVCLID_GENERATOR")
	@Column(name="LVCL_ID")
	private String lvclId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED")
	private Date lastUpdated;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name="LVCL_MAKE")
	private String lvclMake;

	@Column(name="LVCL_NAME")
	private String lvclName;

	@Column(name="LVCL_TYPE")
	private String lvclType;

	public ListedVehicle() {
	}

	public String getLvclId() {
		return this.lvclId;
	}

	public void setLvclId(String lvclId) {
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