package com.saamz.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtill {
	
	public static Connection getConnection() {
		Connection conn = null;

		try {
			//JDBC 1단계: 드라이버 객체 로딩
			DriverManager.registerDriver(new org.h2.Driver());
			String jdbcUrl="jdbc:h2:tcp://localhost/~/test";
			conn = DriverManager.getConnection(jdbcUrl, "sa","");//매개변수: url, ID, password

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//JDBC 5단계: 연결 해제하는 메소드 close를 만든다. 고속도로와 자동차를 회수
	public static void close(Connection conn, PreparedStatement stmt) {
		try {
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void close(ResultSet rs, Connection conn, PreparedStatement stmt) {
		try {
			rs.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
