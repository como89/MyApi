package ca.como89.myapi.api;

/**
 * The class Columns when you want to create columns.
 */
public class Columns {

	/**
	 * The column Name
	 */
	public String colomnName;
	/**
	 * The type of the data, see @TypeData class.
	 */
	public TypeData typeData;
	/**
	 * A bool to know if the column can be null.
	 */
	public boolean isNull;
	/**
	 * A bool to know if the column can be autoincremented. To use with primary column.
	 */
	public boolean autoIncremented;
	/**
	 * The default value of the column or the length of the varchar.
	 */
	public int value;
	/**
	 * The displaySize for the number of a Double or float.
	 */
	public int displaySize;
	/**
	 * The number of the decimal of a Double or float.
	 */
	public int decimalNumber;
	/**
	 * A bool to know if the column is primary or not.
	 */
	public boolean primary;
	/**
	 * A bool to know if the column is unique or not.
	 */
	public boolean unique;
	
	public Columns() {
		value = -1;
	}
}
