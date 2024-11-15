package dao;

import util.ConnectionPool;

import java.util.ArrayList;

import model.Member;
public interface MemberDAOFunction<T> {
	public boolean addMember(Member item);
	public boolean editMember(Member item);
	public boolean delMember(Member item);
	public boolean delMemberByID(int var1);
	ArrayList<T> getMemByName(String var2);
	T getMemByID(int var3);
	ArrayList<T> getMemList();
	ArrayList<T> getListM();
	public ArrayList<Member> getMembers( int at, int total);
	
	ConnectionPool getCP();
	void releaseConnection();
}
