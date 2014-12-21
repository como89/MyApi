package ca.como89.myapi.api;

import java.util.Map;


public class TableData {

	private ApiResponse response;
	private Map<String,Object> mapValue;
	
	public TableData(ApiResponse response, Map<String,Object> mapValue){
		this.response = response;
		this.mapValue = mapValue;
	}
	
	/**
	 * This method will return the values that you want in MapList.
	 * @return Map<String,Object> - the MapList.
	 */
	public Map<String,Object> getMapValue(){
		return mapValue;
	}
	
	/**
	 * This method will return the apiResponse.
	 * @return ApiResponse, SUCCESS - if the operation success. ERROR, if a sql error happen. MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 */
	public ApiResponse getResponse(){
		return response;
	}
}
