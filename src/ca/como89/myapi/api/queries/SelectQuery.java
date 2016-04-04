package ca.como89.myapi.api.queries;

import ca.como89.myapi.api.exceptions.NotValidColumnException;

public class SelectQuery extends ConditionQuery {
	
	private ResultObjects resultObjects; 

	public SelectQuery() {
		super(QueryType.SELECT);
		resultObjects = null;
	}
	
	@Override
	public boolean addColumn(String columnName) throws NotValidColumnException {
		if(!validColumn(columnName))
			throwsExceptionNotValidColumn();
		if(existColumn(columnName))
			return false;
		listColumns.put(columnName, null);
		return true;
	}
	
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
