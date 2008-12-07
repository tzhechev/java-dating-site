package db.exceptions;

public class DependentObjectsFoundException extends Exception {
	private static final long serialVersionUID = 4481971278948588867L;

	public DependentObjectsFoundException() {
		super();
	}

	public DependentObjectsFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DependentObjectsFoundException(String message) {
		super(message);
	}

	public DependentObjectsFoundException(Throwable cause) {
		super(cause);
	}

}
