package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

private static Connection conn = null;
	
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost/cadastro";
	private static final String usuario = "root";
	private static final String senha = "gabriel";
	
	public static Connection getConnection() {
		try {
			if(conn==null) {
				Class.forName(driver);
				return DriverManager.getConnection(url,usuario,senha);
			}
			return conn;
		} catch (ClassNotFoundException | SQLException ex) {
			throw new RuntimeException("Erro no banco : "+ex);
		}
	}
	
	public static void fecharConnection() {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void fecharStatement(Statement st) {
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	public static void fecharResultSet(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
