package ca.como89.myapi.api;

import java.sql.SQLException;
import java.util.HashMap;

import ca.como89.myapi.api.ApiDatabase.DatabaseType;
import ca.como89.myapi.api.exceptions.NotValidColumnException;
import ca.como89.myapi.api.queries.InsertQuery;
import ca.como89.myapi.core.nosql.MongoDB;
import ca.como89.myapi.core.sql.Mysql;
import ca.como89.myapi.core.sql.SQLite;

public class InstanceApi {
	
	private InstanceApi() {}
	public static void test () {
		String pseudo = "como89";
		String nomServeur = "PvpFac";
		
		MyApi myapi = InstanceApi.createInstance(null);
		
		//Nouvelle m�thode pour l'insert avec MyApi 2.0
		//On cr�e une instance de InsertQuery
		InsertQuery insertQuery = new InsertQuery();
		
		//On ajoute le nom de la colonne et la valeur qu'on veut ins�rer.
		try {
		insertQuery.addColumn("IdAchat",1004);
		insertQuery.addColumn("Pseudo", pseudo);
		insertQuery.addColumn("Serveur", nomServeur);
		insertQuery.setColumn("IdAchat", 1050);
		} catch (NotValidColumnException e) {
			e.printStackTrace();
		}
		
		//On envoit la requ�te.
		try {
			myapi.sendQuery(insertQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
		return myapi;
	}
	
	/**
	 * Method to get a instance of the api from the name.
	 * @param projectName - The projectName.
	 * @return the instance of the projectName. If the projectName, doesn't exist, it return null.
	 */
	public static MyApi getInstance(String projectName){
		return getApiFromName(projectName);
	}
	
	/**
	 * Method to remove the instance from the library.
	 * @param projectName - The projectName.
	 */
	public static void removeInstance(String projectName){
		MyApi myapi = getApiFromName(projectName);
		try {
			if(myapi != null && myapi.isConnect() == ApiResponse.DATABASE_CONNECTED){
				myapi.disconnect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static MyApi getApiFromName(String projectName) {
		for(ApiDatabase apiDatabase : listApi.keySet()) {
			if(apiDatabase.projectName.equals(projectName)) {
				return listApi.get(apiDatabase);
			}
		}
		return null;
	}
}