package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.saamz.biz.common.JDBCUtill;

public class SelectUserTest1 {

	public static void main(String[] args) {
		//JDBC 관련 변수 선언
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtill.getConnection();
			String sql = "select * from users";
			stmt = conn.prepareStatement(sql);
			
			
			//JDBC 4단계: sql 전송
			rs = stmt.executeQuery();
			
			// 5단계ㅣ 조회 결과 사용
			System.out.println("[USER 목록]");
			rs.next(); // 다음 레코드를 확인
			System.out.print(rs.getString("Id") + " : ");
			System.out.print(rs.getString("PASSWORD") + " : ");
			System.out.print(rs.getString("NAME") + " : ");
			System.out.println(rs.getString("ROLE"));

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
			JDBCUtill.close(rs, conn, stmt);
		}

	}
}
