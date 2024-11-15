package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.Loan;
import util.ConnectionPool;

public interface LoanFunction {
	
	ArrayList<Loan> getAllLoan();
	
	ArrayList<Loan> getAllByMemberId();
	
	ArrayList<Loan> overdueBooks();
	
	ArrayList<Loan> seeBookBorrowingHistory(int member_id);
	ArrayList<Loan> findPeopleToBorrowBooks(int book_id);
	
	boolean addLoan(Loan loan);
	
	boolean returnBook(int loan_id);
	
	int checkTheNumberOfUnpaidBooks(int member_id);
	
	ConnectionPool getCP();

	void releaseConnection();
	
	Integer numberOfBookBorrowingsThisMonth();
	
	Integer numberOfBookBorrowingsPreviousMonth();
	
	List<Integer> takeTheBorrowedTurnOfTheMonths();

}
