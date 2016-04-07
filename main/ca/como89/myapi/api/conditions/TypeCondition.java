package ca.como89.myapi.api.conditions;

/**
 * This enum is the type of the condition.
 *
 */
public enum TypeCondition {
	/**
	 * EQUALS 
	 *  | = |
	 */
	EQUALS("="),
	/**
	 * GREATER 
	 *  | > |
	 */
	GREATER(">"),
	/**
	 * SMALLER 
	 *  | < | 
	 */
	SMALLER("<"),
	/**
	 * NOT_EQUALS
	 *  | != | 
	 */
	NOT_EQUALS("!=");
	
	private String type;
	
	private TypeCondition(String type){
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
