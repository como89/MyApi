package ca.como89.myapi.api.mysql;

public enum TypeCondition {
	/**
	 * EQUALS -  = 
	 */
	EQUALS("="),
	/**
	 * GREATER -  >
	 */
	GREATER(">"),
	/**
	 * SMALLER -  < 
	 */
	SMALLER("<"),
	/**
	 * NOT_EQUALS - != 
	 */
	NOT_EQUALS("!=");
	
	private String type;
	
	private TypeCondition(String type){
		this.type = type;
	}
	
	public String getTypeInString(){
		return type;
	}
}
