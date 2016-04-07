package ca.como89.myapi.api.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ca.como89.myapi.api.ApiDatabase;
import ca.como89.myapi.api.InstanceApi;
import ca.como89.myapi.api.MyApi;
import ca.como89.myapi.api.TypeData;
import ca.como89.myapi.api.ApiDatabase.DatabaseType;
import ca.como89.myapi.api.ApiResponse;
import ca.como89.myapi.api.Columns;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTable {

	/*
	 * Table with these columns
	 * ID | pseudo | nbCoins
	 * 
	 * Will insert a row to test if the table exist.
	 */
	@Test
	public void testCreateTable() throws SQLException {		
		ApiDatabase api = new ApiDatabase();
		api.projectName = "Test";
		api.databaseType = DatabaseType.MYSQL;
		
		api.host = "localhost";
		api.port = 3306;
		api.username = "root";
		api.password = "";
		api.databaseName = "TestDatabase";
		
		MyApi myapi = InstanceApi.createInstance(api);
		
		myapi.connect();
		
		ArrayList<Columns> listColumns = new ArrayList<Columns>();
		
		Columns columnID = new Columns();
		columnID.colomnName = "ID";
		columnID.typeData = TypeData.INT;
		columnID.value = 0;
		columnID.isNull = false;
		columnID.primary = true;
		columnID.autoIncremented = true;
		
		listColumns.add(columnID);
		
		Columns columnPseudo = new Columns();
		columnPseudo.colomnName = "Pseudo";
		columnPseudo.typeData = TypeData.VARCHAR;
		columnPseudo.value = 254;
		
		listColumns.add(columnPseudo);
		
		Columns columnCoins = new Columns();
		columnCoins.colomnName = "NBCoins";
		columnCoins.typeData = TypeData.INT;
		columnCoins.value = 0;
		
		listColumns.add(columnCoins);
		
		ApiResponse apiResponse = myapi.createTable("Players", listColumns, true);
		myapi.disconnect();
		assertEquals(apiResponse, ApiResponse.SUCCESS);
	}
	
//	@Test
//	public void testDeleteTable() throws SQLException {
//		MyApi myapi = InstanceApi.getInstance("Test");
//		myapi.connect();
//		ApiResponse apiResponse = myapi.deleteTable("Players");
//		myapi.disconnect();
//		assertEquals(apiResponse, ApiResponse.SUCCESS);
//	}

}
