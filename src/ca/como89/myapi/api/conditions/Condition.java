package ca.como89.myapi.api.conditions;

public class Condition {

	public String columnName;
	public  Object value;
	public TypeCondition typeCondition;
	
	public Condition() {
		columnName = "";
		value = null;
		typeCondition = null;
	}
}
