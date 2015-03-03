package ca.como89.myapi.api.mysql;

import ca.como89.myapi.DataManager;
import ca.como89.myapi.api.mysql.exception.IllegalTypeException;
import ca.como89.myapi.api.mysql.exception.LengthTableException;

public class Condition {

	private String[] columns;
	private Object[] values;
	private TypeCondition[] types;
	private boolean[] choice;
	
	/**
	 * The constructor of Condition.
	 * @param columns - The columns name.
	 * @param values - The values of each column.
	 * @param types - The types of each condition.
	 * @param choice - The choice between each condition. ( True = AND, False = OR)
	 * @throws IllegalTypeException - if the type is not supported by MyApi.
	 * @throws IllegalArgumentException - if the parameters are null.
	 * @throws LengthTableException - if the tables aren't equals for the length or choice table don't have enought arguments.
	 */
	public Condition(String[] columns, Object[] values, TypeCondition[] types, boolean[] choice) throws IllegalTypeException, IllegalArgumentException, LengthTableException{
		if(columns == null || types == null || values == null || choice == null)throw new IllegalArgumentException("An argument is null.");
		if(columns.length != types.length || columns.length != values.length || columns.length - 1 != choice.length) throw new LengthTableException("Values, columns, types and choice table aren't equals for the length.");
		if(!DataManager.checkAllValues(values))
			throw new IllegalTypeException("This type is not supported by MyApi. Types supported : Integer, Double, String, Character, Float, Boolean and Long");
		this.columns = columns;
		this.values = values;
		this.types = types;
		this.choice = choice;
	}
	
	/**
	 * The constructor of Condition.
	 * @param column - The column name.
	 * @param value - The value of the column.
	 * @param type - The type of the condition.
	 * @throws IllegalTypeException - if the type is not supported by MyApi.
	 * @throws IllegalArgumentException - if the parameters are null.
	 */
	public Condition(String column, Object value, TypeCondition type) throws IllegalTypeException, IllegalArgumentException{
		if(column == null || type == null || value == null)throw new IllegalArgumentException("An argument is null.");
		if(!(value instanceof Integer) && 
				!(value instanceof Double) && 
				!(value instanceof String) && 
				!(value instanceof Character) && 
				!(value instanceof Float) && 
				!(value instanceof Boolean) &&
				!(value instanceof Long)) 
			throw new IllegalTypeException("This type is not supported by MyApi. Types supported : Integer, Double, String, Character, Float, Boolean and Long");
		this.columns = new String[]{column};
		this.values = new Object[]{value};
		this.types = new TypeCondition[]{type};
		this.choice = null;
	}
	
	public String[] getColumns(){
		return columns;
	}
	
	public Object[] getValues(){
		return values;
	}
	
	public TypeCondition[] getTypes(){
		return types;
	}
	
	public boolean[] getChoices(){
		return choice;
	}
	
}
