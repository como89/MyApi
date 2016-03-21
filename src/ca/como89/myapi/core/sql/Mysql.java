package ca.como89.myapi.core.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.como89.myapi.api.ApiDatabase;
import ca.como89.myapi.api.ApiResponse;
import ca.como89.myapi.api.MyApi;
import ca.como89.myapi.api.TableData;
import ca.como89.myapi.api.conditions.Condition;
import ca.como89.myapi.api.exceptions.LengthTableException;
import ca.como89.myapi.api.queries.InsertQuery;
import ca.como89.myapi.api.queries.SelectQuery;
import ca.como89.myapi.api.queries.UpdateQuery;
import ca.como89.myapi.api.sql.Columns;
import ca.como89.myapi.api.sql.TableProperties;
import ca.como89.myapi.core.CoreSystem;

public class Mysql extends CoreSystem implements MyApi {
	
	private Connection connect;

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
		
		stat.execute("INSERT INTO " + insertQuery.getTableName() + " ("
				+ columnString + ") VALUES " + "(" + valueString + ")");
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return ApiResponse.ERROR;
			}
		return ApiResponse.SUCCESS;
	}

	@Override
	public ApiResponse sendQuery(UpdateQuery updateQuery) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse sendQuery(SelectQuery selectQuery) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ApiResponse updateValues(TableProperties tableProperties, Condition condition)
