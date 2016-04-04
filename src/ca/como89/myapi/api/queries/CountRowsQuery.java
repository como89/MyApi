package ca.como89.myapi.api.queries;

public class CountRowsQuery extends ConditionQuery {
	
	private int nbRows; 

	public CountRowsQuery() {
		super(QueryType.COUNT_ROWS);
		nbRows = 0;
	}
	
	public void setNbRows(int nbRows) {
		this.nbRows = nbRows;
	}
	
	public int getNbRows() {
		return nbRows;
	}

}
