package ca.como89.myapi.api.conditions;

public class Condition {

	/**
	 * The name of the column.
	 */
	public String columnName;
	/**
	 * The value which be equals, not equals, greater or smaller.
	 */
	public  Object value;
	/**
	 * The type of the Condition, see @TypeCondition
	 */
	public TypeCondition typeCondition;
	
	/**
	 * Default constructor
	 */
	public Condition() {
		columnName = "";
		value = null;
		typeCondition = null;
	}
}
