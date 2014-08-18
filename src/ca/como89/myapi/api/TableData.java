package ca.como89.myapi.api;


public class TableData {

	private ApiResponse response;
	private Object[] value;
	
	public TableData(ApiResponse response, Object [] value){
		this.response = response;
		this.value = value;
	}
	
	/**
	 * This method will return the values that you want.
	 * @return Object[] - return a table of Object.
	 */
	public Object[] getValues(){
		return value;
	}
	
	/**
	 * This method will return the apiResponse.
	 * @return ApiResponse, SUCCESS - if the operation success. ERROR, if a sql error happen. MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 */
	public ApiResponse getResponse(){
		return response;
	}
}
