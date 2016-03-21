package ca.como89.myapi.api;

import java.util.Map;

@Deprecated
public class TableData {

	private ApiResponse response;
	private Map<Integer,Object> mapValues;
	private boolean result;
	
	public TableData(ApiResponse response, Map<Integer,Object> mapValues){
		this.response = response;
		this.mapValues = mapValues;
		this.result = false;
	}
	
	public TableData(ApiResponse response, boolean result){
		this.response = response;
		this.result = result;
	}
	
	/**
	 * This method return a map values from the select request.
	 * @return a Map<Integer,Object>
	 */
	public Map<Integer,Object> getMapValue(){
		return mapValues;
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