//			throws IllegalArgumentException, LengthTableException {
//		Statement stat = null;
//		try {
//			if (connect == null || connect.isClosed())
//				return ApiResponse.DATABASE_NOT_CONNECT;
//			if (tableProperties == null || condition == null)
//				throw new IllegalArgumentException("An argument is null.");
//			if (tableProperties.getColumnName().length != tableProperties.getValues().length)
//				throw new LengthTableException(
//						"The tables are not equals.");
//			stat = connect.createStatement();
//			String conditionString = createStringCondition(condition);
//			for(int i = 0; i < tableProperties.getColumnName().length;i++){
//				stat.execute("UPDATE " + tableProperties.getTableName() + " set "
//						+ tableProperties.getColumnName()[i] + " = '"
//						+ tableProperties.getValues()[i] + "' where "
//						+ conditionString);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return ApiResponse.ERROR;
//		} finally {
//			if (stat != null)
//				try {
//					stat.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//					return ApiResponse.ERROR;
//				}
//		}
//		return ApiResponse.SUCCESS;
//	}
//
//	@Override
//	public TableData selectValues(TableProperties tableProperties, Condition condition)
//			throws IllegalArgumentException, LengthTableException {
//		Statement stat = null;
//		ResultSet rs = null;
//		Map<Integer,Object> mapValue = null;
//		try {
//			if (connect == null || connect.isClosed())
//				return new TableData(ApiResponse.DATABASE_NOT_CONNECT, null);
//			if (tableProperties == null)
//				throw new IllegalArgumentException("An argument is null.");
//			if (tableProperties.getColumnName().length < 1)
//				throw new LengthTableException("You need some colomns.");
//			if (tableProperties.getColumnName()[0].equals("*"))
//				throw new IllegalArgumentException(
//						"The * doesn't exist with MyApi.");
//			stat = connect.createStatement();
//			String columnString = createStringColumns(tableProperties
//					.getColumnName());
//			String conditionString = condition == null?"":createStringCondition(condition);
//			rs = stat.executeQuery("SELECT "
//					+ columnString
//					+ " FROM "
//					+ tableProperties.getTableName()
//					+ (condition != null ? " where " + conditionString : ""));
//			mapValue = new HashMap<>();
//			int index = 0;
//			while(rs.next()){
//				for(String columnName : tableProperties.getColumnName()){
//				mapValue.put(index, rs.getObject(columnName));
//				index++;
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return new TableData(ApiResponse.ERROR, null);
//		} finally {
//			if (stat != null)
//				try {
//					stat.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//					return new TableData(ApiResponse.ERROR, null);
//				}
//			if(rs != null){
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//					return new TableData(ApiResponse.ERROR, null);
//				}
//			}
//		}
//		return new TableData(ApiResponse.SUCCESS, mapValue);
//	}
//
//	@Override
//	public TableData countRows(TableProperties tableProperties, Condition condition)
//			throws IllegalArgumentException, LengthTableException {
//		Statement stat = null;
//		ResultSet rs = null;
//		Map<Integer,Object> mapValue = null;
//		try {
//			if(connect == null || connect.isClosed())
//				return new TableData(ApiResponse.DATABASE_NOT_CONNECT,null);
//			if(tableProperties == null || condition == null)
//				throw new IllegalArgumentException("An argument is null.");
//			if (tableProperties.getColumnName().length < 1)
//				throw new LengthTableException("You need some colomns.");
//			if (tableProperties.getColumnName()[0].equals("*"))
//				throw new IllegalArgumentException(
//						"The * doesn't exist with MyApi.");
//			stat = connect.createStatement();
//			String columnString = createStringColumns(tableProperties
//					.getColumnName());
//			String conditionString = createStringCondition(condition);
//			rs = stat.executeQuery("SELECT " + columnString 
//					+ ", COUNT(*) FROM " + tableProperties.getTableName() 
//					+ " WHERE " + conditionString);
//			mapValue = new HashMap<>();
//			int index = 0;
//			while(rs.next()){
//				for(String columnName : tableProperties.getColumnName()){
//				mapValue.put(index, rs.getObject(columnName));
//				index++;
//				}
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//			return new TableData(ApiResponse.ERROR,null);
//		}
//		finally {
//			if (stat != null)
//				try {
//					stat.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//					return new TableData(ApiResponse.ERROR, null);
//				}
//			if(rs != null){
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//					return new TableData(ApiResponse.ERROR, null);
//				}
//			}
//		}
//		return new TableData(ApiResponse.SUCCESS,mapValue);
//	}

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
	public TableData checkIfTableExist(String tableName) throws IllegalArgumentException {
		DatabaseMetaData databaseMeta = null;
		try {
			if(connect == null || connect.isClosed())
				return new TableData(ApiResponse.DATABASE_NOT_CONNECT,false);
		if(tableName == null)
			throw new IllegalArgumentException("An argument is null.");
		databaseMeta = connect.getMetaData();
		ResultSet rs = null;
		rs = databaseMeta.getTables(null, null, tableName, null);
		return (rs.next()?new TableData(ApiResponse.SUCCESS,true):new TableData(ApiResponse.SUCCESS,false));
		} catch (SQLException e) {
			e.printStackTrace();
			return new TableData(ApiResponse.ERROR,false);
		}
	}

	@Override
	public TableData checkIfColumnExist(String tableName, String columnName) throws IllegalArgumentException {
		DatabaseMetaData databaseMeta = null;
		try {
			if(connect == null || connect.isClosed())
				return new TableData(ApiResponse.DATABASE_NOT_CONNECT,false);
		if(tableName == null || columnName == null)
			throw new IllegalArgumentException("An argument is null.");
		databaseMeta = connect.getMetaData();
		ResultSet rs = null;
		rs = databaseMeta.getColumns(null, null, tableName, columnName);
		return (rs.next()?new TableData(ApiResponse.SUCCESS,true):new TableData(ApiResponse.SUCCESS,false));
		} catch (SQLException e) {
			e.printStackTrace();
			return new TableData(ApiResponse.ERROR,false);
		}
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
					+ (colomns.getValue() != -1 ? colomns.getValue() : colomns.getDisplaySize() + "," + colomns.getDecimalNumber())
					+ ")"
					+ (colomns.isNull() ? " DEFAULT NULL " : " NOT NULL")
					+ (colomns.isAutoIncremented()?" AUTO_INCREMENT" : "");
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

}
