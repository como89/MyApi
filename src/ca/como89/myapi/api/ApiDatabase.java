package ca.como89.myapi.api;

public class ApiDatabase {
	
	public String path,projectName,host,username,password,databaseName;
	public int port;
	DatabaseType databaseType;

	public ApiDatabase(String projectName, String path) {
		this.projectName = projectName;
		this.path = path;
		this.databaseType = DatabaseType.SQLITE;
	}
	
	public ApiDatabase(String projectName, String host, int port, String username, String password, String databaseName) {
		this.projectName = projectName;
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.databaseName = databaseName;
		this.databaseType = DatabaseType.MYSQL;
	}
	
	public ApiDatabase(String host, int port, String databaseName) {
		this.host = host;
		this.port = port;
		this.databaseName = databaseName;
		this.databaseType = DatabaseType.NO_SQL_MONGODB;
	}

	public enum DatabaseType {
		MYSQL,
		SQLITE,
		NO_SQL_MONGODB;
	}	
}
