package ca.como89.myapi.api.queries;

import java.util.ArrayList;

import ca.como89.myapi.api.conditions.Condition;

public abstract class ConditionQuery extends Query {

	protected ArrayList<Condition> listConditions;
	protected PrefQuery prefQuery;
	
	protected ConditionQuery(QueryType queryType) {
		super(queryType);
		this.listConditions = new ArrayList<Condition>();
		prefQuery = new PrefQuery();
	}
	
	public void addCondition(Condition condition) {
		listConditions.add(condition);
	}
	
	public PrefQuery getPreferences() {
		return prefQuery;
	}
	
	public ArrayList<Condition> getConditions() {
		return listConditions;
	}

}
