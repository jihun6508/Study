package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.saamz.biz.common.JDBCUtill;

public class DeleteUserTest {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement stmt = null;
		

		try {
			new JDBCUtill();
			conn = JDBCUtill.getConnection();
			//JDBC 1�ܰ�: ����̹� ��ü �ε�
			DriverManager.registerDriver(new org.h2.Driver());
			String jdbcUrl="jdbc:h2:tcp://localhost/~/test";
			conn = DriverManager.getConnection(jdbcUrl, "sa","");//�Ű�����: url, ID, password
			if(conn!=null) {
				System.out.println("H2 ���� ����: "+conn.toString());
			}
			//JDB 2�ܰ�: Ŀ�ؼ� ����
			
			String sql = "delete users where id=?";
			stmt = conn.prepareStatement(sql);
			if(stmt!=null) {
				System.out.println("Statement ��ü: "+ stmt.toString());
			}
			//JDBC 3�ܰ�: Statement ����
			//? �� ����
			stmt.setString(1, "ssamz3");

			
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
