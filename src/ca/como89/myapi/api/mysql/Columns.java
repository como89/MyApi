package ca.como89.myapi.api.mysql;

public class Columns {

	private String colomnName;
	private TypeData typeData;
	private boolean isNull;
	private boolean autoIncremented;
	private int value;
	private boolean primary;
	private boolean unique;
	
	/**
	 * The constructor of columns. 
	 * @param colomnName - The columnName.
	 * @param typeData - The type of the data.
	 * @param value - The length of the type or the value of the data.
	 * @param isNull - If this column can be null.
	 * @param primary - If this column is primary. 
	 * @param autoIncremented - If this column can be autoIncremented.
	 * @param unique - If the values of this column are unique.
	 */
	public Columns(String colomnName,TypeData typeData,int value,boolean isNull,boolean primary, boolean autoIncremented, boolean unique){
		this.colomnName = colomnName;
		this.typeData = typeData;
		this.isNull = isNull;
		this.primary = primary;
		this.unique = unique;
		this.autoIncremented = autoIncremented;
		this.value = value;
	}

	public String getColomnName() {
		return colomnName;
	}
	
	public TypeData getTypeData(){
		return typeData;
	}
	
	public boolean isNull(){
		return isNull;
	}
	
	public int getValue(){
		return value;
	}
	
	public boolean isPrimaryKey(){
		return primary;
	}
	
	public boolean isAutoIncremented(){
		return autoIncremented;
	}
	
	public boolean isUnique(){
		return unique;
	}
}
