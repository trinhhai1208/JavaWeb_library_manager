

import java.util.ArrayList;

import util.ConnectionPool;

public interface BookFunction<T> {
	
	int addAuthor(String name);
	
	int addCategory(String name);
	
	boolean addT(T var1);

	boolean editT(T var1);

	boolean deleteT(int id);

	T getTById(int var1);

	ArrayList<T> getTByTitle(String var1);

	ArrayList<T> getListT(T var1, int var2, int var3);

	ArrayList<T> getListT();
	
	ConnectionPool getCP();

	void releaseConnection();
	 
}
