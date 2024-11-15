package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Book;
import model.Loan;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

public class LoanFuntionImpl implements LoanFunction {

	private Connection con;
	private ConnectionPool cp;

	public LoanFuntionImpl(ConnectionPool cp) {
		if (cp == null) {
			this.cp = new ConnectionPoolImpl();
		} else {
			this.cp = cp;
		}

		try {
			this.con = this.cp.getConnection("Book ");
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException var3) {
			var3.printStackTrace();
		}

	}

	@Override
	public ArrayList<Loan> getAllLoan() {
		
		ArrayList<Loan> loans = new ArrayList<Loan>();
		
	    String sql = 
	    		  "SELECT "
	    		+ "    Loans.loan_id, "
	    		+ "    Loans.book_id, "
	    		+ "    Loans.member_id, "
	    		+ "    Members.member_name, "
	    		+ "    Books.book_title, "
	    		+ "    Loans.loan_date, "
	    		+ "    Loans.loan_due_date, "
	    		+ "    Loans.loan_return_date "
	    		+ "FROM "
	    		+ "    Loans "
	    		+ "JOIN "
	    		+ "    Members ON Loans.member_id = Members.member_id "
	    		+ "JOIN "
	    		+ "    Books ON Loans.book_id = Books.book_id "
	    		+ "WHERE "
	    		+ "    Loans.loan_return_date IS NULL";
		
		try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs != null) {
                while(rs.next()) {
                	Loan item = new Loan();
                    item.setLoan_id(rs.getInt("loan_id"));
                    item.setBook_id(rs.getInt("book_id")); // bạn có thể giữ lại nếu cần, nhưng truy vấn hiện tại không trả về book_id
                    item.setMember_id(rs.getInt("member_id")); // bạn có thể giữ lại nếu cần, nhưng truy vấn hiện tại không trả về member_id
                    item.setLoan_date(rs.getDate("loan_date"));
                    item.setLoan_due_date(rs.getDate("loan_due_date"));
                    item.setLoan_return_date(rs.getDate("loan_return_date"));
                    item.setBook_title(rs.getString("book_title")); // thiết lập book_title
                    item.setMember_name(rs.getString("member_name")); // thiết lập member_name
                    loans.add(item);
                }

                rs.close();
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }
		// TODO Auto-generated method stub
		return loans;
	}
	
	

	@Override
	public ArrayList<Loan> getAllByMemberId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addLoan(Loan loan) {
		String insertLoanSQL = "INSERT INTO Loans (book_id, member_id, loan_date, loan_due_date, loan_return_date) VALUES (?, ?, ?, ?, ?)";
        String updateBookSQL = "UPDATE Books SET book_quantity = book_quantity - 1 WHERE book_id = ?";
        
        try {
            PreparedStatement insertStmt = this.con.prepareStatement(insertLoanSQL);
            PreparedStatement updateStmt = this.con.prepareStatement(updateBookSQL);
            
            this.con.setAutoCommit(false);
            
            insertStmt.setInt(1, loan.getBook_id());
            insertStmt.setInt(2, loan.getMember_id());
            insertStmt.setDate(3, loan.getLoan_date());
            insertStmt.setDate(4, loan.getLoan_due_date());
            insertStmt.setDate(5, loan.getLoan_return_date());
            
            int rowsInserted = insertStmt.executeUpdate();
            
            updateStmt.setInt(1, loan.getBook_id());
            
            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsInserted > 0 && rowsUpdated > 0) {
                con.commit();
                return true;
            } else {
                con.rollback(); 
                return false;
            }
        } catch (SQLException e) {
            try {
                con.rollback(); 
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace(); 
            }
            e.printStackTrace(); 
            return false;
        } finally {
            try {
                con.setAutoCommit(true); 
            } catch (SQLException e) {
                e.printStackTrace(); 
            }
        }
	}

	@Override
	public boolean returnBook(int loan_id) {
        String selectLoanByIdSQL = "SELECT book_id FROM Loans WHERE loan_id = ?";
        String returnBookSQL = "UPDATE Loans SET loan_return_date = NOW() WHERE loan_id = ?";
        String updateBookSQL = "UPDATE Books SET book_quantity = book_quantity + 1 WHERE book_id = ?";

        try (PreparedStatement selectLoanByIdStmt = con.prepareStatement(selectLoanByIdSQL);
             PreparedStatement returnBookStmt = con.prepareStatement(returnBookSQL);
             PreparedStatement updateBookStmt = con.prepareStatement(updateBookSQL)) {
            
            con.setAutoCommit(false);
            
            selectLoanByIdStmt.setInt(1, loan_id);
            ResultSet rs = selectLoanByIdStmt.executeQuery();
            int book_id = 0;
            if (rs.next()) {
                book_id = rs.getInt("book_id");
            } else {
                con.rollback();
                return false; 
            }

            returnBookStmt.setInt(1, loan_id);
            int rowsReturnBook = returnBookStmt.executeUpdate();

            updateBookStmt.setInt(1, book_id);
            int rowsUpdateBook = updateBookStmt.executeUpdate();

            if (rowsReturnBook > 0 && rowsUpdateBook > 0) {
                con.commit();
                return true;
            } else {
                con.rollback(); 
                return false;
            }

        } catch (SQLException e) {
            try {
                con.rollback(); 
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace(); 
            }
            e.printStackTrace(); 
            return false;
        } finally {
            try {
                con.setAutoCommit(true); 
            } catch (SQLException e) {
                e.printStackTrace(); 
            }
        }
    }


	@Override
	public ConnectionPool getCP() {
		return this.cp;
	}

	@Override // Trả kết nối
	public void releaseConnection() {
		try {
			this.cp.releaseConnection(this.con, "Book");
		} catch (SQLException var2) {
			var2.printStackTrace();
		}

	}

	private boolean exe(PreparedStatement pre) {
		if (pre == null) {
			return false;
		} else {
			try {
				int result = pre.executeUpdate();
				if (result != 0) {
					this.con.commit();
					return true;
				}

				this.con.rollback();
				return false;
			} catch (SQLException var9) {
				var9.printStackTrace();
				try {
					this.con.rollback();
				} catch (SQLException var8) {
					var8.printStackTrace();
				}
			} finally {
				pre = null;
			}
			return false;
		}
	}
	


	// liệt kê các cuốn sách quá hạn
	@Override
	public ArrayList<Loan> overdueBooks() {
	    ArrayList<Loan> overdueLoans = new ArrayList<>();
	    String sql = "SELECT l.loan_id, l.book_id, l.member_id, l.loan_date, l.loan_due_date, l.loan_return_date, " +
	                 "b.book_title, m.member_name " +
	                 "FROM Loans l " +
	                 "JOIN Books b ON l.book_id = b.book_id " +
	                 "JOIN Members m ON l.member_id = m.member_id " +
	                 "WHERE l.loan_due_date < CURDATE() AND l.loan_return_date IS NULL";
	    
	    try {
	        PreparedStatement pre = con.prepareStatement(sql);
	        ResultSet rs = pre.executeQuery();

	        while (rs.next()) {
	            Loan loan = new Loan();
	            loan.setLoan_id(rs.getInt("loan_id"));
	            loan.setBook_id(rs.getInt("book_id"));
	            loan.setMember_id(rs.getInt("member_id"));
	            loan.setLoan_date(rs.getDate("loan_date"));
	            loan.setLoan_due_date(rs.getDate("loan_due_date"));
	            loan.setLoan_return_date(rs.getDate("loan_return_date"));
	            loan.setBook_title(rs.getString("book_title"));
	            loan.setMember_name(rs.getString("member_name"));

	            overdueLoans.add(loan);
	        }

	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return overdueLoans;
	}



	// xem lịch sử mượn sách của người dùng
	@Override
	public ArrayList<Loan> seeBookBorrowingHistory(int member_id) {
		ArrayList<Loan> loans = new ArrayList<Loan>();
		String sql = 
			    "SELECT " +
			    "    l.loan_id, l.book_id, l.member_id, l.loan_date, l.loan_due_date, l.loan_return_date, " +
			    "    b.book_title, m.member_name " +
			    "FROM " +
			    "    Loans l " +
			    "JOIN " +
			    "    Books b ON l.book_id = b.book_id " +
			    "JOIN " +
			    "    Members m ON l.member_id = m.member_id " +
			    "WHERE " +
			    "    l.member_id = ?";
		try {
	        PreparedStatement pre = con.prepareStatement(sql);
	        pre.setInt(1, member_id);
	        ResultSet rs = pre.executeQuery();

	        while (rs.next()) {
	            Loan loan = new Loan();
	            loan.setLoan_id(rs.getInt("loan_id"));
	            loan.setBook_id(rs.getInt("book_id"));
	            loan.setMember_id(rs.getInt("member_id"));
	            loan.setLoan_date(rs.getDate("loan_date"));
	            loan.setLoan_due_date(rs.getDate("loan_due_date"));
	            loan.setLoan_return_date(rs.getDate("loan_return_date"));
	            loan.setBook_title(rs.getString("book_title"));
	            loan.setMember_name(rs.getString("member_name"));

	            loans.add(loan);
	        }

	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		return loans;
	}

	// tính tổng số cuốn sách mà người dùng đang mượn
//	@Override
	public int checkTheNumberOfUnpaidBooks(int member_id) {
	    String sql = "SELECT COUNT(*) AS total_unreturned_loans FROM Loans WHERE member_id = ? AND loan_return_date IS NULL";
	    int totalUnreturnedLoans = 0;
	    
	    try {
	        PreparedStatement pre = this.con.prepareStatement(sql);
	        pre.setInt(1, member_id);
	        ResultSet rs = pre.executeQuery();
	        
	        if (rs.next()) {
	            totalUnreturnedLoans = rs.getInt("total_unreturned_loans");
	        }
	        
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return totalUnreturnedLoans;
	}


	
	
	public ArrayList<Loan> findPeopleToBorrowBooks(int book_id) {
		// TODO Auto-generated method stub
		ArrayList<Loan> loans = new ArrayList<Loan>();
		String sql = 
			    "SELECT " +
			    "    l.loan_id, l.book_id, l.member_id, l.loan_date, l.loan_due_date, l.loan_return_date, " +
			    "    b.book_title, m.member_name " +
			    "FROM " +
			    "    Loans l " +
			    "JOIN " +
			    "    Books b ON l.book_id = b.book_id " +
			    "JOIN " +
			    "    Members m ON l.member_id = m.member_id " +
			    "WHERE " +
			    "    l.book_id = ? " + 
			    "AND " +
			    "l.loan_return_date IS NULL";
		try {
	        PreparedStatement pre = con.prepareStatement(sql);
	        pre.setInt(1, book_id);
	        ResultSet rs = pre.executeQuery();

	        while (rs.next()) {
	            Loan loan = new Loan();
	            loan.setLoan_id(rs.getInt("loan_id"));
	            loan.setBook_id(rs.getInt("book_id"));
	            loan.setMember_id(rs.getInt("member_id"));
	            loan.setLoan_date(rs.getDate("loan_date"));
	            loan.setLoan_due_date(rs.getDate("loan_due_date"));
	            loan.setLoan_return_date(rs.getDate("loan_return_date"));
	            loan.setBook_title(rs.getString("book_title"));
	            loan.setMember_name(rs.getString("member_name"));

	            loans.add(loan);
	        }

	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		return loans;
	}
	
	public Integer numberOfBookBorrowingsThisMonth() {
		String sql = 
					"SELECT "
				+ 	"		COUNT(*) AS total_loans "
				+ 	"FROM "
				+ 	"		Loans "
				+ 	"WHERE "
				+ 	"		MONTH(loan_date) = MONTH(CURRENT_DATE())"
				+ 	"AND "
				+ 	"		YEAR(loan_date) = YEAR(CURRENT_DATE())";
		
        Integer totalLoans = null;
        	
        try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            	totalLoans = rs.getInt("total_loans");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalLoans;
	}
	
public static void main(String[] args) {
	ConnectionPool cp = new ConnectionPoolImpl();
	LoanFunction lf = new LoanFuntionImpl(cp);
	
	System.out.println(lf.numberOfBookBorrowingsPreviousMonth());
}
	public Integer numberOfBookBorrowingsPreviousMonth() {
		String sql = "SELECT " + "		COUNT(*) AS total_loans " + "FROM " + "		Loans " + "WHERE "
				+ "		MONTH(loan_date) = MONTH(CURRENT_DATE()) - 1 " + "AND "
				+ "		YEAR(loan_date) = YEAR(CURRENT_DATE())";

		Integer totalLoans = null;

		try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				totalLoans = rs.getInt("total_loans");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalLoans;
	}
	

	@Override
	public List<Integer> takeTheBorrowedTurnOfTheMonths() {
		List<Integer> data = new ArrayList<Integer>();
		
        LocalDate currentDate = LocalDate.now();
        int curcurrentMonth = currentDate.getMonthValue();
        for (int i = 1; i <= curcurrentMonth; i++) {
        	String sql = 
        				"SELECT "
    				+ 	"		count(*) AS total_loans_of_month "
        			+ 	"FROM "
        			+ 	"		Loans "
        			+ 	"WHERE "
        			+ 	"		MONTH(loan_date) = " + i
        			+ 	" AND "
        			+ 	"		YEAR(loan_date) = YEAR(CURRENT_DATE())";
        	Integer totalLoans = null;
        	
            try {
    			PreparedStatement stmt = this.con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                	totalLoans = rs.getInt("total_loans_of_month");
                	data.add(totalLoans);
                }
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
		// TODO Auto-generated method stub
		return data;
	}



}
