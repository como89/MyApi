package ca.como89.myapi.api;

public enum ApiResponse {

	/**
	 * ERROR, if the method have a sql error.
	 */
	ERROR,
	/**
	 * SUCCESS, if the method success.
	 */
	SUCCESS,
	/**
	 * MYSQL_NOT_CONNECT, if the method cannot be use, because the library is not connected to mysql server.
	 */
	MYSQL_NOT_CONNECT;
}
