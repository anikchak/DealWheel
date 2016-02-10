package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the payment database table.
 * 
 */
@Entity
@Table(name="payment")
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAYMENT_PYMTID_GENERATOR", sequenceName="KEYSEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENT_PYMTID_GENERATOR")
	@Column(name="PYMT_ID")
	private String pymtId;

	@Column(name="PYMT_AMOUNT")
	private int pymtAmount;

	@Column(name="PYMT_STATUS")
	private String pymtStatus;

	@Column(name="PYMT_TXN_ID")
	private String pymtTxnId;

	@Column(name="PYMT_TYPE")
	private String pymtType;

	public Payment() {
	}

	public String getPymtId() {
		return this.pymtId;
	}

	public void setPymtId(String pymtId) {
		this.pymtId = pymtId;
	}

	public int getPymtAmount() {
		return this.pymtAmount;
	}

	public void setPymtAmount(int pymtAmount) {
		this.pymtAmount = pymtAmount;
	}

	public String getPymtStatus() {
		return this.pymtStatus;
	}

	public void setPymtStatus(String pymtStatus) {
		this.pymtStatus = pymtStatus;
	}

	public String getPymtTxnId() {
		return this.pymtTxnId;
	}

	public void setPymtTxnId(String pymtTxnId) {
		this.pymtTxnId = pymtTxnId;
	}

	public String getPymtType() {
		return this.pymtType;
	}

	public void setPymtType(String pymtType) {
		this.pymtType = pymtType;
	}

}