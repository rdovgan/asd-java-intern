package team.asd.tutorials.exceptions;

public class WrongArgumentException extends RuntimeException {
	public WrongArgumentException(String message) {
		super(message);
	}

	public WrongArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
}