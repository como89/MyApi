package ca.como89.myapi.api.sql;

//import ca.como89.myapi.DataManager;
import ca.como89.myapi.api.exceptions.IllegalTypeException;

@Deprecated
public class TableProperties {

	private String tableName;
	private String[] columnName;
	private Object[] values;

	/**
	 * The constructor of TablePropeties.
	 * @param tableName - the name of the table.
	 * @param columnNames - the columns name.
	 * @param value - the values.
	 * @throws IllegalTypeException - if the type is not supported by MyApi.
	 * @throws IllegalArgumentException - if the parameters are null.
	 */
	@Deprecated
	public TableProperties(String tableName, String[] columnNames, Object[] value)
			throws IllegalTypeException, IllegalArgumentException {
		if (tableName == null || columnNames == null)
			throw new IllegalArgumentException("An argument is null.");
//			if(!DataManager.checkAllValues(value)) {
//				throw new IllegalTypeException(
//						"This type is not supported by MyApi. Types supported : Integer, Double, String, Character, Float, Boolean and Long");
//		}
		this.tableName = tableName;
		this.columnName = columnNames;
		this.values = value;
	}
	
	/**
	 * Another constructor of TableProperties.
	 * @param tableName - the name of the table.
	 * @param columnNames - the name of the columns.
	 * @throws IllegalArgumentException - if the parameters are null.
	 */
	@Deprecated
	public TableProperties(String tableName, String[] columnNames) 
			throws IllegalArgumentException{
		if (tableName == null || columnNames == null)
			throw new IllegalArgumentException("An argument is null.");
		this.tableName = tableName;
		this.columnName = columnNames;
	}

	public String getTableName() {
		return tableName;
	}

	public String[] getColumnName() {
		return columnName;
	}

	public Object[] getValues() {
		return values;
	}
}
