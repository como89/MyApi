package ca.como89.myapi.api.queries;

/**
 * This class is when you need to count the rows in a table.
 */
public class CountRowsQuery extends ConditionQuery {
	
	private int nbRows; 

	public CountRowsQuery() {
		nbRows = 0;
	}
	
	public void setNbRows(int nbRows) {
		this.nbRows = nbRows;
	}
	
	/**
	 * This method return the rows count in the table.
	 * @return
	 */
	public int getNbRows() {
		return nbRows;
	}

}
