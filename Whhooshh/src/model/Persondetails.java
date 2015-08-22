package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the persondetails database table.
 * 
 */
@Entity
@Table(name="persondetails")

public class Persondetails implements Serializable {
	private static final long serialVersionUID = 1L;
	private int personId;
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String contactNumber;
	private String country;
	private String dob;
	private String gender;
	private String personName;
	private String personRegistration;
	private String personType;
	private String pincode;
	private String salt;
	private String state;
	private String username;
	private String userpassword;

	public Persondetails() {
	}


	@Id
	@SequenceGenerator(name="PERSONDETAILS_PERSONID_GENERATOR", sequenceName="KEYGEN")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSONDETAILS_PERSONID_GENERATOR")
	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}


	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}


	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	public String getDob() {
		return this.dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}


	public String getPersonRegistration() {
		return this.personRegistration;
	}

	public void setPersonRegistration(String personRegistration) {
		this.personRegistration = personRegistration;
	}


	public String getPersonType() {
		return this.personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}


	public String getPincode() {
		return this.pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getUserpassword() {
		return this.userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

}