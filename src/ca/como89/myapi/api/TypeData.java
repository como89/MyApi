package ca.como89.myapi.api;

/**
 * The type of the data.
 */
public enum TypeData {

	/**
	 * A Integer
	 */
	INT("INTEGER"),
	/**
	 * A double
	 */
	DOUBLE("DOUBLE"),
	/**
	 * A String in java.
	 */
	VARCHAR("VARCHAR"),
	/**
	 * A very large String in java.
	 */
	TEXT("TEXT"),
	/**
	 * A character.
	 */
	CHAR("CHAR"),
	/**
	 * A float.
	 */
	FLOAT("FLOAT"),
	/**
	 * A Long
	 */
	LONG("SIGNED BIGINT"),
	/**
	 * A Boolean
	 */
	BOOLEAN("BOOLEAN");
	
	private String type;
	
	private TypeData(String type){
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
