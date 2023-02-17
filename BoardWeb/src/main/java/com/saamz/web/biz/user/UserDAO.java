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

	
	//insert ±¸Çö
	public void insertUser(UserVO vo) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn=JDBCUtil.getConection();
			stmt= conn.prepareStatement(USER_INSERT);
			if(stmt!=null) {
				System.out.println("Statement °´Ã¼: "+ stmt.toString());
			} else {
				System.out.println("Statement °´Ã¼°¡ null°ªÀÓ");
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
	
	//update ±¸Çö
	public void updateUser(UserVO vo) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn=JDBCUtil.getConection();
			stmt= conn.prepareStatement(USER_UPDATE);
			if(stmt!=null) {
				System.out.println("Statement °´Ã¼: "+ stmt.toString());
			} else {
				System.out.println("Statement °´Ã¼°¡ null°ªÀÓ");
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
		ResultSet rs = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn=JDBCUtil.getConection();
			
			stmt= conn.prepareStatement(USER_SELECT);
			
			if(stmt!=null) {
				System.out.println("Statement °´Ã¼: "+ stmt.toString());
			} else {
				System.out.println("Statement °´Ã¼°¡ null°ªÀÓ");
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
