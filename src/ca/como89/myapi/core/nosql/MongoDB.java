package ca.como89.myapi.core.nosql;

import java.sql.SQLException;
import java.util.List;

import ca.como89.myapi.api.ApiDatabase;
import ca.como89.myapi.api.ApiResponse;
import ca.como89.myapi.api.MyApi;
import ca.como89.myapi.api.TableData;
import ca.como89.myapi.api.conditions.Condition;
import ca.como89.myapi.api.queries.CountRowsQuery;
import ca.como89.myapi.api.queries.InsertQuery;
import ca.como89.myapi.api.queries.SelectQuery;
import ca.como89.myapi.api.queries.UpdateQuery;
import ca.como89.myapi.api.sql.Columns;
import ca.como89.myapi.api.sql.TableProperties;
import ca.como89.myapi.core.CoreSystem;

public class MongoDB extends CoreSystem implements MyApi {

	public MongoDB(ApiDatabase apiDatabase) {
		super(apiDatabase);
	}
	
	@Override
	public String getProjectName() {
		return apiDatabase.projectName;
	}

	@Override
	public ApiResponse connect() {
		return null;
	}

	@Override
	public ApiResponse disconnect() throws SQLException {
		return null;
	}

	@Override
	public ApiResponse isConnect() throws SQLException {
		return null;
	}

	@Override
	public ApiResponse createTable(String tableName, List<Columns> listcolumns, boolean existCondition)
			throws IllegalArgumentException {
		return null;
	}

	@Override
	public ApiResponse deleteTable(String tableName) throws IllegalArgumentException {		
		return null;
	}


	@Override
	public ApiResponse deleteRow(String tableName, Condition condition) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse addColumns(String tableName, List<Columns> listColumns, boolean hisIgnore)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse changeColumn(String tableName, String oldColumnName, Columns newColumn, boolean hisIgnore)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse removeColumn(String tableName, String columnName, boolean hisIgnore)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public TableData checkIfTableExist(String tableName) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public TableData checkIfColumnExist(String tableName, String columnName) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse sendQuery(InsertQuery insertQuery) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public ApiResponse sendQuery(CountRowsQuery countRowsQuery) throws SQLException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
