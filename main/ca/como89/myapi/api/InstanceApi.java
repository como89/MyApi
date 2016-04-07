package ca.como89.myapi.api;

import java.sql.SQLException;
import java.util.HashMap;

import ca.como89.myapi.api.ApiDatabase.DatabaseType;
import ca.como89.myapi.core.nosql.MongoDB;
import ca.como89.myapi.core.sql.Mysql;
import ca.como89.myapi.core.sql.SQLite;

public class InstanceApi {
	
	private InstanceApi() {}

	private static HashMap<ApiDatabase, MyApi> listApi = new HashMap<>();
	
	/**
	 * Method to create a new instance of the api.
	 * @param apiDatabase - an instance of the database api.
	 * @return a new instance of the MyApi api, return null if not created.
	 */
	public static MyApi createInstance(ApiDatabase apiDatabase){
		DatabaseType dt = apiDatabase.databaseType;
		MyApi myapi = null;
		switch(dt) {
		case MYSQL:
			myapi = new Mysql(apiDatabase);
			break;
		case NO_SQL_MONGODB:
			myapi = new MongoDB(apiDatabase);
			break;
		case SQLITE:
			myapi = new SQLite(apiDatabase);
			break;
		}
		listApi.put(apiDatabase, myapi);
		return myapi;
	}
	
	/**
	 * Method to get a instance of the api from the name.
	 * @param projectName - The projectName.
	 * @return the instance of the projectName. If the projectName, doesn't exist, it return null.
	 */
	public static MyApi getInstance(String projectName){
		ApiDatabase apiDatabase = getApiDatabaseByName(projectName);
		return listApi.get(apiDatabase);
	}
	
	/**
	 * Method to remove the instance from the library.
	 * @param projectName - The projectName.
	 */
	public static MyApi removeInstance(String projectName){
		ApiDatabase apiDatabase = getApiDatabaseByName(projectName);
		MyApi myapi = listApi.remove(apiDatabase);
		try {
			if(myapi != null && myapi.isConnect() == ApiResponse.DATABASE_CONNECTED){
				myapi.disconnect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myapi;
	}
	
	private static ApiDatabase getApiDatabaseByName(String projectName) {
		for(ApiDatabase apiDatabase : listApi.keySet()) {
			if(apiDatabase.projectName.equals(projectName)) {
				return apiDatabase;
			}
		}
		return null;
	}
}
