package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.saamz.biz.common.JDBCUtill;
//기본 구현
public class InsertUserTest {
	public static void main(String[] args) {
		//JDBC 관련 변수 선언
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			new JDBCUtill();
			conn = JDBCUtill.getConnection();
			//JDBC 1단계: 드라이버 객체 로딩
			DriverManager.registerDriver(new org.h2.Driver());
			String jdbcUrl="jdbc:h2:tcp://localhost/~/test";
			conn = DriverManager.getConnection(jdbcUrl, "sa","");//매개변수: url, ID, password
			if(conn!=null) {
				System.out.println("H2 연결 성공: "+conn.toString());
			}
			//JDB 2단계: 커넥션 연열
			
			String sql = "insert into users values(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			if(stmt!=null) {
				System.out.println("Statement 객체: "+ stmt.toString());
			}
			//JDBC 3단계: Statement 생성
			//? 값 설정
			stmt.setString(1, "ssamz3");
			stmt.setString(2, "ssamz123");
			stmt.setString(3, "쌤즈");
			stmt.setString(4, "Admin");
			
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
