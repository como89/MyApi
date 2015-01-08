package ca.como89.myapi.api;

import java.util.Map;


public class TableData {

	private ApiResponse response;
	private Map<String,Object> mapValue;
	private boolean result;
	
	public TableData(ApiResponse response, Map<String,Object> mapValue){
		this.response = response;
		this.mapValue = mapValue;
	}
	
	public TableData(ApiResponse response, boolean result){
		this.response = response;
		this.result = result;
	}
	
	/**
	 * This method will return the values that you want in MapList.
	 * @return Map<String,Object> - the MapList.
	 */
	public Map<String,Object> getMapValue(){
		return mapValue;
	}
	/**
	 * This method will return true, if the method have result.
	 * @return true when have result and false when do not have result.
	 */
	public boolean hasResult(){
		return result;
	}
	
	/**
	 * This method will return the apiResponse.
	 * @return ApiResponse, SUCCESS - if the operation success. ERROR, if a sql error happen. MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 */
	public ApiResponse getResponse(){
		return response;
	}
}
