package ca.como89.myapi.api.queries;

/**
 * This class is the preference of the Query when you want to use the multiCondition.
 */
public class PrefQuery {

	/**
	 * Bool when you want to use the multiCondition.
	 */
	public boolean multiCondition;
	/**
	 * A tab which you can add Operator between conditions. See @Operator
	 */
	public Operator[] operator;
	
	PrefQuery() {}
}
