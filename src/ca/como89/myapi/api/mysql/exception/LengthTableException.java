package ca.como89.myapi.api.mysql.exception;

public class LengthTableException extends Exception{

	private static final long serialVersionUID = -7891756867513641379L;

	private String msg;
	
	public LengthTableException(String msg){
		this.msg = msg;
	}
	
	@Override
	public String getMessage(){
		return msg;
	}
}
