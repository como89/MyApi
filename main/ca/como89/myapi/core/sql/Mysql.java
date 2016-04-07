package ca.como89.myapi.core.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.como89.myapi.api.ApiDatabase;
import ca.como89.myapi.api.ApiResponse;
import ca.como89.myapi.api.Columns;
import ca.como89.myapi.api.MyApi;
import ca.como89.myapi.api.conditions.Condition;
import ca.como89.myapi.api.queries.CountRowsQuery;
import ca.como89.myapi.api.queries.InsertQuery;
import ca.como89.myapi.api.queries.ResultObjects;
import ca.como89.myapi.api.queries.SelectQuery;
import ca.como89.myapi.api.queries.UpdateQuery;
import ca.como89.myapi.core.CoreSystem;

public class Mysql extends CoreSystem implements MyApi {
	
	protected Connection connect;

	public Mysql(ApiDatabase apiDatabase) {
		super(apiDatabase);
	}

	@Override
	public String getProjectName() {
		return apiDatabase.projectName;
	}

	@Override
	public ApiResponse connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://" + apiDatabase.host
				+ ":" + apiDatabase.port + "/" + apiDatabase.databaseName,
				apiDatabase.username, apiDatabase.password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return ApiResponse.ERROR;
		}
		return ApiResponse.SUCCESS;
	}

	@Override
	public ApiResponse disconnect() throws SQLException {
		if (connect != null) {
			connect.close();
			return ApiResponse.SUCCESS;
		}
		return ApiResponse.DATABASE_NOT_CONNECT;
	}

	@Override
	public ApiResponse isConnect() throws SQLException {
		if(connect != null){
			return !connect.isValid(0)?ApiResponse.DATABASE_CONNECTED:ApiResponse.DATABASE_NOT_CONNECT;
		}
		return ApiResponse.DATABASE_NOT_CONNECT;
	}

	@Override
	public ApiResponse createTable(String tableName, List<Columns> listColumns, boolean existCondition)
			throws IllegalArgumentException {
		Statement stat = null;
		try {
			if (connect == null || connect.isClosed())
				return ApiResponse.DATABASE_NOT_CONNECT;
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
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return ApiResponse.ERROR;
				}
		}
		return ApiResponse.SUCCESS;
	}

	@Override
	public ApiResponse deleteTable(String tableName) throws IllegalArgumentException {
		Statement stat = null;
		try {
			if (connect == null || connect.isClosed())
				return ApiResponse.DATABASE_NOT_CONNECT;
			if (tableName == null)
				throw new IllegalArgumentException("An argument is null.");
			stat = connect.createStatement();
			stat.execute("DROP TABLE IF EXISTS " + tableName);
		} catch (SQLException e) {
			e.printStackTrace();
			return ApiResponse.ERROR;
		} finally {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return ApiResponse.ERROR;
				}
		}
		return ApiResponse.SUCCESS;
	}
	
	public ApiResponse sendQuery(InsertQuery insertQuery) throws SQLException {
		Statement stat = null;
		try {
		if (connect == null || connect.isClosed())
			return ApiResponse.DATABASE_NOT_CONNECT;
		if(insertQuery == null)
			throw new IllegalArgumentException("The argument is null.");
		stat = connect.createStatement();
		
		List<String> listColumnNames = insertQuery.getListColumns();
		String columnString = createStringColumns(listColumnNames);
		
		Object[] values = new Object[listColumnNames.size()];
		int i = 0;
		for(String columnName : listColumnNames) {
			values[i] = insertQuery.getValueFromColumn(columnName);
			i++;
		}
		String valueString = createStringValues(values);
		
		String query = "INSERT INTO " + insertQuery.getTableName() + " ("
				+ columnString + ") VALUES " + "(" + valueString + ")";
		stat.execute(query);
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return ApiResponse.ERROR;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ApiResponse.ERROR;
		}
		return ApiResponse.SUCCESS;
	}

	@Override
	public ApiResponse sendQuery(UpdateQuery updateQuery) throws SQLException {
		Statement stat = null;
		try {
			if (connect == null || connect.isClosed())
				return ApiResponse.DATABASE_NOT_CONNECT;
			if(updateQuery == null)
				throw new IllegalArgumentException("An argument is null.");
			stat = connect.createStatement();
			String conditionString = createStringCondition(updateQuery.getConditions(),updateQuery.getPreferences());
			List<String> listColumns = updateQuery.getListColumns();
			String tableName = updateQuery.getTableName();
			
			for(String columnName : listColumns) {
				Object value = updateQuery.getValueFromColumn(columnName);
				stat.execute("UPDATE " + tableName 
						+ " set " + columnName + " = '"
						+ value + "' " + (!conditionString.isEmpty() ? " where " + conditionString : ""));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return ApiResponse.ERROR;
		}
		return ApiResponse.SUCCESS;
	}

	@Override
	public ApiResponse sendQuery(SelectQuery selectQuery) throws SQLException {
		Statement stat = null;
		ResultSet rs = null;
		try {
		if (connect == null || connect.isClosed())
			return ApiResponse.DATABASE_NOT_CONNECT;
		if (selectQuery == null)
			throw new IllegalArgumentException("An argument is null.");
		stat = connect.createStatement();
		
		List<String> listColumnNames = selectQuery.getListColumns();
		String columnString = createStringColumns(listColumnNames);
		
		String conditionString = createStringCondition(selectQuery.getConditions(),selectQuery.getPreferences());
		
		rs = stat.executeQuery("SELECT "
				+ columnString
				+ " FROM "
				+ selectQuery.getTableName()
				+ (!conditionString.isEmpty() ? " where " + conditionString : ""));
		
		ResultObjects resultObject = selectQuery.getResultObjects();
		while(rs.next()){
			for(String columnName : listColumnNames) {
				Object object = rs.getObject(columnName);
				if(object == null)
					continue;
				resultObject.addObject(columnName, object);
			}
		}
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
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return ApiResponse.ERROR;
			}
		}
	}
		return ApiResponse.SUCCESS;
	}
	
	@Override
	public ApiResponse sendQuery(CountRowsQuery countRowsQuery) throws SQLException {
		Statement stat = null;
		ResultSet rs = null;
		
		try {
		if(connect == null || connect.isClosed())
			return ApiResponse.DATABASE_NOT_CONNECT;
		if(countRowsQuery == null)
			throw new IllegalArgumentException("An argument is null.");
		stat = connect.createStatement();
		
		ArrayList<Condition> listCondition = countRowsQuery.getConditions();
		
		String conditionString = createStringCondition(listCondition,countRowsQuery.getPreferences());
		rs = stat.executeQuery("SELECT COUNT(*) FROM " + countRowsQuery.getTableName() 
		+ (!conditionString.isEmpty() ? " where " + conditionString : ""));
		
		while(rs.next()){
			countRowsQuery.setNbRows(rs.getInt(1));
		}
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
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return ApiResponse.ERROR;
			}
		}
	}
		
		return ApiResponse.SUCCESS;
	}

	/*
	 * Todo(non-Javadoc)
	 * @see ca.como89.myapi.api.MyApi#deleteRow(java.lang.String, ca.como89.myapi.api.conditions.Condition)
	 */
	@Override
	public ApiResponse deleteRow(String tableName, Condition condition) throws IllegalArgumentException {
		Statement stat = null;
		try {
			if(connect == null || connect.isClosed())
				return ApiResponse.DATABASE_NOT_CONNECT;
			if(tableName == null || condition == null)
				throw new IllegalArgumentException("An argument is null.");
			stat = connect.createStatement();
			//String conditionString = createStringCondition(condition);
			//stat.execute("DELETE FROM " + tableName + " WHERE " + conditionString);
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

	@Override
	public ApiResponse addColumns(String tableName, List<Columns> listColumns, boolean hisIgnore)
			throws IllegalArgumentException {
		Statement stat = null;
		try{
			if(connect == null || connect.isClosed())
				return ApiResponse.DATABASE_NOT_CONNECT;
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

	@Override
	public ApiResponse changeColumn(String tableName, String oldColumnName, Columns newColumn, boolean hisIgnore)
			throws IllegalArgumentException {
		Statement stat = null;
		try{
			if(connect == null || connect.isClosed())
				return ApiResponse.DATABASE_NOT_CONNECT;
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

	@Override
	public ApiResponse removeColumn(String tableName, String columnName, boolean hisIgnore)
			throws IllegalArgumentException {
		Statement stat = null;
		try{
			if(connect == null || connect.isClosed())
				return ApiResponse.DATABASE_NOT_CONNECT;
			if(tableName == null || columnName == null)
				throw new IllegalArgumentException("An argument is null.");
			stat = connect.createStatement();
			stat.execute("ALTER " + (hisIgnore?"IGNORE ":"") + "TABLE " + tableName + " DROP COLUMN " + columnName);
		}
		catch(SQLException e){
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

	@Override
	public boolean checkIfTableExist(String tableName) throws IllegalArgumentException {
		DatabaseMetaData databaseMeta = null;
		try {
			if(connect == null || connect.isClosed())
				return false;
		if(tableName == null)
			throw new IllegalArgumentException("An argument is null.");
		databaseMeta = connect.getMetaData();
		ResultSet rs = null;
		rs = databaseMeta.getTables(null, null, tableName, null);
		return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean checkIfColumnExist(String tableName, String columnName) throws IllegalArgumentException {
		DatabaseMetaData databaseMeta = null;
		try {
			if(connect == null || connect.isClosed())
				return false;
		if(tableName == null || columnName == null)
			throw new IllegalArgumentException("An argument is null.");
		databaseMeta = connect.getMetaData();
		ResultSet rs = null;
		rs = databaseMeta.getColumns(null, null, tableName, columnName);
		return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private String createColumns(List<Columns> listColomns) {
		String colomnString = "";
		String primaryKey = "";
		String unique = "";
		int index = 0;
		for (Columns colomns : listColomns) {
			colomnString += colomns.colomnName;
			colomnString += " " + colomns.typeData.toString();
			colomnString += " ("
					+ (colomns.value != -1 ? colomns.value : colomns.displaySize + "," + colomns.decimalNumber)
					+ ")"
					+ (colomns.isNull ? " DEFAULT NULL " : " NOT NULL")
					+ (colomns.autoIncremented?" AUTO_INCREMENT" : "");
			if (colomns.primary) {
				primaryKey = " PRIMARY KEY (" + colomns.colomnName + ")";
			}
			else if(colomns.unique){
				unique = " UNIQUE (" + colomns.colomnName + ")";
			}
			colomnString += (index < listColomns.size() - 1 ? ", " : (!primaryKey.isEmpty() || !unique.isEmpty()?",":"")
					+ primaryKey + (!unique.isEmpty() && !primaryKey.isEmpty()?",":"") +  unique);
			index++;
		}
		return colomnString;
	}

}
