package dao;

import java.util.ArrayList;
import model.Author;
import util.ConnectionPool;


public interface AuthorFunction<T> {
	
	boolean addAuthor(Author item);
	boolean deleteAuthor(Author item);
	public boolean deleteAuthorByID(int var1);
	boolean editAuthor(Author item);

	T getAuthorById(int var1);
	ArrayList<T> getAuthorList();

	ArrayList<T> getAuthorByName(String var1);
	ArrayList<T> getListAuthor(T var1, int var2, int var3);
	
	ConnectionPool getCP();

	void releaseConnection();
	 
}
