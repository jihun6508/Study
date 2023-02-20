package com.ssamz.web.biz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {

	public static Connection getConnection() {
		Connection conn = null;
		String jdbcURL = null;
		try {
			DriverManager.registerDriver(new org.h2.Driver());
			jdbcURL = "jdbc:h2:tcp://localhost/~/test";
			conn = DriverManager.getConnection(jdbcURL, "sa", "");
			if(conn!=null) {
				System.out.println("H2 연결 성공: "+conn.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(PreparedStatement stmt, Connection conn) {
		try {
			stmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs,PreparedStatement stmt, Connection conn) {
		try {
			rs.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
