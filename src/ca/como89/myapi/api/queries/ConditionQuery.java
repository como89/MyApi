package ca.como89.myapi.api.queries;

import java.util.ArrayList;

import ca.como89.myapi.api.conditions.Condition;

/**
 * This class is herited when the query can have conditions.
 */
public abstract class ConditionQuery extends Query {

	protected ArrayList<Condition> listConditions;
	protected PrefQuery prefQuery;
	
	protected ConditionQuery() {
		this.listConditions = new ArrayList<Condition>();
		prefQuery = new PrefQuery();
	}
	
	/**
	 * This method add a condition to the query.
	 * @param condition - The Condition, see @Condition.
	 */
	public void addCondition(Condition condition) {
		listConditions.add(condition);
	}
	
	/**
	 * This method return the preference for the multiCondition.
	 * @return The preference. See @PrefQuery
	 */
	public PrefQuery getPreferences() {
		return prefQuery;
	}
	
	public ArrayList<Condition> getConditions() {
		return listConditions;
	}

}
