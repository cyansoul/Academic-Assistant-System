package booking.exception;

public class CustomeException extends RuntimeException {

	public CustomeException() {
		super();
	}

	public CustomeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomeException(String message) {
		super(message);
	}

	public CustomeException(Throwable cause) {
		super(cause);
	}
}
