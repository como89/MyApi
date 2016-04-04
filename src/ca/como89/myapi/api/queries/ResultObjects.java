package ca.como89.myapi.api.queries;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultObjects {
	
	private HashMap<String, ArrayList<Object>> listResult;

	public ResultObjects() {
		this.listResult = new HashMap<>();
	}
	
	public void addObject(String columnName,Object object) {
		ArrayList<Object> listIntern = listResult.get(columnName);
		if(listIntern == null) {
			listIntern = new ArrayList<>();
		}
		listIntern.add(object);
		listResult.put(columnName, listIntern);
	}
	
	public ArrayList<Object> getListObjectFromColumn(String columnName) {
		return listResult.get(columnName);
	}
}
