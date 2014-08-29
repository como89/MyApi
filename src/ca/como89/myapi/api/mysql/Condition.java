package ca.como89.myapi.api.mysql;

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
	 * @param choice - The choice between each condition. ( True = AND, False = OR) (Put it null, if you have one column).
	 * @throws IllegalTypeException - if the type is not supported by MyApi.
	 * @throws IllegalArgumentException - if the parameters are null.
	 * @throws LengthTableException - if the tables aren't equals for the length or choice table don't have enought arguments.
	 */
	public Condition(String[] columns, Object[] values, TypeCondition[] types, boolean[] choice) throws IllegalTypeException, IllegalArgumentException, LengthTableException{
		if(columns == null || types == null || values == null)throw new IllegalArgumentException("An argument is null.");
		if(columns.length != types.length || columns.length != values.length) throw new LengthTableException("Values, columns and types table aren't equals for the length.");
		if(choice != null){
		if(columns.length - 1 != choice.length)throw new LengthTableException("Choice table don't have enought arguments.");
		}
		if(!(values[0] instanceof Integer) && 
				!(values[0] instanceof Double) && 
				!(values[0] instanceof String) && 
				!(values[0] instanceof Character) && 
				!(values[0] instanceof Float) && 
				!(values[0] instanceof Boolean)) 
			throw new IllegalTypeException("This type is not supported by MYApi. Types supported : Integer, Double, String, Character, Float and Boolean");
		this.columns = columns;
		this.values = values;
		this.types = types;
		this.choice = choice;
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
