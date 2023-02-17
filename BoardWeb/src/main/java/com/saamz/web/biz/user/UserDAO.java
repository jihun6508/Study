package com.saamz.web.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.saamz.web.biz.util.JDBCUtil;

public class UserDAO {
	private String USER_INSERT = "insert into USERS values(?,?,?,?)";//insert id, password, name, role
	private String USER_UPDATE = "update users set password=?, name=?, role=? where id=?";//update
	private String USER_DELETE ="delete users where id=?";//delete
	private String USER_SELECT = "select * from users";//select
	
	//user table 관련 CRUD 메소드
	private String USER_GET = "select * from users where id=?";//user get
	
	//공용 변수 선언
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs= null;
	
	//회원 상세 조회
	public UserVO getUser(UserVO vo) {
		UserVO user = null;


		try {
			conn=JDBCUtil.getConection();
			stmt= conn.prepareStatement(USER_GET);
			if(stmt!=null) {
				System.out.println("Statement 객체: "+ stmt.toString());
			} else {
				System.out.println("Statement 객체가 null값임");
			}
			stmt.setString(1, vo.getId());
					
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return user;
	}
	
	//db 삽입 삭제 업데이트 구혀
	//delete 구현
	public void deleteUISer(UserVO vo) {
		try {
			conn=JDBCUtil.getConection();
			stmt= conn.prepareStatement(USER_DELETE);
			if(stmt!=null) {
				System.out.println("Statement 객체: "+ stmt.toString());
			} else {
				System.out.println("Statement 객체가 null값임");
			}
			stmt.setString(1, vo.getId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	//insert 구현
	public void insertUser(UserVO vo) {
		try {
			conn=JDBCUtil.getConection();
			stmt= conn.prepareStatement(USER_INSERT);
			if(stmt!=null) {
				System.out.println("Statement 객체: "+ stmt.toString());
			} else {
				System.out.println("Statement 객체가 null값임");
			}
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getRole());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//update 구현
	public void updateUser(UserVO vo) {
		try {
			conn=JDBCUtil.getConection();
			stmt= conn.prepareStatement(USER_UPDATE);
			if(stmt!=null) {
				System.out.println("Statement 객체: "+ stmt.toString());
			} else {
				System.out.println("Statement 객체가 null값임");
			}
			stmt.setString(1, vo.getPassword());
			stmt.setString(2, vo.getName());
			stmt.setString(3, vo.getRole());
			stmt.setString(4, vo.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//select
	public List<UserVO> getUserList(){
		List<UserVO> userList = new ArrayList<UserVO>();
		try {
			conn=JDBCUtil.getConection();
			
			stmt= conn.prepareStatement(USER_SELECT);
			
			if(stmt!=null) {
				System.out.println("Statement 객체: "+ stmt.toString());
			} else {
				System.out.println("Statement 객체가 null값임");
			}
			rs= stmt.executeQuery();
			
			while(rs.next()){
				UserVO user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return userList;
	}
}
