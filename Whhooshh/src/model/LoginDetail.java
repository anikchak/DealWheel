package model;

import java.io.Serializable;

import javax.jws.soap.InitParam;
import javax.persistence.*;


/**
 * The persistent class for the loginDetails database table.
 * 
 */
@Entity
@Table(name="loginDetails")

public class LoginDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOGINDETAILS_ID_GENERATOR", sequenceName="SEQ", initialValue = 1001)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOGINDETAILS_ID_GENERATOR")
	private int id;

	private String password;

	private String username;

	public LoginDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}