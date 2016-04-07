package ca.como89.myapi.api.queries;

import ca.como89.myapi.api.exceptions.NotValidColumnException;

/**
 * This class is when you need to select values in a table.
 */
public class SelectQuery extends ConditionQuery {
	
	private ResultObjects resultObjects; 

	public SelectQuery() {
		resultObjects = null;
	}
	
	/**
	 * See addColumn(String) method in @Query class.
	 */
	@Override
	public boolean addColumn(String columnName) throws NotValidColumnException {
		if(!validColumn(columnName))
			throwsExceptionNotValidColumn();
		if(existColumn(columnName))
			return false;
		listColumns.put(columnName, null);
		return true;
	}
	
	/**
	 * This method return the Result objects with all data request by the query.
	 * @return a @ResultObjects class.
	 */
	public ResultObjects getResultObjects(){
		ResultObjects result = resultObjects;
		if(result == null) {
			resultObjects = new ResultObjects();
			result = resultObjects;
		} else {
			resultObjects = null;
		}
	    return result;
	}

}
