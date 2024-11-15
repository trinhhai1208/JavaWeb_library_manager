package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Book;
import model.Member;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

public class BookFunctionImpl implements BookFunction {

	private Connection con;
	private ConnectionPool cp;

	public BookFunctionImpl(ConnectionPool cp) {
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

	public int addAuthor(String name) {
		String sql = "SELECT author_name FROM Authors ";
		sql += "WHERE author_name = ?";

		int author_id = 0;
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, name);

			ResultSet rs = pre.executeQuery();
			String nameAuthor = null;
			if (rs.next()) {
				nameAuthor = rs.getString(1); // Lấy giá trị từ cột đầu tiên của kết quả
			}
			rs.close();

			if (nameAuthor == null) {
				String sqlAdd = "INSERT INTO Authors( ";
				sqlAdd += "author_name, author_biography) ";
				sqlAdd += "VALUES (?, ?)";

				pre = this.con.prepareStatement(sqlAdd);
				pre.setString(1, name);
				pre.setString(2, "");

				this.exe(pre);
			}

			String sqlFindId = "SELECT author_id FROM Authors ";
			sqlFindId += "WHERE author_name = ?";

			pre = this.con.prepareStatement(sqlFindId);
			pre.setString(1, name);
			ResultSet rsx = pre.executeQuery();

			if (rsx.next()) {
				author_id = rsx.getInt(1); // Lấy giá trị từ cột đầu tiên của kết quả
			}
			rsx.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return author_id;
	}

	public int addCategory(String name) {
		String sql = "SELECT category_name FROM Categories ";
		sql += "WHERE category_name = ?";

		int category_id = 0;
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, name);

			ResultSet rs = pre.executeQuery();
			String nameCategory = null;
			if (rs.next()) {
				nameCategory = rs.getString(1); // Lấy giá trị từ cột đầu tiên của kết quả
			}
			rs.close();

			if (nameCategory == null) {
				String sqlAdd = "INSERT INTO Categories( ";
				sqlAdd += "category_name, category_description) ";
				sqlAdd += "VALUES (?, ?)";

				pre = this.con.prepareStatement(sqlAdd);
				pre.setString(1, name);
				pre.setString(2, "");

				this.exe(pre);
			}

			String sqlFindId = "SELECT category_id FROM Categories ";
			sqlFindId += "WHERE category_name = ?";

			pre = this.con.prepareStatement(sqlFindId);
			pre.setString(1, name);
			ResultSet rsx = pre.executeQuery();

