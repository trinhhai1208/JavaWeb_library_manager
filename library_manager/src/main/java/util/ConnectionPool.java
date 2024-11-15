package util;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
	Connection getConnection(String var1) throws SQLException;

    void releaseConnection(Connection var1, String var2) throws SQLException;

}
