package servlets.exceptions;

public class InvalidProfileDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1014942034922936640L;
	
	public InvalidProfileDataException(){
		super();
	}
	
	public InvalidProfileDataException(String msg){
		super(msg);
	}
}