			if (rsx.next()) {
				category_id = rsx.getInt(1); // Lấy giá trị từ cột đầu tiên của kết quả
			}
			rsx.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category_id;
	}

	// Thêm
	public boolean addT(Book var1) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO Books(");
		sql.append("book_title, book_isbn, ");
		sql.append("author_id, category_id, book_published_year, ");
		sql.append("book_number_of_copies, ");
		sql.append("book_quantity)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, var1.getBook_title());
			pre.setString(2, var1.getBook_isbn());
			pre.setInt(3, var1.getAuthor_id());
			pre.setInt(4, var1.getCategory_id());
			pre.setInt(5, var1.getBook_published_year());
			pre.setInt(6, var1.getBook_number_of_copies());
			pre.setInt(7, var1.getBook_quantity());
			return this.exe(pre);
		} catch (SQLException var4) {
			var4.printStackTrace();
			return false;
		}
	}

	// Sửa
	public boolean editT(Book var1) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE Books ");
		sql.append("SET ");
		sql.append("book_title = ?, book_isbn = ?, ");
		sql.append("author_id = ?, category_id = ?, book_published_year = ?, ");
		sql.append("book_number_of_copies = ?, book_quantity = ? ");
		sql.append("WHERE book_id = ?;");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, var1.getBook_title());
			pre.setString(2, var1.getBook_isbn());
			pre.setInt(3, var1.getAuthor_id());
			pre.setInt(4, var1.getCategory_id());
			pre.setInt(5, var1.getBook_published_year());
			pre.setInt(6, var1.getBook_number_of_copies());
			pre.setInt(7, var1.getBook_quantity());
			pre.setInt(8, var1.getBook_id());
			return this.exe(pre);
		} catch (SQLException var4) {
			var4.printStackTrace();
			return false;
		}
	}

	public boolean deleteT(int id) {
		String sql = "DELETE FROM Books ";
		sql += "WHERE book_id = ?";

		try {

			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, id);
			return this.exe(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Book getTById(int var1) {
		String sql = "SELECT b.*, a.author_name " + "FROM Books b " + "JOIN Authors a ON b.author_id = a.author_id "
				+ "WHERE b.book_id = ?";

		Book item = new Book();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, var1); // Thiết lập tham số cho book_id

			ResultSet rs = pre.executeQuery();

			if (rs.next()) {
				item.setBook_id(rs.getInt("book_id"));
				item.setBook_title(rs.getString("book_title"));
				item.setBook_isbn(rs.getString("book_isbn"));
				item.setAuthor_id(rs.getInt("author_id"));
				item.setCategory_id(rs.getInt("category_id"));
				item.setBook_published_year(rs.getInt("book_published_year"));
				item.setBook_number_of_copies(rs.getInt("book_number_of_copies"));
				item.setAuthor_name(rs.getString("author_name"));

				// In ra thông tin để kiểm tra
				System.out.println("Book ID: " + item.getBook_id());
				System.out.println("Book Title: " + item.getBook_title());
				System.out.println("Book ISBN: " + item.getBook_isbn());
				System.out.println("Author ID: " + item.getAuthor_id());
				System.out.println("Author Name: " + item.getAuthor_name());
				// Các thuộc tính khác tương tự...

			} else {
				System.out.println("Không tìm thấy sách với book_id = " + var1);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}



	public ArrayList<Book> getTByTitle(String title) {
		String sql = "SELECT * FROM Books WHERE book_title LIKE ?";
		ArrayList<Book> books = new ArrayList<>();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, "%" + title + "%"); // Sử dụng wildcard để tìm kiếm tiêu đề sách

			ResultSet rs = pre.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					Book item = new Book();
					item.setBook_id(rs.getInt("book_id"));
					item.setBook_title(rs.getString("book_title"));
					item.setBook_isbn(rs.getString("book_isbn"));
					item.setBook_published_year(rs.getInt("book_published_year"));
					item.setBook_number_of_copies(rs.getInt("book_number_of_copies"));
					item.setBook_quantity(rs.getInt("book_quantity"));
					books.add(item);
				}

				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	public ArrayList<Book> getByAuthor(String var1) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM Books ");
		sql.append("WHERE author_id = (");
		sql.append("SELECT author_id FROM Authors ");
		sql.append("WHERE author_name = ?)");
		ArrayList<Book> books = new ArrayList<>();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, var1);
			ResultSet rs = pre.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					Book item = new Book();
					item.setBook_id(rs.getInt("book_id"));
					item.setBook_title(rs.getString("book_title"));
					item.setBook_isbn(rs.getString("book_isbn"));
					item.setBook_published_year(rs.getInt("book_published_year"));
					item.setBook_number_of_copies(rs.getInt("book_number_of_copies"));
					item.setBook_quantity(rs.getInt("book_quantity"));
					books.add(item);
				}

				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	public ArrayList<Book> getByCategory(String var1) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM Books ");
		sql.append("WHERE category_id = (");
		sql.append("SELECT category_id FROM Categories ");
		sql.append("WHERE category_name = ?)");
		ArrayList<Book> books = new ArrayList<>();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, var1);
			ResultSet rs = pre.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					Book item = new Book();
					item.setBook_id(rs.getInt("book_id"));
					item.setBook_title(rs.getString("book_title"));
					item.setBook_isbn(rs.getString("book_isbn"));
					item.setBook_published_year(rs.getInt("book_published_year"));
					item.setBook_number_of_copies(rs.getInt("book_number_of_copies"));
					item.setBook_quantity(rs.getInt("book_quantity"));
					books.add(item);
				}

				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	// Lấy danh sách sách nhưng phân trang
	public ArrayList<Book> getListT(Book var1, int at, int total) {
		ArrayList<Book> books = new ArrayList();
		Book item = null;
		String sql = "SELECT * FROM Books";
		sql = sql + "ORDER BY book_id DESC ";
		sql = sql + "LIMIT " + at + ", " + total;

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					item = new Book();
					item.setBook_id(rs.getInt("book_id"));
					item.setBook_title(rs.getString("book_title"));
					item.setBook_isbn(rs.getString("book_isbn"));
					item.setBook_published_year(rs.getInt("book_published_year"));
					item.setBook_number_of_copies(rs.getInt("book_number_of_copies"));
					item.setBook_quantity(rs.getInt("book_quantity"));
					books.add(item);
				}

				rs.close();
			}
		} catch (SQLException var9) {
			var9.printStackTrace();
		}

		return books;
	}

	// Lấy danh sách sách
	public ArrayList<Book> getListT() {
		ArrayList<Book> books = new ArrayList();
		Book item = null;
		String sql = "SELECT * FROM Books";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					item = new Book();
					item.setBook_id(rs.getInt("book_id"));
					item.setBook_title(rs.getString("book_title"));
					item.setBook_isbn(rs.getString("book_isbn"));
					item.setBook_published_year(rs.getInt("book_published_year"));
					item.setBook_number_of_copies(rs.getInt("book_number_of_copies"));
					item.setBook_quantity(rs.getInt("book_quantity"));
					books.add(item);
				}

				rs.close();
			}
		} catch (SQLException var6) {
			var6.printStackTrace();
		}

		return books;
	}

	public ConnectionPool getCP() {
		return this.cp;
	}

	// Trả kết nối
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

	@Override
	public Integer getTheNumberOfMembers() {
		String sql = "SELECT COUNT(*) AS total_members FROM Members";
		
        Integer totalMembers = null;
        	
        try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                totalMembers = rs.getInt("total_members");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalMembers;
	}
	
	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		BookFunction bf = new BookFunctionImpl(cp);

		ArrayList<Member> members = bf.memberVIP();
		members.forEach(m -> System.out.println(m.getMember_id() + " " + m.getTotal_loans()));
		
