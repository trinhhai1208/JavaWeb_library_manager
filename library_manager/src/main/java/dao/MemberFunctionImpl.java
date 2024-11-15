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

public class MemberFunctionImpl implements MemberDAOFunction<Member> {
	
	//doi tuong ket noi de lam viec
    private Connection con;
    private ConnectionPool cp;

    public MemberFunctionImpl(ConnectionPool cp) {
        if (cp == null) {
            this.cp = new ConnectionPoolImpl();
        } else {
            this.cp = cp;
        }

        try {
            this.con = this.cp.getConnection("Member ");
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
	public boolean addMember(Member item) {
		
//		if(this.isExisting(item)) {
//			return false;
//		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO members (");
		sql.append(" member_name, member_address, member_phone,  member_email) ");
		sql.append("VALUES ( ?, ?, ?, ?);");


		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1,item.getMember_name());
			pre.setString(2,item.getMember_address());
			pre.setString(3,item.getMember_phone());
			pre.setString(4,item.getMember_email());

			
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
	public boolean editMember(Member item) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE members SET ");
		sql.append("member_name=? ,member_address=?, member_phone=?, member_email=? ");
		sql.append("WHERE member_id = ?;");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1,item.getMember_name());
			pre.setString(2,item.getMember_address());
			pre.setString(3,item.getMember_phone());
			pre.setString(4,item.getMember_email());
            pre.setInt(5, item.getMember_id());

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
	public boolean delMember(Member item) {
		String sql = "DELETE FROM members WHERE member_id=?;";
		try {
		PreparedStatement pre = this.con.prepareStatement(sql);
		pre.setInt(1, item.getMember_id());
		return this.exe(pre);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	

	@Override
	public boolean delMemberByID(int mem_id) {
    	String sql = "DELETE FROM members ";
    	sql += "WHERE member_id = ?";
    	
    	try {
    		
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, mem_id);
			return this.exe(pre);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }
	
	
    // Lay ds member
    public ArrayList<Member> getMemList() {
        ArrayList<Member> list = new ArrayList();
        String sql = "SELECT * FROM members;";

        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs != null) {
                while(rs.next()) {
                    Member item = new Member();
    				item.setMember_id(rs.getInt("member_id"));
    				item.setMember_name(rs.getString("member_name"));
    				item.setMember_address(rs.getString("member_address"));
    				item.setMember_phone(rs.getString("member_phone"));
    				item.setMember_email(rs.getString("member_email"));
                    list.add(item);
                    
                }
                rs.close();
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return list;
    }
    
    public ArrayList<Member> getListM() {
        ArrayList<Member> members = new ArrayList<Member>();
        Member item = null;
        String sql = "SELECT * FROM members";

        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs != null) {
                while(rs.next()) {
                	item = new Member();
    				item.setMember_id(rs.getInt("member_id"));
    				item.setMember_name(rs.getString("member_name"));
    				item.setMember_address(rs.getString("member_address"));
    				item.setMember_phone(rs.getString("member_phone"));
    				item.setMember_email(rs.getString("member_email"));
                    members.add(item);
                    }

                rs.close();
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return members;
    }


	@Override
	public ArrayList<Member> getMembers( int at, int total) {
		String sql = "SELECT * FROM members ";
		sql += "";
		sql += "ORDER BY member_id ASC ";
		sql += "LIMIT "+at+","+total;
		//bien dich cau lenh
		ArrayList<Member> list = new ArrayList<>();
		Member item = null;
		try {
		PreparedStatement pre = this.con.prepareStatement(sql);
		ResultSet rs = pre.executeQuery();
		if(rs != null) {
			while(rs.next()) {
				item = new Member();
				item.setMember_id(rs.getInt("member_id"));
				item.setMember_name(rs.getString("member_name"));
				item.setMember_address(rs.getString("member_address"));
				item.setMember_phone(rs.getString("member_phone"));
				item.setMember_email(rs.getString("member_email"));
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
	
    public ArrayList<Member> getMemByName(String name) {
        String sql = "SELECT * FROM members WHERE member_name LIKE ?";
        ArrayList<Member> members = new ArrayList<>();
        
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, "%" + name + "%");

            ResultSet rs = pre.executeQuery();
            
            if (rs != null) {
                while(rs.next()) {
    				Member item = new Member();
    				item.setMember_id(rs.getInt("member_id"));
    				item.setMember_name(rs.getString("member_name"));
    				item.setMember_address(rs.getString("member_address"));
    				item.setMember_phone(rs.getString("member_phone"));
    				item.setMember_email(rs.getString("member_email"));
    				members.add(item);
                }

                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return members;
    }
    
	
    public ConnectionPool getCP() {
        return this.cp;
    }

    // Trả kết nối
    public void releaseConnection() {
        try {
            this.cp.releaseConnection(this.con, "Member");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }

    }

	@Override
	public Member getMemByID(int var3) {
		String sql = "SELECT * FROM members ";
    	sql += "WHERE member_id = ?";
    	
    	Member item = new Member();
    	try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, var3);
			
			ResultSet rs = pre.executeQuery();
			
			if (rs.next()) {
				item.setMember_id(rs.getInt("member_id"));
				item.setMember_name(rs.getString("member_name"));
				item.setMember_address(rs.getString("member_address"));
				item.setMember_phone(rs.getString("member_phone"));
				item.setMember_email(rs.getString("member_email"));
            }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return item;
	}
	
    public ArrayList<Member> getListMemPage(Member var1, int at, int total) {
        ArrayList<Member> members = new ArrayList();
        Member item = null;
        String sql = "SELECT * FROM members";
        sql = sql + "ORDER BY member_id DESC ";
        sql = sql + "LIMIT " + at + ", " + total;

        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs != null) {
                while(rs.next()) {
                    item = new Member();
                    item.setMember_id(rs.getInt("member_id"));
    				item.setMember_name(rs.getString("member_name"));
    				item.setMember_address(rs.getString("member_address"));
    				item.setMember_phone(rs.getString("member_phone"));
    				item.setMember_email(rs.getString("member_email"));
                    members.add(item);
                }

                rs.close();
            }
        } catch (SQLException e5) {
            e5.printStackTrace();
        }

        return members;
    }




}
