package ca.como89.myapi.core.sql;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ca.como89.myapi.api.ApiDatabase;
import ca.como89.myapi.api.ApiResponse;
import ca.como89.myapi.api.sql.Columns;

public class SQLite extends Mysql{

	public SQLite(ApiDatabase apiDatabase) {
		super(apiDatabase);
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
			String columnString = createColumnsSqLite(listColumns);
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

	private String createColumnsSqLite(List<Columns> listColumns) {
		String columnString = "";
		int index = 0;
		for (Columns columns : listColumns) {
			columnString += "'" + columns.getColomnName() + "'";
			columnString += " " + columns.getTypeData().getTypeInString();
			columnString += (!columns.isAutoIncremented()?" ("
					+ (columns.getValue() != -1 ? columns.getValue() : columns.getDisplaySize() + ","
					+ columns.getDecimalNumber()) + ")":"")
					+ (columns.isPrimaryKey()?" PRIMARY KEY":"")
					+ (columns.isAutoIncremented()?" AUTOINCREMENT":"")
					+ (columns.isNull() ? " DEFAULT NULL" : " NOT NULL");
			columnString += (index < listColumns.size() - 1 ? ", ":"");
			index++;
		}
		return columnString;
	}

}
