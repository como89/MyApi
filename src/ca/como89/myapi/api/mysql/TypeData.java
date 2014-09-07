package ca.como89.myapi.api.mysql;

/**
 * The type of the data.
 */
public enum TypeData {

	INT("INT"),
	DOUBLE("DOUBLE"),
	VARCHAR("VARCHAR"),
	TEXT("TEXT"),
	CHAR("CHAR"),
	FLOAT("FLOAT"),
	BOOLEAN("BOOLEAN");
	
	private String type;
	
	private TypeData(String type){
		this.type = type;
	}
	
	public String getTypeInString(){
		return type;
	}
}
