package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool {
    private String username;
    private String password;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url;
    private Stack<Connection> pool;

    public ConnectionPoolImpl() {
        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException var2) {
            var2.printStackTrace();
        }

        this.username = "root";
        this.password = "123456";
        this.url = "jdbc:mysql://localhost:3306/library_manager";
        this.pool = new Stack();
    }

    public Connection getConnection(String objectName) throws SQLException {
        if (this.pool.isEmpty()) {
            System.out.println(objectName + "has created a new connection.");
            return DriverManager.getConnection(this.url, this.username, this.password);
        } else {
            System.out.println(objectName + "has taken a connection.");
            return (Connection)this.pool.pop();
        }
    }

    public void releaseConnection(Connection con, String objectName) throws SQLException {
        System.out.println(objectName + "has released a connection.");
        this.pool.push(con);
    }

    protected void finalize() throws Throwable {
        this.pool.clear();
        this.pool = null;
        System.out.println("Connection pool has closed.");
    }
}