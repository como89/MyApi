package ca.como89.myapi.api.queries;

public enum Operator {
	
	AND("AND"),
	OR("OR");
	
	private String operator;
	
	private Operator(String operator) {
		this.operator = operator;
	}
	
	public String toString() {
		return operator;
	}
	
}
