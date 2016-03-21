package ca.como89.myapi.api;

/**
 * The type of the data.
 */
public enum TypeData {

	INT("INTEGER"),
	DOUBLE("DOUBLE"),
	VARCHAR("VARCHAR"),
	TEXT("TEXT"),
	CHAR("CHAR"),
	FLOAT("FLOAT"),
	LONG("SIGNED BIGINT"),
	BOOLEAN("BOOLEAN");
	
	private String type;
	
	private TypeData(String type){
		this.type = type;
	}
	
	public String getTypeInString(){
		return type;
	}
}
