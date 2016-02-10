package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the users_temp database table.
 * 
 */
@Entity
@Table(name="users_temp")
@NamedQuery(name="UsersTemp.findAll", query="SELECT u FROM UsersTemp u")
public class UsersTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private String userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED")
	private Date lastUpdated;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name="secret_code")
	private String secretCode;

	@Column(name="USER_DATE_OF_BIRTH")
	private String userDateOfBirth;

	@Column(name="USER_EMAIL")
	private String userEmail;

	@Column(name="USER_GENDER")
	private String userGender;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_PRIMARY_CONTACT")
	private BigInteger userPrimaryContact;

	@Column(name="USER_SECONDARY_CONTACT")
	private BigInteger userSecondaryContact;

	@Column(name="USER_TYPE")
	private String userType;

	public UsersTemp() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getSecretCode() {
		return this.secretCode;
	}

	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}

	public String getUserDateOfBirth() {
		return this.userDateOfBirth;
	}

	public void setUserDateOfBirth(String userDateOfBirth) {
		this.userDateOfBirth = userDateOfBirth;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserGender() {
		return this.userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigInteger getUserPrimaryContact() {
		return this.userPrimaryContact;
	}

	public void setUserPrimaryContact(BigInteger userPrimaryContact) {
		this.userPrimaryContact = userPrimaryContact;
	}

	public BigInteger getUserSecondaryContact() {
		return this.userSecondaryContact;
	}

	public void setUserSecondaryContact(BigInteger userSecondaryContact) {
		this.userSecondaryContact = userSecondaryContact;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}