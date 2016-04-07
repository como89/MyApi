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
import ca.como89.myapi.api.conditions.Condition;
import ca.como89.myapi.api.conditions.Operator;
import ca.como89.myapi.api.conditions.TypeCondition;
import ca.como89.myapi.api.exceptions.NotValidColumnException;
import ca.como89.myapi.api.queries.CountRowsQuery;
import ca.como89.myapi.api.queries.InsertQuery;
import ca.como89.myapi.api.queries.PrefQuery;
import ca.como89.myapi.api.queries.ResultObjects;
import ca.como89.myapi.api.queries.SelectQuery;
import ca.como89.myapi.api.queries.UpdateQuery;
import ca.como89.myapi.api.ApiDatabase.DatabaseType;
import ca.como89.myapi.api.ApiResponse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestQueries {

	static  {
		ApiDatabase api = new ApiDatabase();
		api.projectName = "Test";
		api.databaseType = DatabaseType.MYSQL;
		
		api.host = "localhost";
		api.port = 3306;
		api.username = "root";
		api.password = "";
		api.databaseName = "TestDatabase";
		
		InstanceApi.createInstance(api);
	}
	
	@Test
	public void testInsertQuery() {
		MyApi myapi = InstanceApi.getInstance("Test");
		myapi.connect();
		
		InsertQuery insertQuery = new InsertQuery();
		
		try {
			insertQuery.addColumn("Pseudo", "como89");
			insertQuery.addColumn("NBCoins", 156);
		} catch (NotValidColumnException e) {
			e.printStackTrace();
		}
		
		insertQuery.setTableName("Players");
		ApiResponse apiResponse = null;
		try {
			apiResponse = myapi.sendQuery(insertQuery);
			myapi.disconnect();
		} catch (IllegalArgumentException | SQLException e) {
			e.printStackTrace();
		}
		assertEquals(apiResponse, ApiResponse.SUCCESS);
	}
	
	@Test
	public void testUpdateQuery() {
		MyApi myapi = InstanceApi.getInstance("Test");
		myapi.connect();
		
		UpdateQuery updateQuery = new UpdateQuery();
		
		updateQuery.setTableName("Players");
		
		try {
			updateQuery.addColumn("NBCoins",1150);
		} catch (NotValidColumnException e) {
			e.printStackTrace();
		}
		
		Condition condition = new Condition();
		condition.columnName = "Pseudo";
		condition.value = "como89";
		condition.typeCondition = TypeCondition.EQUALS;
		
		updateQuery.addCondition(condition);
		ApiResponse apiResponse = null;
		try {
			apiResponse = myapi.sendQuery(updateQuery);
			myapi.disconnect();
		} catch (IllegalArgumentException | SQLException e) {
			e.printStackTrace();
		}
		assertEquals(apiResponse, ApiResponse.SUCCESS);
	}
	
	@Test
	public void testSelectQuery() {
		MyApi myapi = InstanceApi.getInstance("Test");
		myapi.connect();
		
		SelectQuery selectQuery = new SelectQuery();
		try {
			selectQuery.addColumn("Pseudo");
		} catch (NotValidColumnException e) {
			e.printStackTrace();
		}
		selectQuery.setTableName("Players");
		
		Condition condition = new Condition();
		condition.columnName = "NBCoins";
		condition.value = 200;
		condition.typeCondition = TypeCondition.GREATER;
		
		selectQuery.addCondition(condition);
		
		ApiResponse apiResponse = null;
		try {
			apiResponse = myapi.sendQuery(selectQuery);
			myapi.disconnect();
		} catch (IllegalArgumentException | SQLException e) {
			e.printStackTrace();
		}
		if(apiResponse == ApiResponse.SUCCESS) {
		ResultObjects resultObject = selectQuery.getResultObjects();
		ArrayList<Object> listObject = resultObject.getListObjectFromColumn("Pseudo");
		assertEquals(listObject.size(),2);
		}
	}
	
	@Test
	public void testMultiConditionSelect() {
		MyApi myapi = InstanceApi.getInstance("Test");
		myapi.connect();
		
		SelectQuery selectQuery = new SelectQuery();
		try {
			selectQuery.addColumn("ID");
		} catch (NotValidColumnException e) {
			e.printStackTrace();
		}
		selectQuery.setTableName("Players");
		
		PrefQuery prefQuery = selectQuery.getPreferences();
		prefQuery.multiCondition = true;
		prefQuery.operator = new Operator[] {Operator.AND};
		
		Condition condition = new Condition();
		condition.columnName = "NBCoins";
		condition.value = 200;
		condition.typeCondition = TypeCondition.GREATER;
		
		selectQuery.addCondition(condition);
		
		Condition condition2 = new Condition();
		condition2.columnName = "Pseudo";
		condition2.value = "como89";
		condition2.typeCondition = TypeCondition.EQUALS;
		
		selectQuery.addCondition(condition2);
		
		ApiResponse apiResponse = null;
		try {
			apiResponse = myapi.sendQuery(selectQuery);
			myapi.disconnect();
		} catch (IllegalArgumentException | SQLException e) {
			e.printStackTrace();
		}
		if(apiResponse == ApiResponse.SUCCESS) {
		ResultObjects resultObject = selectQuery.getResultObjects();
		ArrayList<Object> listObject = resultObject.getListObjectFromColumn("ID");
		assertEquals(listObject.size(),1);
		}
	}
	
	@Test
	public void testCountRowsQuery() {
		MyApi myapi = InstanceApi.getInstance("Test");
		myapi.connect();
		
		CountRowsQuery countRow = new CountRowsQuery();
		countRow.setTableName("Players");
		
		Condition condition = new Condition();
		condition.columnName = "Pseudo";
		condition.value = "como89";
		condition.typeCondition = TypeCondition.EQUALS;
		
		countRow.addCondition(condition);
		
		ApiResponse apiResponse = null;
		try {
			apiResponse = myapi.sendQuery(countRow);
			myapi.disconnect();
		} catch (IllegalArgumentException | SQLException e) {
			e.printStackTrace();
		}
		if(apiResponse == ApiResponse.SUCCESS) {
			int nbRow = countRow.getNbRows();
			assertEquals(nbRow,1);
		}
	}

}