//		System.out.println(x + " " + y);

	}
	

	@Override
	public Integer getTheNumberOfBooks() {
		String sql = "SELECT COUNT(*) AS total_books FROM Books";
		
        Integer totalBooks = null;
        	
        try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            	totalBooks = rs.getInt("total_books");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalBooks;
	}

	@Override
	public ArrayList<Member> memberVIP() {
		ArrayList<Member> members = new ArrayList<Member>();
		Member item;
		String sql = 
                "SELECT "
              + "    m.member_id, "
              + "    m.member_name, "
              + "    m.member_phone, "
              + "    COUNT(l.loan_id) AS total_loans "
              + "FROM "
              + "    Loans l "
              + "JOIN "
              + "    Members m ON l.member_id = m.member_id "
              + "WHERE "
              + "    MONTH(l.loan_date) = MONTH(CURRENT_DATE()) "
              + "    AND YEAR(l.loan_date) = YEAR(CURRENT_DATE()) "
              + "GROUP BY "
              + "    m.member_id, m.member_name, m.member_phone "
              + "ORDER BY "
              + "    total_loans DESC "
              + "LIMIT 5;";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					item = new Member();
					item.setMember_id(rs.getInt("member_id"));
					item.setMember_name(rs.getString("member_name"));
					item.setMember_phone(rs.getString("member_phone"));
					item.setTotal_loans(rs.getInt("total_loans"));
					members.add(item);
				}

				rs.close();
			}
		} catch (SQLException var6) {
			var6.printStackTrace();
		}
		return members;
	}


}