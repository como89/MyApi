package ca.como89.myapi.api.queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.como89.myapi.api.exceptions.NotValidColumnException;
/**
 * This class is the main core class of all queries.
 */
public abstract class Query {
	
	protected String tableName;
	protected HashMap<String,Object> listColumns;
	
	protected Query() {
		listColumns = new HashMap<String,Object>();
	}
	/**
	 * This method is to add a column with a value. It is used with insert and update methods.
	 * @param columnName - The columnName need to be add.
	 * @param value - The value associate with the columnName.
	 * @return Boolean, True, if the column is correctly added and False, if the column already exist.
	 * @throws NotValidColumnException - It is not valid, because the name is restricted for sql syntax.
	 */
	public boolean addColumn(String columnName,Object value) throws NotValidColumnException {return false;}
	/**
	 * This method is to add a column without value. It is used with select method.
	 * @param columnName - The columnName need to be add.
	 * @return Boolean, True, if the column is correctly added and False, if the column already exist.
	 * @throws NotValidColumnException - It is not valid, because the name is restricted for sql syntax.
	 */
	public boolean addColumn(String columnName) throws NotValidColumnException{return false;}
	/**
	 * This method is to set a column with is value. It can be used with insert and update methods.
	 * @param columnName - The columnName need to be add.
	 * @param value - The value associate with the columnName.
	 * @throws NotValidColumnException - It is not valid, because the name is restricted for sql syntax.
	 */
	public void setColumn(String columnName,Object value) throws NotValidColumnException {}
	
	/**
	 * This method is to set the table name.
	 * @param tableName - the Table name.
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName(){
		return tableName;
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
		throw new NotValidColumnException("The column name is not valid. The name is restricted for sql syntax.");
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
				return true;
			}
		}
		return false;
	}
}
