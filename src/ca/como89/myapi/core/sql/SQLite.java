package ca.como89.myapi.core.sql;

import java.sql.SQLException;
import java.util.List;

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

@SuppressWarnings("deprecation")
public class SQLite extends CoreSystem implements MyApi{

	public SQLite(ApiDatabase apiDatabase) {
		super(apiDatabase);
	}

	@Override
	public String getProjectName() {
		return apiDatabase.projectName;
	}

	@Override
	public ApiResponse connect() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse disconnect() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse isConnect() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse createTable(String tableName, List<Columns> listColumns, boolean existCondition)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse deleteTable(String tableName) throws IllegalArgumentException {
		// TODO Auto-generated method stub
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
	public TableData checkIfTableExist(String tableName) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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

}
