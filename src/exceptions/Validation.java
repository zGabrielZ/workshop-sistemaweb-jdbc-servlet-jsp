package exceptions;

public class Validation extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Validation(String msg) {
		super(msg);
	}

}
