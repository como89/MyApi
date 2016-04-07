package ca.como89.myapi.api.queries;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is all the result from a selectQuery.
 */
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
	/**
	 * This method will return a list of object from the column specified.
	 * @param columnName - The column name.
	 * @return a ArrayList with all values from the query. This arrayList is empty, if no value exist.
	 */
	public ArrayList<Object> getListObjectFromColumn(String columnName) {
		return listResult.get(columnName);
	}
}
