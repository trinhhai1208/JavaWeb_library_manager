package dao;

import java.util.ArrayList;

import model.Book;
import model.Member;
import util.ConnectionPool;

public interface BookFunction {
	
	int addAuthor(String name);
	
	int addCategory(String name);
	
	boolean addT(Book var1);

	boolean editT(Book var1);

	boolean deleteT(int id);

	Book getTById(int var1);

	ArrayList<Book> getTByTitle(String var1);
	ArrayList<Book> getByAuthor(String var1);
	ArrayList<Book> getByCategory(String var1);

	ArrayList<Book> getListT(Book var1, int var2, int var3);

	ArrayList<Book> getListT();
	
	ConnectionPool getCP();

	void releaseConnection();
	
	Integer getTheNumberOfMembers();
	Integer getTheNumberOfBooks();
	ArrayList<Member> memberVIP();
	
}
