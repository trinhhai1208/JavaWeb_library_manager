package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnectionPool;
import util.ConnectionPoolImpl;

public class AdminFunctionImpl implements AdminFunction {
	
	private Connection con;
	private ConnectionPool cp;

	public AdminFunctionImpl(ConnectionPool cp) {
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
	
	public boolean login(String username, String password) {
		String sql = "SELECT * FROM Admins WHERE username = ? AND password = ?";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, username);
			pre.setString(2, password);
			ResultSet rs = pre.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
	
}
