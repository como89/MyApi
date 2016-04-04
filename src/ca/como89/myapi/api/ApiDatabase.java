package ca.como89.myapi.api;

/**
 * This class is use to initialize all information about the database.
 *
 */
public class ApiDatabase {
	/**
	 * The path of the SQLite file.
	 */
	public String path,
	/**
	 * The projectName for a reference.
	 */
	projectName,
	/**
	 * The host of the database.
	 */
	host,
	/**
	 * The user name of the database.
	 */
	username,
	/**
	 * The password of the database.
	 */
	password,
	/**
	 * The name of the database.
	 */
	databaseName;
	/**
	 * The port of the database.
	 */
	public int port;
	/**
	 * The type of the database, see @DatabaseType
	 */
	public DatabaseType databaseType;

	/**
	 * Default constructor
	 */
	public ApiDatabase() {
		
	}

	/**
	 * The type of the database.
	 *
	 */
	public enum DatabaseType {
		/**
		 * If you want to use Mysql, choose this one.
		 */
		MYSQL,
		/**
		 * If you want to use SQLite, choose this one.
		 */
		SQLITE,
		/**
		 * If you want to use MongoDB, choose this one.
		 */
		NO_SQL_MONGODB;
	}	
}
