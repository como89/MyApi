package ca.como89.myapi;

import java.sql.SQLException;
import java.util.HashMap;

import ca.como89.myapi.api.ApiResponse;
import ca.como89.myapi.api.MyApi;

public class MyApiLib {

	private static HashMap<String, MyApi> listApi = new HashMap<>();
	
	public static MyApi createInstance(String projectName){
		MyApi myapi = new DataManager();
		listApi.put(projectName, myapi);
		return myapi;
	}
	
	public static MyApi getInstance(String projectName){
		return listApi.get(projectName);
	}
	
	public static void removeInstance(String projectName){
		MyApi myapi = listApi.remove(projectName);
		try {
			if(myapi != null && myapi.isConnect() == ApiResponse.DATABASE_CONNECTED){
				myapi.disconnect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
