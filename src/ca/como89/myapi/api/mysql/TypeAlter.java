package ca.como89.myapi.api.mysql;

public enum TypeAlter {
	ADD("ADD"), CHANGE("CHANGE"), DROP("DROP");

	private String type;

	private TypeAlter(String type) {
		this.type = type;
	}

	public String getTypeInString() {
		return type;
	}

}
