package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.saamz.biz.common.JDBCUtill;

public class UserDAO {
	//접근지정자로 은닉
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	//User 테이블 관련 SQL 명령어
	private String USER_LIST = "select * from USERS";
	private String USER_INSERT = "insert into users values(?,?,?,?)";
	private String USER_UPDATE = "update users set name=?, role=? where id=?";
	private String USER_DELETE = "delete users where id=?";

	
	
	
	
	//USERS 테이블 관련 CRUD 메소드
	//회원 삭제
	public void deleteUser(String id) {
		try {
			conn = JDBCUtill.getConnection();
			//JDB 2단계: 커넥션 연열
			stmt = conn.prepareStatement(USER_DELETE);
			//JDBC 3단계: Statement 생성
			//? 값 설정
			stmt.setString(1, id);

			
			//JDBC 4단계: sql 전송
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, stmt);
		}

	}
	
	//회원 업데이트(vo 적용 전)
	public void updateUser(String name, String role, String id) {
		try {
			conn = JDBCUtill.getConnection();
			
			stmt = conn.prepareStatement(USER_UPDATE);

			//JDBC 3단계: Statement 생성
			//? 값 설정
			stmt.setString(1, name);
			stmt.setString(2, role);
			stmt.setString(3, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, stmt);
		}	
	}
	//회원 업데이트(vo 적용 후)
	public void updateUser(UserVO vo) {
		try {
			conn = JDBCUtill.getConnection();
			
			stmt = conn.prepareStatement(USER_UPDATE);

			//JDBC 3단계: Statement 생성
			//? 값 설정
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getRole());
			stmt.setString(3, vo.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, stmt);
		}	
	}
	
	//회원 등록(vo 적용 전)
	public void insertUser(String id, String password, String name, String role) {
		try {
			conn = JDBCUtill.getConnection();			
			stmt = conn.prepareStatement(USER_INSERT);
			
			//JDBC 3단계: Statement 생성
			//? 값 설정
			stmt.setString(1, id);
			stmt.setString(2, password);
			stmt.setString(3, name);
			stmt.setString(4, role);
			
			//JDBC 4단계: sql 전송
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, stmt);
		}
	}
	//회원 등록(vo 적용 후)
	public void insertUser(UserVO vo) {
		try {
			conn = JDBCUtill.getConnection();			
			stmt = conn.prepareStatement(USER_INSERT);
			
			//JDBC 3단계: Statement 생성
			//? 값 설정
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getRole());
			
			//JDBC 4단계: sql 전송
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, stmt);
		}
	}
	//회원 목록 조회
	public void getUserList() {
		try {
			conn = JDBCUtill.getConnection();
			stmt = conn.prepareStatement(USER_LIST);
			
			//JDBC 4단계: sql 전송
			rs = stmt.executeQuery();
			
			System.out.println("[USER 목록]");
			while(rs.next()){// 다음 레코드를 확인
				System.out.print(rs.getString("Id") + " : ");
				System.out.print(rs.getString("PASSWORD") + " : ");
				System.out.print(rs.getString("NAME") + " : ");
				System.out.println(rs.getString("ROLE"));			
			}; 


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(rs, conn, stmt);
		}
	}
	//회원 목록 조회(vo 적용 및 리스트값만 리턴하도록 수정)
	public List<UserVO> getUserListVO(){
		List<UserVO> userList = new ArrayList<UserVO>();

		try {
			conn = JDBCUtill.getConnection();
			stmt = conn.prepareStatement(USER_LIST);
			
			//JDBC 4단계: sql 전송
			rs = stmt.executeQuery();
			
			System.out.println("[USER 목록]");
			while(rs.next()){// 다음 레코드를 확인
				UserVO user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setName(rs.getString("NAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setRole(rs.getString("ROLE"));
				userList.add(user);
			}; 


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(rs, conn, stmt);
		}
		return userList;
	}

	
	
}
