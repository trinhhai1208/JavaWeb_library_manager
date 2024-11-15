package dao;

import java.util.ArrayList;

import model.Category;
import util.ConnectionPool;

public interface CategoryFunction<T>{
	boolean addCategory(Category item);
	boolean deleteCategory(Category item);
	public boolean deleteCategoryByID(int var1);
	boolean editCategory(Category item);

	T getCategoryById(int var1);
	ArrayList<T> getCategoryList();

	ArrayList<T> getCategoryByName(String var1);
	ArrayList<T> getListCategory(T var1, int var2, int var3);
	
	ConnectionPool getCP();

	void releaseConnection();
}
