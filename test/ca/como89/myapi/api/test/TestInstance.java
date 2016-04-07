package ca.como89.myapi.api.test;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ca.como89.myapi.api.ApiDatabase;
import ca.como89.myapi.api.ApiDatabase.DatabaseType;
import ca.como89.myapi.api.InstanceApi;
import ca.como89.myapi.api.MyApi;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestInstance {

	@Test
	public void testCreate() {
		ApiDatabase api = new ApiDatabase();
		api.projectName = "Test";
		api.databaseType = DatabaseType.MYSQL;
		
		api.host = "localhost";
		api.port = 3306;
		api.username = "test";
		api.password = "testing";
		api.databaseName = "TestDatabase";
		
		MyApi myapi = InstanceApi.createInstance(api);
		
		assertNotNull(myapi);
	}
	
	@Test
	public void testGet() {
		MyApi myapi = InstanceApi.getInstance("Test");
		
		assertNotNull(myapi);
	}
	
	@Test
	public void testRemove() {
		InstanceApi.removeInstance("Test");
		MyApi myapi = InstanceApi.getInstance("Test");
		
		assertNull(myapi);
	}

}
