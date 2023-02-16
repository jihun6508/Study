package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.saamz.biz.common.JDBCUtill;

public class SelectUserTest1 {

	public static void main(String[] args) {
		//JDBC ���� ���� ����
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtill.getConnection();
			String sql = "select * from users";
			stmt = conn.prepareStatement(sql);
			
			
			//JDBC 4�ܰ�: sql ����
			rs = stmt.executeQuery();
			
			// 5�ܰ�� ��ȸ ��� ���
			System.out.println("[USER ���]");
			rs.next(); // ���� ���ڵ带 Ȯ��
			System.out.print(rs.getString("Id") + " : ");
			System.out.print(rs.getString("PASSWORD") + " : ");
			System.out.print(rs.getString("NAME") + " : ");
			System.out.println(rs.getString("ROLE"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC 5�ܰ�: ���� ����
/*			���� �ڵ�
			try {
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
*/
			//������ ���� Ŭ�������� ������
			JDBCUtill.close(rs, conn, stmt);
		}

	}
}
