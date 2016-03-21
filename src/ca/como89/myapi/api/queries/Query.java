package ca.como89.myapi.api.queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.como89.myapi.api.exceptions.NotValidColumnException;

public abstract class Query {
	
	protected String tableName;
	protected HashMap<String,Object> listColumns;

	protected QueryType queryType;
	
	protected Query(QueryType queryType) {
		this.queryType = queryType;
		this.listColumns = new HashMap<String,Object>();
	}
	
	public boolean addColumn(String columnName,Object value) throws NotValidColumnException {return false;}
	public boolean addColumn(String columnName) throws NotValidColumnException{return false;}
	public boolean setColumn(String columnName,Object value) throws NotValidColumnException {return false;}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName(){
		return tableName;
	}
	
	public QueryType getType() {
		return queryType;
	}
	
	public List<String> getListColumns() {
		List<String> listColumnsName = new ArrayList<String>();
		for(String columnName : listColumns.keySet()) {
			listColumnsName.add(columnName);
		}
		return listColumnsName;
	}
	
	public Object getValueFromColumn(String columnName) {
		return listColumns.get(columnName);
	}
	
	protected void throwsExceptionNotValidColumn() throws NotValidColumnException {
		throw new NotValidColumnException("The column name is not valid. The name already exist or the name is restricted for sql syntax.");
	}
	
	protected boolean validColumn(String columnName) {
			if(columnName.equalsIgnoreCase("for")
					&& columnName.equalsIgnoreCase("select")
					&& columnName.equalsIgnoreCase("insert")) {
				return false;
			}
		return true;
	}
	
	protected boolean existColumn(String columnName) {
		for(String columnsName : listColumns.keySet()) {
			if(columnsName.equalsIgnoreCase(columnName)){
				return false;
			}
		}
		return true;
	}
}
