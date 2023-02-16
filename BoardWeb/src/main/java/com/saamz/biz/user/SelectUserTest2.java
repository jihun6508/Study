package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.saamz.biz.common.JDBCUtill;

public class SelectUserTest2 {

	public static void main(String[] args) {
		//JDBC ���� ���� ����
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			new JDBCUtill();
			conn = JDBCUtill.getConnection();
			//JDBC 3�ܰ�: Statement ����			
			String sql = "select * from USERS where ROLE=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, "ADMIN");
			
			//JDBC 4�ܰ�: sql ����
			rs = stmt.executeQuery();
			
			// 5�ܰ�� ��ȸ ��� ���
			System.out.println("[USER ���]");
			while(rs.next()){// ���� ���ڵ带 Ȯ��
				System.out.print(rs.getString("Id") + " : ");
				System.out.print(rs.getString("PASSWORD") + " : ");
				System.out.print(rs.getString("NAME") + " : ");
				System.out.println(rs.getString("ROLE"));			
			}; 


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
