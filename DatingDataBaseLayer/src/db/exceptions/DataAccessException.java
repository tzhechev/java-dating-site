/**
 * 
 */
package db.exceptions;

public class DataAccessException extends RuntimeException {

	private static final long serialVersionUID = 1101454226626418885L;

	public DataAccessException(Throwable cause) {
		super("Error while accessing database.", cause);
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(String message) {
		super(message);
	}

}
