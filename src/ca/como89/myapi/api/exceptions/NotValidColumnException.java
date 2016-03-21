package ca.como89.myapi.api.exceptions;

public class NotValidColumnException extends Exception {

	
	private String msg;
	
	public NotValidColumnException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return msg;
	}

	private static final long serialVersionUID = 2913091235930356480L;
}