package ca.como89.myapi.api.queries;

import ca.como89.myapi.api.exceptions.NotValidColumnException;

public class UpdateQuery extends ConditionQuery {

	public UpdateQuery() {
		super(QueryType.UPDATE);
	}
	
	@Override
	public boolean addColumn(String columnName,Object value) throws NotValidColumnException {
		if(!validColumn(columnName))
			throwsExceptionNotValidColumn();
		if(existColumn(columnName))
			return false;
		listColumns.put(columnName, value);
		return true;
	}
	
	@Override
	public boolean setColumn(String columnName,Object value) throws NotValidColumnException {
		if(!validColumn(columnName))
			throwsExceptionNotValidColumn();
		if(existColumn(columnName))
			return false;
		listColumns.put(columnName, value);
		return true;
	}

	
}
