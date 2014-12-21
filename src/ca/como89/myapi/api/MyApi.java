package ca.como89.myapi.api;

import java.sql.SQLException;
import java.util.List;

import ca.como89.myapi.DataManager;
import ca.como89.myapi.api.mysql.Columns;
import ca.como89.myapi.api.mysql.Condition;
import ca.como89.myapi.api.mysql.TableProperties;
import ca.como89.myapi.api.mysql.exception.LengthTableException;

/**
 * The MyApi library. (MySQL library)
 * @author como89
 * @version 1.2
 * @since October 7, 2014
 *
 */
public class MyApi {

	private DataManager datamanager;
	
	/**
	 * The constructor to initialize the API.
	 */
	public MyApi(){
		datamanager = null;
	}
	
	/**
	 * This method will initialize the connection information.
	 * @param host - The Mysql server host.
	 * @param port - The Mysql server port.
	 * @param userName - The username of mysql server.
	 * @param password - The password of the username.
	 * @param databaseName - The database name.
	 */
	public void init(String host,int port,String userName,String password,String databaseName){
		if(datamanager == null){
		datamanager = new DataManager(host,port,userName,password,databaseName);
		}
	}
	/**
	 * This method will connect the library with the mysql server.
	 * @throws ClassNotFoundException - When not found the class of jdbc.
	 * @throws SQLException - SQL problems, connection not found.
	 */
	public void connect() throws ClassNotFoundException, SQLException{
		datamanager.connect();
	}
	
	/**
	 * This method will disconnect the library with the mysql server.
	 * @throws SQLException - SQL problems, connection not found.
	 */
	public void disconnect() throws SQLException{
		datamanager.disconnect();
	}
	
	/**
	 * This method will create a Table with the name and the columns.
	 * @param tableName - The name of the table.
	 * @param listcolumns - The list of the Columns.
	 * @param existCondition - If you want to verify if the table exist.
	 * @return ApiResponse, SUCCESS - if the operation success. ERROR, if a sql error happen. MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * @throws IllegalArgumentException - If the parameters are null.
	 */
	public ApiResponse createTable(String tableName,List<Columns> listcolumns, boolean existCondition) throws IllegalArgumentException{
		return datamanager.createTable(tableName, listcolumns, existCondition);
	}
	/**
	 * This method will delete a Table with the name.
	 * @param tableName - The name of the table.
	 * @return ApiResponse, SUCCESS - if the operation success. ERROR, if a sql error happen. MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * @throws IllegalArgumentException - If the parameter is null.
	 */
	public ApiResponse deleteTable(String tableName) throws IllegalArgumentException{
		return datamanager.deleteTable(tableName);
	}
	/**
	 * This method will insert values with what you have specified in the tableProperties.
	 * @param tableProperties - The TableProperties object.
	 * @return ApiResponse, SUCCESS - if the operation success. ERROR, if a sql error happen. MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * @throws IllegalArgumentException - If the parameter is null.
	 * @throws LengthTableException - if the tables are not the same lenght.
	 * @throws SQLException - SQL problems, connection not found.
	 */
	public ApiResponse insertValues(TableProperties tableProperties) throws IllegalArgumentException, LengthTableException, SQLException{
		return datamanager.insertValues(tableProperties);
	}
	
	/**
	 * This method will update values with what you have specified in the tableProperties and with the condition.
	 * @param  tableProperties - The tableProperties object.
	 * @param  condition - The Condition object.
	 * @return ApiResponse, SUCCESS - if the operation success. ERROR, if a sql error happen. MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * @throws LengthTableException - if the tables are not the same lenght.
	 * @throws IllegalArgumentException - If the parameter is null.
	 */
	public ApiResponse updateValues(TableProperties tableProperties, Condition condition) throws IllegalArgumentException, LengthTableException{
		return datamanager.updateValues(tableProperties, condition);
	}
	/**
	 * This method will select values with what you have specified in the tableProperties and with the condition.
	 * @param tableProperties - The TableProperties object.
	 * @param condition -The condition object. (Not neccessary, can be null)
	 * @return TableData - You can get values and get response from the api.
	 * @throws IllegalArgumentException - If the parameter is null.
	 * @throws LengthTableException - if the tables are not the same lenght.
	 */
	public TableData selectValues(TableProperties tableProperties, Condition condition) throws IllegalArgumentException, LengthTableException{
		return datamanager.selectValues(tableProperties,condition);
	}
	
	/**
	 * This method will count rows with what you have specified in the tableProperties and with the condition.
	 * @param tableProperties - The TableProperties object.
	 * @param condition -The condition object.
	 * @return TableData - You can get values and get response from the api.
	 * @throws IllegalArgumentException - If the parameter is null.
	 * @throws LengthTableException - if the tables are not the same lenght.
	 */
	public TableData countRows(TableProperties tableProperties, Condition condition) throws IllegalArgumentException, LengthTableException{
		return datamanager.countRows(tableProperties, condition);
	}
	
	/**
	 * This method will delete rows in the tableName and with the condition.
	 * @param tableName - The table name.
	 * @param condition - The condition object.
	 * @return ApiResponse, SUCCESS - if the operation success. ERROR, if a sql error happen. MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * @throws IllegalArgumentException - If a parameter is null.
	 */
	public ApiResponse deleteRow(String tableName,Condition condition) throws IllegalArgumentException{
		return datamanager.deleteRow(tableName, condition);
	}
	/**
	 * This method will add columns in the tableName.
	 * @param tableName - The table name.
	 * @param listColumns - List of the columns.
	 * @param hisIgnore - If true, do a copy of the table and restore data, after the modification.
	 * @return ApiResponse, SUCCESS - if the operation success. ERROR, if a sql error happen. MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * @throws IllegalArgumentException - If a parameter is null.
	 */
	public ApiResponse addColumns(String tableName,List<Columns> listColumns, boolean hisIgnore) throws IllegalArgumentException{
		return datamanager.addColumns(tableName, listColumns, hisIgnore);
	}
	
	/**
	 * This method will change the column in parameter.
	 * @param tableName - The table name.
	 * @param oldColumnName - The old column name.
	 * @param newColumn - The new column.
	 * @param hisIgnore - If true, do a copy of the table and restore data, after the modification.
	 * @return ApiResponse, SUCCESS - if the operation success. ERROR, if a sql error happen. MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * @throws IllegalArgumentException - If a parameter is null.
	 */
	public ApiResponse changeColumn(String tableName, String oldColumnName, Columns newColumn, boolean hisIgnore) throws IllegalArgumentException{
		return datamanager.changeColumn(tableName, oldColumnName, newColumn, hisIgnore);
	}
	
	/**
	 * This method will remove the column in parameter.
	 * @param tableName - The table name.
	 * @param columnName - The column name.
	 * @param hisIgnore - If true, do a copy of the table and restore data, after the modification.
	 * @return ApiResponse, SUCCESS - if the operation success. ERROR, if a sql error happen. MYSQL_NOT_CONNECT, if the library is not connected with the mysql server.
	 * @throws IllegalArgumentException - If a parameter is null.
	 */
	public ApiResponse removeColumn(String tableName, String columnName, boolean hisIgnore) throws IllegalArgumentException{
		return datamanager.removeColumn(tableName, columnName, hisIgnore);
	}
}
