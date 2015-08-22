package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bikelookup database table.
 * 
 */
@Entity
@Table(name="bikelookup")

public class Bikelookup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BIKELOOKUP_BIKESEQ_GENERATOR", sequenceName="BIKESEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BIKELOOKUP_BIKESEQ_GENERATOR")
	private int bikeSeq;

	private String bikeName;

	private String company;

	public Bikelookup() {
	}

	public int getBikeSeq() {
		return this.bikeSeq;
	}

	public void setBikeSeq(int bikeSeq) {
		this.bikeSeq = bikeSeq;
	}

	public String getBikeName() {
		return this.bikeName;
	}

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}