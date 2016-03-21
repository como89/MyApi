package ca.como89.myapi.api.exceptions;

public class IllegalTypeException extends Exception {
	private static final long serialVersionUID = 3354511878747838018L;

	private String msg;
	
	public IllegalTypeException(String msg){
		this.msg = msg;
	}
	
	@Override
	public String getMessage(){
		return msg;
	}
}
