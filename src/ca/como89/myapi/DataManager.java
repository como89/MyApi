package ca.como89.myapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.como89.myapi.api.ApiResponse;
import ca.como89.myapi.api.TableData;
import ca.como89.myapi.api.mysql.Columns;
import ca.como89.myapi.api.mysql.Condition;
import ca.como89.myapi.api.mysql.TableProperties;
import ca.como89.myapi.api.mysql.exception.LengthTableException;

public class DataManager {

	private Connection connect;
	private Connexion connexion;

	public DataManager(String host, int port, String userName, String password,
			String databaseName) {
		this.connexion = new Connexion(host, port, userName, password,
				databaseName);
	}

	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://" + connexion.host
				+ ":" + connexion.port + "/" + connexion.databaseName,
				connexion.username, connexion.password);
	}

	public void disconnect() throws SQLException {
		if (connect != null) {
			connect.close();
		}
	}
	
	public ApiResponse addColumns(String tableName,List<Columns> listColumns, boolean hisIgnore) throws IllegalArgumentException{
		Statement stat = null;
		try{
			if(connect == null || connect.isClosed())
				return ApiResponse.MYSQL_NOT_CONNECT;
			if(tableName == null || listColumns == null)
				throw new IllegalArgumentException("An argument is null.");
			stat = connect.createStatement();
		String columnString = createColumns(listColumns);
		stat.execute("ALTER " + (hisIgnore?"IGNORE ":"") + "TABLE " + tableName + " ADD COLUMN (" + columnString + ")");
		}
		catch(SQLException e){
			e.printStackTrace();
			return ApiResponse.ERROR;
		}
		return ApiResponse.SUCCESS;
	}
	
	public ApiResponse changeColumn(String tableName,String oldColumnName, Columns newColumn, boolean hisIgnore) throws IllegalArgumentException{
		Statement stat = null;
		try{
			if(connect == null || connect.isClosed())
				return ApiResponse.MYSQL_NOT_CONNECT;
			if(tableName == null || newColumn == null)
				throw new IllegalArgumentException("An argument is null.");
			stat = connect.createStatement();
			List<Columns> listColumns = new ArrayList<Columns>();
			listColumns.add(newColumn);
		String columnString = createColumns(listColumns);
		stat.execute("ALTER " + (hisIgnore?"IGNORE ":"") + "TABLE " + tableName + " CHANGE COLUMN " + oldColumnName + " " + columnString);
		}
		catch(SQLException e){
			e.printStackTrace();
			return ApiResponse.ERROR;
		}
		return ApiResponse.SUCCESS;
	}
	
	public ApiResponse removeColumn(String tableName,String columnName,boolean hisIgnore) throws IllegalArgumentException{
		Statement stat = null;
		try{
			if(connect == null || connect.isClosed())
				return ApiResponse.MYSQL_NOT_CONNECT;
			if(tableName == null || columnName == null)
				throw new IllegalArgumentException("An argument is null.");
			stat = connect.createStatement();
			stat.execute("ALTER " + (hisIgnore?"IGNORE ":"") + "TABLE " + tableName + " DROP COLUMN " + columnName);
		}
		catch(SQLException e){
			e.printStackTrace();
			return ApiResponse.ERROR;
		}
		return ApiResponse.SUCCESS;
	}

	public ApiResponse deleteRow(String tableName, Condition condition) throws IllegalArgumentException{
		Statement stat = null;
		try {
			if(connect == null || connect.isClosed())
				return ApiResponse.MYSQL_NOT_CONNECT;
			if(tableName == null || condition == null)
				throw new IllegalArgumentException("An argument is null.");
			stat = connect.createStatement();
			String conditionString = createStringCondition(condition);
			stat.execute("DELETE FROM " + tableName + " WHERE " + conditionString);
		}catch(SQLException e){
			e.printStackTrace();
			return ApiResponse.ERROR;
		}
		finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return ApiResponse.ERROR;
				}
		}
		return ApiResponse.SUCCESS;
	}

	public ApiResponse createTable(String tableName, List<Columns> listColumns,
			boolean existCondition) {
		Statement stat = null;
		try {
			if (connect == null || connect.isClosed())
				return ApiResponse.MYSQL_NOT_CONNECT;
			if (tableName == null || listColumns == null)
				throw new IllegalArgumentException("An argument is null.");
			stat = connect.createStatement();
			String columnString = createColumns(listColumns);
			stat.execute("CREATE TABLE "
					+ (existCondition ? "IF NOT EXISTS " : "") + "" + tableName
					+ " (" + columnString + ")");
		} catch (SQLException e) {
			e.printStackTrace();
			return ApiResponse.ERROR;
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return ApiResponse.ERROR;
				}
		}
		return ApiResponse.SUCCESS;
	}
	
	public ApiResponse deleteTable(String tableName) throws IllegalArgumentException{
		Statement stat = null;
		try {
			if (connect == null || connect.isClosed())
				return ApiResponse.MYSQL_NOT_CONNECT;
			if (tableName == null)
				throw new IllegalArgumentException("An argument is null.");
			stat = connect.createStatement();
			stat.execute("DROP TABLE IF EXISTS " + tableName);
		} catch (SQLException e) {
			e.printStackTrace();
			return ApiResponse.ERROR;
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return ApiResponse.ERROR;
				}
		}
		return ApiResponse.SUCCESS;
	}

	public ApiResponse insertValues(TableProperties tableProperties)
			throws IllegalArgumentException, LengthTableException, SQLException {
		Statement stat = null;
			if (connect == null || connect.isClosed())
				return ApiResponse.MYSQL_NOT_CONNECT;
			if (tableProperties == null)
				throw new IllegalArgumentException("The argument is null.");
			if (tableProperties.getColumnName().length != tableProperties
					.getValues().length)
				throw new LengthTableException(
						"Value and column table aren't equals for the length.");
			stat = connect.createStatement();
			String columnString = createStringColumns(tableProperties
					.getColumnName());
			String valueString = createStringValues(tableProperties.getValues());
			stat.execute("INSERT INTO " + tableProperties.getTableName() + " ("
					+ columnString + ") VALUES " + "(" + valueString + ")");
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return ApiResponse.ERROR;
				}
			return ApiResponse.SUCCESS;
	}

	public ApiResponse updateValue(TableProperties tableProperties,
			Condition condition) throws IllegalArgumentException,
			LengthTableException {
		Statement stat = null;
		try {
			if (connect == null || connect.isClosed())
				return ApiResponse.MYSQL_NOT_CONNECT;
			if (tableProperties == null || condition == null)
				throw new IllegalArgumentException("An argument is null.");
			if (tableProperties.getColumnName().length != 1
					|| tableProperties.getValues().length != 1)
				throw new LengthTableException(
						"You put more than one column or value.");
			stat = connect.createStatement();
			String conditionString = createStringCondition(condition);
			stat.execute("UPDATE " + tableProperties.getTableName() + " set "
					+ tableProperties.getColumnName()[0] + " = '"
					+ tableProperties.getValues()[0] + "' where "
					+ conditionString);
		} catch (SQLException e) {
			e.printStackTrace();
			return ApiResponse.ERROR;
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return ApiResponse.ERROR;
				}
		}
		return ApiResponse.SUCCESS;
	}

	public TableData selectValues(TableProperties tableProperties, Condition condition)
			throws IllegalArgumentException, LengthTableException {
		Statement stat = null;
		ResultSet rs = null;
		Object[] values = null;
		try {
			if (connect == null || connect.isClosed())
				return new TableData(ApiResponse.MYSQL_NOT_CONNECT, null);
			if (tableProperties == null)
				throw new IllegalArgumentException("An argument is null.");
			if (tableProperties.getColumnName().length < 1)
				throw new LengthTableException("You need some colomns.");
			if (tableProperties.getColumnName()[0].equals("*"))
				throw new IllegalArgumentException(
						"The * doesn't exist with MyApi.");
			stat = connect.createStatement();
			String columnString = createStringColumns(tableProperties
					.getColumnName());
			String conditionString = createStringCondition(condition);
			rs = stat.executeQuery("SELECT "
					+ columnString
					+ " FROM "
					+ tableProperties.getTableName()
					+ (condition != null ? " where " + conditionString : ""));
			int index = 0;
			rs.last();
			values = new Object[rs.getRow() * tableProperties.getColumnName().length];
			rs.beforeFirst();
			while(rs.next()){
				for(String columnName : tableProperties.getColumnName()){
				values[index] = rs.getObject(columnName);
				index++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new TableData(ApiResponse.ERROR, null);
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return new TableData(ApiResponse.ERROR, null);
				}
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return new TableData(ApiResponse.ERROR, null);
				}
			}
		}
		return new TableData(ApiResponse.SUCCESS, values);
	}
	
	public static boolean checkAllValues(Object[] values){
		boolean correct = true;
		for(Object value : values){
			if(!(value instanceof Integer) && 
					!(value instanceof Double) && 
					!(value instanceof String) && 
					!(value instanceof Character) && 
					!(value instanceof Float) && 
					!(value instanceof Boolean)){
				correct = false;
			}
		}
		return correct;
	}

	private String createStringValues(Object[] values) {
		String valueString = "";
		for (int i = 0; i < values.length; i++) {
			valueString += "'" + values[i] + "'" + (i + 1 < values.length ? ", " : "");
		}
		return valueString;
	}

	private String createStringColumns(String[] columnNames) {
		String columnString = "";
		for (int i = 0; i < columnNames.length; i++) {
			columnString += columnNames[i]
					+ (i + 1 < columnNames.length ? ", " : "");
		}
		return columnString;
	}
	
	private String createStringCondition(Condition c){
		String conditionString = "";
		for(int i = 0; i < c.getColumns().length; i++){
			conditionString += c.getColumns()[i] + " "
					+ c.getTypes()[i].getTypeInString() 
					+ " '" + c.getValues()[i] + "'" 
					+ (i + 1 < c.getColumns().length ? (c.getChoices()[i]?" AND ":" OR ") : "");
		}
		return conditionString;
	}

	private String createColumns(List<Columns> listColomns) {
		String colomnString = "";
		String primaryKey = "";
		String unique = "";
		int index = 0;
		for (Columns colomns : listColomns) {
			colomnString += colomns.getColomnName();
			colomnString += " " + colomns.getTypeData().getTypeInString();
			colomnString += " ("
					+ (colomns.getValue() != -1 ? colomns.getValue() : "10,2")
					+ ")"
					+ (colomns.isNull() ? " DEFAULT NULL " : " NOT NULL")
					+ (colomns.isAutoIncremented() ? " AUTO_INCREMENT" : "");
			if (colomns.isPrimaryKey()) {
				primaryKey = " PRIMARY KEY (" + colomns.getColomnName() + ")";
			}
			else if(colomns.isUnique()){
				unique = " UNIQUE (" + colomns.getColomnName() + ")";
			}
			colomnString += (index < listColomns.size() - 1 ? ", " : (!primaryKey.isEmpty() || !unique.isEmpty()?",":"")
					+ primaryKey + (!unique.isEmpty() && !primaryKey.isEmpty()?",":"") +  unique);
			index++;
		}
		return colomnString;
	}

	private class Connexion {
		String host;
		int port;
		String username;
		String password;
		String databaseName;

		public Connexion(String host, int port, String userName,
				String password, String databaseName) {
			this.host = host;
			this.port = port;
			this.username = userName;
			this.password = password;
			this.databaseName = databaseName;
		}
	}
}
