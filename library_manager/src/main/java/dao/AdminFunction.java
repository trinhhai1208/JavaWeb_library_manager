package dao;

import util.ConnectionPool;

public interface AdminFunction {
	
	public boolean login(String username, String password);
	
	ConnectionPool getCP();

	void releaseConnection();

}
