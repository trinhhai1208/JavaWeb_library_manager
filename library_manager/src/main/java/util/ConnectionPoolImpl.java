package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool {
    private static ConnectionPool cp = null;
    private String username;
    private String password;
    private String driver;
    private String url;
    private Stack<Connection> pool;

    public ConnectionPoolImpl() {
    	this.driver = "com.mysql.cj.jdbc.Driver";
        this.username = "root";
        this.password = "123456";
        this.url = "jdbc:mysql://localhost:3306/library_manager";
        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException var2) {
            var2.printStackTrace();
        }

        this.pool = new Stack<Connection> ();
    }

    public Connection getConnection(String objectName) throws SQLException {
        if (this.pool.isEmpty()) {
            System.out.println(objectName + "has created a new connection.");
            return DriverManager.getConnection(this.url, this.username, this.password);
        } else {
            System.out.println(objectName + "have popped the connection.");
            return (Connection)this.pool.pop();
        }
    }

    public static ConnectionPool getInstance() {
        if (cp == null) {
            synchronized (ConnectionPoolImpl.class) {
                if (cp == null) {
                    cp = new ConnectionPoolImpl();
                }
            }
        }
        return cp;
    }

    public void releaseConnection(Connection con, String objectName) throws SQLException {
        System.out.println(objectName + "have pushed a connection.");
        this.pool.push(con);
    }
    protected void finalize() throws Throwable {
        this.pool.clear();
        this.pool = null;
        System.out.println("Connection pool has closed.");
    }
}