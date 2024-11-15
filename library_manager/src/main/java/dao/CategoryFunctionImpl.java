package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Author;
import model.Category;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

public class CategoryFunctionImpl implements CategoryFunction{
    private Connection con;
    private ConnectionPool cp;
    
    public CategoryFunctionImpl(ConnectionPool cp) {
        if (cp == null) {
            this.cp = new ConnectionPoolImpl();
        } else {
            this.cp = cp;
        }

        try {
            this.con = this.cp.getConnection("Category ");
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
	public boolean addCategory(Category item) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO categories (");
		sql.append("category_name, category_description) ");
		sql.append("VALUES ( ?, ?);");


		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1,item.getCategory_name());
			pre.setString(2,item.getCategory_description());
			
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
	public boolean deleteCategory(Category item) {
		String sql = "DELETE FROM categories WHERE category_id=?;";
		try {
		PreparedStatement pre = this.con.prepareStatement(sql);
		pre.setInt(1, item.getCategory_id());
		return this.exe(pre);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCategoryByID(int var1) {
		String sql = "DELETE FROM categories ";
    	sql += "WHERE category_id = ?";
    	
    	try {
    		
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, var1);
			return this.exe(pre);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
	}

	@Override
	public boolean editCategory(Category item) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE categories SET ");
		sql.append("category_name=? ,category_description=? ");
		sql.append("WHERE category_id = ?;");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1,item.getCategory_name());
			pre.setString(2,item.getCategory_description());
            pre.setInt(3, item.getCategory_id());

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
	public Category getCategoryById(int var1) {
		String sql = "SELECT * FROM categories ";
    	sql += "WHERE category_id = ?";
    	
    	Category item = new Category();
    	try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, var1);
			
			ResultSet rs = pre.executeQuery();
			
			if (rs.next()) {
				item.setCategory_id(rs.getInt("category_id"));
				item.setCategory_name(rs.getNString("category_name"));
				item.setCategory_description(rs.getNString("category_description"));
            }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return item;
	}

	@Override
	public ArrayList getCategoryList() {
        ArrayList<Category> list = new ArrayList();
        String sql = "SELECT * FROM categories;";

        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs != null) {
                while(rs.next()) {
                	Category item = new Category();
    				item.setCategory_id(rs.getInt("category_id"));
    				item.setCategory_name(rs.getNString("category_name"));
    				item.setCategory_description(rs.getNString("category_description"));
                    list.add(item);
                    
                }
                rs.close();
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return list;
	}

	@Override
	public ArrayList getCategoryByName(String var1) {
        String sql = "SELECT * FROM categories WHERE category_name LIKE ?";
        ArrayList<Category> categories = new ArrayList<>();
        
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, "%" + var1 + "%");

            ResultSet rs = pre.executeQuery();
            
            if (rs != null) {
                while(rs.next()) {
                	Category item = new Category();
    				item.setCategory_id(rs.getInt("category_id"));
    				item.setCategory_name(rs.getNString("category_name"));
    				item.setCategory_description(rs.getNString("category_description"));
    				categories.add(item);
                }

                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return categories;
	}

	@Override
	public ArrayList getListCategory(Object var1, int at, int total) {
        ArrayList<Category> categories = new ArrayList();
        Category item = null;
        String sql = "SELECT * FROM members";
        sql = sql + "ORDER BY member_id DESC ";
        sql = sql + "LIMIT " + at + ", " + total;

        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs != null) {
                while(rs.next()) {
                    item = new Category();
                    item.setCategory_id(rs.getInt("category_id"));
    				item.setCategory_name(rs.getNString("category_name"));
    				item.setCategory_description(rs.getNString("category_description"));
    				categories.add(item);
                }

                rs.close();
            }
        } catch (SQLException e5) {
            e5.printStackTrace();
        }

        return categories;
	}
	
	@Override
    public ConnectionPool getCP() {
        return this.cp;
    }

	@Override
    public void releaseConnection() {
        try {
            this.cp.releaseConnection(this.con, "Category");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }

    }

}
