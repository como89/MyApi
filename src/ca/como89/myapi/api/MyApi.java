package ca.como89.myapi.api;

import java.sql.SQLException;
import java.util.List;

import ca.como89.myapi.api.conditions.Condition;
import ca.como89.myapi.api.exceptions.LengthTableException;
import ca.como89.myapi.api.queries.InsertQuery;
import ca.como89.myapi.api.queries.SelectQuery;
import ca.como89.myapi.api.queries.UpdateQuery;
import ca.como89.myapi.api.sql.Columns;

/**
 * The MyApi library. (MySQL, SQLite and NOSQL MongoDB library)
 * @author como89
 * @version 2.0
 * @since March 12, 2016
 *
 */
public interface MyApi {
	
	/**
	 * You can get the projectName that this api is linked to.
	 * @return - the project name.
	 */
	public String getProjectName();
	
	/**
	 * This method will connect the library with the mysql server, SQLite file or noSQL mongoDB.
	 * @return ApiResponse, 
	 * SUCCESS - if the operation success. 
	 * ERROR, if a sql error happen. 
	 * MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * MYAPI_NOT_INITIALISE, if the library is not initialise correctly.
	 * @throws ClassNotFoundException - When not found the class of jdbc.
	 * @throws SQLException - SQL problems, connection not found.
	 */
	public ApiResponse connect()throws ClassNotFoundException, SQLException; 
	
	/**
	 * This method will disconnect the library with the mysql server, SQLite file or noSQL mongoDB.
	 * @return ApiResponse, 
	 * SUCCESS - if the operation success. 
	 * ERROR, if a sql error happen. 
	 * MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * MYAPI_NOT_INITIALISE, if the library is not initialise correctly.
	 * @throws SQLException - SQL problems, connection not found.
	 */
	public ApiResponse disconnect() throws SQLException;

	/**
	 * This method will check if the library is connected with the mysql server, SQLite file or noSQL mongoDB.
	 * @return ApiResponse, 
	 * MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * MYAPI_NOT_INITIALISE, if the library is not initialise correctly.
	 * DATABASE_CONNECTED, if the library is connected to the database.
	 * @throws SQLException - SQL problems, connection not found.
	 */
	public ApiResponse isConnect() throws SQLException;
	
	/**
	 * This method will create a Table with the name and the columns.
	 * @param tableName - The name of the table.
	 * @param listColumns - The list of the Columns.
	 * @param existCondition - If you want to verify if the table exist.
	 * @return ApiResponse, 
	 * SUCCESS - if the operation success. 
	 * ERROR, if a sql error happen. 
	 * MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * MYAPI_NOT_INITIALISE, if the library is not initialise correctly.
	 * @throws IllegalArgumentException - If the parameters are null.
	 */
	public ApiResponse createTable(String tableName,List<Columns> listColumns, boolean existCondition) throws IllegalArgumentException;
	
	/**
	 * This method will delete a Table with the name.
	 * @param tableName - The name of the table.
	 * @return ApiResponse, 
	 * SUCCESS - if the operation success. 
	 * ERROR, if a sql error happen. 
	 * MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * MYAPI_NOT_INITIALISE, if the library is not initialise correctly.
	 * @throws IllegalArgumentException - If the parameter is null.
	 */
	public ApiResponse deleteTable(String tableName) throws IllegalArgumentException;
	/**
	 * This method will insert values with what you have specified in the tableProperties.
	 * @param tableProperties - The TableProperties object.
	 * @return ApiResponse, 
	 * SUCCESS - if the operation success. 
	 * ERROR, if a sql error happen. 
	 * MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * MYAPI_NOT_INITIALISE, if the library is not initialise correctly.
	 * @throws IllegalArgumentException - If the parameter is null.
	 * @throws LengthTableException - if the tables are not the same lenght.
	 * @throws SQLException - SQL problems, connection not found.
	 */
	public ApiResponse sendQuery(InsertQuery insertQuery) throws SQLException;
	
	public ApiResponse sendQuery(UpdateQuery updateQuery) throws SQLException;
	
	public ApiResponse sendQuery(SelectQuery selectQuery) throws SQLException;
	
	/**
	 * This method will delete rows in the tableName and with the condition.
	 * @param tableName - The table name.
	 * @param condition - The condition object.
	 * @return ApiResponse, 
	 * SUCCESS - if the operation success. 
	 * ERROR, if a sql error happen. 
	 * MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * MYAPI_NOT_INITIALISE, if the library is not initialise correctly.
	 * @throws IllegalArgumentException - If a parameter is null.
	 */
	public ApiResponse deleteRow(String tableName,Condition condition) throws IllegalArgumentException;
	/**
	 * This method will add columns in the tableName.
	 * @param tableName - The table name.
	 * @param listColumns - List of the columns.
	 * @param hisIgnore - If true, do a copy of the table and restore data, after the modification.
	 * @return ApiResponse, 
	 * SUCCESS - if the operation success. 
	 * ERROR, if a sql error happen. 
	 * MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * MYAPI_NOT_INITIALISE, if the library is not initialise correctly.
	 * @throws IllegalArgumentException - If a parameter is null.
	 */
	public ApiResponse addColumns(String tableName, List<Columns> listColumns, boolean hisIgnore) throws IllegalArgumentException;
	
	/**
	 * This method will change the column in parameter.
	 * @param tableName - The table name.
	 * @param oldColumnName - The old column name.
	 * @param newColumn - The new column.
	 * @param hisIgnore - If true, do a copy of the table and restore data, after the modification.
	 * @return ApiResponse, 
	 * SUCCESS - if the operation success. 
	 * ERROR, if a sql error happen. 
	 * MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * MYAPI_NOT_INITIALISE, if the library is not initialise correctly.
	 * @throws IllegalArgumentException - If a parameter is null.
	 */
	public ApiResponse changeColumn(String tableName, String oldColumnName, Columns newColumn, boolean hisIgnore) throws IllegalArgumentException;
	
	/**
	 * This method will remove the column in parameter.
	 * @param tableName - The table name.
	 * @param columnName - The column name.
	 * @param hisIgnore - If true, do a copy of the table and restore data, after the modification.
	 * @return ApiResponse, 
	 * SUCCESS - if the operation success. 
	 * ERROR, if a sql error happen. 
	 * MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * MYAPI_NOT_INITIALISE, if the library is not initialise correctly.
	 * @throws IllegalArgumentException - If a parameter is null.
	 */
	public ApiResponse removeColumn(String tableName, String columnName, boolean hisIgnore) throws IllegalArgumentException;
	
	/**
	 * This method will check if the table exist in parameter.
	 * @param tableName - The table name.
	 * @return TableData - You can get the result and get response from the api.
	 * @throws IllegalArgumentException - If a parameter is null.
	 * @deprecated - Use the new method. This method will be remove in future update.
	 */
	@Deprecated
	public TableData checkIfTableExist(String tableName) throws IllegalArgumentException;
	
	/**
	 * This method will check if the column exist in parameter.
	 * @param tableName - The table name.
	 * @param columnName - The column name.
	 * @return TableData - You can get the result and get response from the api.
	 * @throws IllegalArgumentException - If a parameter is null.
	 * @deprecated - Use the new method. This method will be remove in future update.
	 */
	@Deprecated
	public TableData checkIfColumnExist(String tableName, String columnName) throws IllegalArgumentException;
}
