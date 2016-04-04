package ca.como89.myapi.api.queries;

import ca.como89.myapi.api.exceptions.NotValidColumnException;

/**
 * This class is when you need to insert values.
 */
public class InsertQuery extends Query {
	
	public InsertQuery() {
	}
	
	/**
	 * See addColumn(String,Object) method in @Query class.
	 */
	@Override
	public boolean addColumn(String columnName,Object value) throws NotValidColumnException {
		if(!validColumn(columnName))
			throwsExceptionNotValidColumn();
		if(existColumn(columnName))
			return false;
		listColumns.put(columnName, value);
		return true;
	}
	
	/**
	 * See setColumn(String,Object) method in @Query class.
	 */
	@Override
	public void setColumn(String columnName,Object value) throws NotValidColumnException {
		if(!validColumn(columnName))
			throwsExceptionNotValidColumn();
		listColumns.put(columnName, value);
	}
}
