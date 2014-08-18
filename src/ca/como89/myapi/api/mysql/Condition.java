package ca.como89.myapi.api.mysql;

import ca.como89.myapi.api.mysql.exception.IllegalTypeException;

public class Condition {

	private String column;
	private Object value;
	private TypeCondition type;
	
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
				!(value instanceof Boolean)) 
			throw new IllegalTypeException("This type is not supported by MYApi. Types supported : Integer, Double, String, Character, Float and Boolean");
		this.column = column;
		this.value = value;
		this.type = type;
	}
	
	public String getColumn(){
		return column;
	}
	
	public Object getValue(){
		return value;
	}
	
	public TypeCondition getTypeCondition(){
		return type;
	}
	
}
