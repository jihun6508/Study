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
			//JDB 2단계: 커넥션 연열
			
			String sql = "update users set name=?, role=? where id=?";
			stmt = conn.prepareStatement(sql);
			if(stmt!=null) {
				System.out.println("Statement 객체: "+ stmt.toString());
			}
			//JDBC 3단계: Statement 생성
			//? 값 설정
			stmt.setString(1, "수정");
			stmt.setString(2, "User");
			stmt.setString(3, "ssamz3");
			
			//JDBC 4단계: sql 전송
			int count = stmt.executeUpdate();
			System.out.println(count + "건 데이터 처리 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC 5단계: 연결 해제
	/*			기존 코드
			try {
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
	*/
			//별도로 만든 클래스에서 가져옴
			JDBCUtill.close(conn, stmt);
		}

	}

}
	

