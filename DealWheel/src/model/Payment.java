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
	@SequenceGenerator(name="PAYMENT_PAYMENTSEQ_GENERATOR", sequenceName="PAYMENT_SEQ_NAME")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENT_PAYMENTSEQ_GENERATOR")
	private String paymentseq;

	private String addedon;

	private String amount;

	@Column(name="bank_ref_num")
	private String bankRefNum;

	private String bankcode;

	private String bookingId;

	@Column(name="card_type")
	private String cardType;

	private String cardcategory;

	private String cardnum;

	private String discount;

	private String email;

	@Column(name="error_message")
	private String errorMessage;

	private String errorcode;

	private String firstname;

	@Column(name="issuing_bank")
	private String issuingBank;

	private String mihpayid;

	@Column(name="name_on_card")
	private String nameOnCard;

	@Column(name="net_amount_debit")
	private String netAmountDebit;

	@Column(name="payment_mode")
	private String paymentMode;

	private String paymentsource;

	@Column(name="pg_type")
	private String pgType;

	private String phone;

	private String productinfo;

	private String status;

	private String txnid;

	private String unmappedstatus;

	public Payment() {
	}

	public String getPaymentseq() {
		return this.paymentseq;
	}

	public void setPaymentseq(String paymentseq) {
		this.paymentseq = paymentseq;
	}

	public String getAddedon() {
		return this.addedon;
	}

	public void setAddedon(String addedon) {
		this.addedon = addedon;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBankRefNum() {
		return this.bankRefNum;
	}

	public void setBankRefNum(String bankRefNum) {
		this.bankRefNum = bankRefNum;
	}

	public String getBankcode() {
		return this.bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardcategory() {
		return this.cardcategory;
	}

	public void setCardcategory(String cardcategory) {
		this.cardcategory = cardcategory;
	}

	public String getCardnum() {
		return this.cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorcode() {
		return this.errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getIssuingBank() {
		return this.issuingBank;
	}

	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}

	public String getMihpayid() {
		return this.mihpayid;
	}

	public void setMihpayid(String mihpayid) {
		this.mihpayid = mihpayid;
	}

	public String getNameOnCard() {
		return this.nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getNetAmountDebit() {
		return this.netAmountDebit;
	}

	public void setNetAmountDebit(String netAmountDebit) {
		this.netAmountDebit = netAmountDebit;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentsource() {
		return this.paymentsource;
	}

	public void setPaymentsource(String paymentsource) {
		this.paymentsource = paymentsource;
	}

	public String getPgType() {
		return this.pgType;
	}

	public void setPgType(String pgType) {
		this.pgType = pgType;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProductinfo() {
		return this.productinfo;
	}

	public void setProductinfo(String productinfo) {
		this.productinfo = productinfo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTxnid() {
		return this.txnid;
	}

	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}

	public String getUnmappedstatus() {
		return this.unmappedstatus;
	}

	public void setUnmappedstatus(String unmappedstatus) {
		this.unmappedstatus = unmappedstatus;
	}

}