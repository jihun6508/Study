package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.saamz.biz.common.JDBCUtill;

public class UpdateUserTest {
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement stmt = null;
		

		try {
			conn = JDBCUtill.getConnection();
			//JDB 2�ܰ�: Ŀ�ؼ� ����
			
			String sql = "update users set name=?, role=? where id=?";
			stmt = conn.prepareStatement(sql);
			if(stmt!=null) {
				System.out.println("Statement ��ü: "+ stmt.toString());
			}
			//JDBC 3�ܰ�: Statement ����
			//? �� ����
			stmt.setString(1, "����");
			stmt.setString(2, "User");
			stmt.setString(3, "ssamz3");
			
			//JDBC 4�ܰ�: sql ����
			int count = stmt.executeUpdate();
			System.out.println(count + "�� ������ ó�� �Ϸ�");
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
			JDBCUtill.close(conn, stmt);
		}

	}

}
	

