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
			//JDBC 1�ܰ�: ����̹� ��ü �ε�
			DriverManager.registerDriver(new org.h2.Driver());
			String jdbcUrl="jdbc:h2:tcp://localhost/~/test";
			conn = DriverManager.getConnection(jdbcUrl, "sa","");//�Ű�����: url, ID, password

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//JDBC 5�ܰ�: ���� �����ϴ� �޼ҵ� close�� �����. ��ӵ��ο� �ڵ����� ȸ��
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
