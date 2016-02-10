package exception;

public class SomeThingNotRightException extends Exception {
	private static final long serialVersionUID = 1997753363232807009L;

	public SomeThingNotRightException() {
	}

	public SomeThingNotRightException(String message) {
		super(message);
	}

	public SomeThingNotRightException(Throwable cause) {
		super(cause);
	}

	public SomeThingNotRightException(String message, Throwable cause) {
		super(message, cause);
	}

	public SomeThingNotRightException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
