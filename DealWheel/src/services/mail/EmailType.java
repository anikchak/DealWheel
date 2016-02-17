package services.mail;

public enum EmailType {
	VERIFY_VENDOR("VERIFY_VENDOR"),
	VERIFY_USER("VERIFY_USER"),
	CANCEL_BOOKING_BY_USER("CANCEL_BOOKING_BY_USER"), 
	CANCEL_BOOKING_BY_VENDOR("CANCEL_BOOKING_BY_VENDOR"),
	CONFIRM_BOOKING_TO_USER("CONFIRM_BOOKING_TO_USER"),
	CONFIRM_BOOKING_TO_VENDOR("CONFIRM_BOOKING_TO_VENDOR"), 
	FORGOT_PASSWORD("FORGOT_PASSWORD");
	
	private final String value;       

    private EmailType(String s) {
    	value = s;
    }
    
    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : value.equals(otherName);
    }

    public String toString() {
       return this.value;
    }

}
