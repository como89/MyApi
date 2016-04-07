package ca.como89.myapi.api;

/**
 * This enum class is when the method return a ApiResponse. This response is to know if the method works or not.
 *
 */
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
	 * DATABASE_NOT_CONNECT, if the method cannot be use, because the library is not connected to mysql server / SQL file.
	 */
	DATABASE_NOT_CONNECT,
	/**
	 * DATABASE_CONNECTED, if the library is connected to mysql server / SQL file.
	 */
	DATABASE_CONNECTED;
}
