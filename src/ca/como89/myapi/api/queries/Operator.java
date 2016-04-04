package ca.como89.myapi.api.queries;

/**
 * This enum class is when you want to add between two conditions an operator.
 * This enum is needed for multi conditions.
 */
public enum Operator {
	
	/**
	 * The operator AND.
	 */
	AND("AND"),
	/**
	 * The operator OR.
	 */
	OR("OR");
	
	private String operator;
	
	private Operator(String operator) {
		this.operator = operator;
	}
	
	public String toString() {
		return operator;
	}
	
}
