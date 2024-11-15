package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Author;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

public class AuthorFunctionImpl implements AuthorFunction<Author> {

    private Connection con;
    private ConnectionPool cp;
    
    public AuthorFunctionImpl(ConnectionPool cp) {
        if (cp == null) {
            this.cp = new ConnectionPoolImpl();
        } else {
            this.cp = cp;
        }

        try {
            this.con = this.cp.getConnection("Author ");
            if (this.con.getAutoCommit()) {
                this.con.setAutoCommit(false);
            }
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }
	
	private boolean exe(PreparedStatement pre) {
		//pre  - đã được biên dịch và truyền giá trị đầy đủ cho các tham số
		if(pre!=null) {
			try {
				int results = pre.executeUpdate();
				if(results==0) {
					this.con.rollback();
					return false;
				}
				
				//xác nhận thực thi sau cùng 
				this.con.commit();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				
				try {
					this.con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}finally{
				pre = null;
			}
		}
		return false;
	}
	@Override
	public boolean addAuthor(Author item) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO authors (");
		sql.append("author_name, author_biography) ");
		sql.append("VALUES ( ?, ?);");


		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1,item.getAuthor_name());
			pre.setString(2,item.getAuthor_biography());

			
			return this.exe(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean deleteAuthor(Author item) {
		String sql = "DELETE FROM authors WHERE author_id=?;";
		try {
		PreparedStatement pre = this.con.prepareStatement(sql);
		pre.setInt(1, item.getAuthor_id());
		return this.exe(pre);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean editAuthor(Author item) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE authors SET ");
		sql.append("author_name=? ,author_biography=?");
		sql.append("WHERE author_id = ?;");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1,item.getAuthor_name());
			pre.setString(2,item.getAuthor_biography());
            pre.setInt(3, item.getAuthor_id());
			return this.exe(pre);
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Author getAuthorById(int var1) {
		String sql = "SELECT * FROM authors ";
    	sql += "WHERE author_id = ?";
    	
    	Author item = new Author();
    	try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, var1);
			
			ResultSet rs = pre.executeQuery();
			
			if (rs.next()) {
				item.setAuthor_id(rs.getInt("author_id"));
				item.setAuthor_name(rs.getNString("author_name"));
				item.setAuthor_biography(rs.getNString("author_biography"));
            }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return item;
	}

	@Override
	public ArrayList<Author> getAuthorByName(String var1) {
		String sql = "SELECT * FROM authors WHERE author_name LIKE ?";
        ArrayList<Author> authors = new ArrayList<>();
        
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, "%" + var1 + "%");

            ResultSet rs = pre.executeQuery();
            
            if (rs != null) {
                while(rs.next()) {
    				Author item = new Author();
    				item.setAuthor_id(rs.getInt("author_id"));
    				item.setAuthor_name(rs.getNString("author_name"));
    				item.setAuthor_biography(rs.getNString("author_biography"));
    				authors.add(item);
                }

                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return authors;
	}

	@Override
	public ArrayList<Author> getListAuthor(Author var1, int at, int total) {
		String sql = "SELECT * FROM authors ";
		sql += "";
		sql += "ORDER BY author_id ASC ";
		sql += "LIMIT "+at+","+total;
		//bien dich cau lenh
		ArrayList<Author> list = new ArrayList<>();
		Author item = null;
		try {
		PreparedStatement pre = this.con.prepareStatement(sql);
		ResultSet rs = pre.executeQuery();
		if(rs != null) {
			while(rs.next()) {
				item = new Author();
				item.setAuthor_id(rs.getInt("author_id"));
				item.setAuthor_name(rs.getNString("author_name"));
				item.setAuthor_biography(rs.getNString("author_biography"));
				list.add(item);
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.con.rollback();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	@Override
    public ConnectionPool getCP() {
        return this.cp;
    }

	@Override
    public void releaseConnection() {
        try {
            this.cp.releaseConnection(this.con, "Author");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }
	
	@Override
	public boolean deleteAuthorByID(int author_id) {
		String sql = "DELETE FROM authors ";
    	sql += "WHERE author_id = ?";
    	
    	try {
    		
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, author_id);
			return this.exe(pre);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
	}

	@Override
	public ArrayList<Author> getAuthorList() {
		ArrayList<Author> list = new ArrayList();
        String sql = "SELECT * FROM authors;";

        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs != null) {
                while(rs.next()) {
                	Author item = new Author();
    				item.setAuthor_id(rs.getInt("author_id"));
    				item.setAuthor_name(rs.getString("author_name"));
    				item.setAuthor_biography(rs.getString("author_biography"));
                    list.add(item);
                }
                rs.close();
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return list;
	}

}
