package ca.como89.myapi.api.queries;

import ca.como89.myapi.api.exceptions.NotValidColumnException;

public class InsertQuery extends Query {
	
	public InsertQuery() {
		super(QueryType.INSERT);
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
