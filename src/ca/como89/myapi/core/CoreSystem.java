package ca.como89.myapi.core;

import java.util.ArrayList;
import java.util.List;

import ca.como89.myapi.api.ApiDatabase;
import ca.como89.myapi.api.conditions.Condition;
import ca.como89.myapi.api.queries.PrefQuery;

 public abstract class CoreSystem {

	protected ApiDatabase apiDatabase;
	
	@SuppressWarnings("unused")
	private CoreSystem() {}
	
	protected CoreSystem(ApiDatabase apiDatabase){
		this.apiDatabase = apiDatabase;
	}
	
	protected String createStringValues(Object[] values) {
		String valueString = "";
		for (int i = 0; i < values.length; i++) {
			valueString += "'" + values[i] + "'" + (i + 1 < values.length ? ", " : "");
		}
		return valueString;
	}

	protected String createStringColumns(List<String> listColumnNames) {
		String columnString = "";
		int i = 0;
		int totalSize = listColumnNames.size();
		for (String columnName : listColumnNames) {
			columnString += columnName
					+ (i + 1 < totalSize ? ", " : "");
		}
		return columnString;
	}
	
	protected String createStringCondition(ArrayList<Condition> listCondition,PrefQuery prefQuery){
		String conditionString = "";
		int sizeTotal = listCondition.size();
		int index = 0;
		if(prefQuery.useMultiCondition) {
			for(Condition c : listCondition) {
				conditionString += c.columnName + " "
						+ c.typeCondition.getTypeInString() 
						+ " '" + c.value + "'"
						+ (index + 1 < sizeTotal ? prefQuery.operator[index].toString():"") ;
				index++;
			}
		} else {
			Condition c = listCondition.get(0);
			conditionString += c.columnName + " "
					+ c.typeCondition.getTypeInString() 
					+ " '" + c.value + "'";
		}
		return conditionString;
	}

}
