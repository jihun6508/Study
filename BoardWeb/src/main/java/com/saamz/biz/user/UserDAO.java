package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.saamz.biz.common.JDBCUtill;

public class UserDAO {
	//���������ڷ� ����
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	//User ���̺� ���� SQL ��ɾ�
	private String USER_LIST = "select * from USERS";
	private String USER_INSERT = "insert into users values(?,?,?,?)";
	private String USER_UPDATE = "update users set name=?, role=? where id=?";
	private String USER_DELETE = "delete users where id=?";

	
	
	
	
	//USERS ���̺� ���� CRUD �޼ҵ�
	//ȸ�� ����
	public void deleteUser(String id) {
		try {
			conn = JDBCUtill.getConnection();
			//JDB 2�ܰ�: Ŀ�ؼ� ����
			stmt = conn.prepareStatement(USER_DELETE);
			//JDBC 3�ܰ�: Statement ����
			//? �� ����
			stmt.setString(1, id);

			
			//JDBC 4�ܰ�: sql ����
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, stmt);
		}

	}
	
	//ȸ�� ������Ʈ(vo ���� ��)
	public void updateUser(String name, String role, String id) {
		try {
			conn = JDBCUtill.getConnection();
			
			stmt = conn.prepareStatement(USER_UPDATE);

			//JDBC 3�ܰ�: Statement ����
			//? �� ����
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
	//ȸ�� ������Ʈ(vo ���� ��)
	public void updateUser(UserVO vo) {
		try {
			conn = JDBCUtill.getConnection();
			
			stmt = conn.prepareStatement(USER_UPDATE);

			//JDBC 3�ܰ�: Statement ����
			//? �� ����
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
	
	//ȸ�� ���(vo ���� ��)
	public void insertUser(String id, String password, String name, String role) {
		try {
			conn = JDBCUtill.getConnection();			
			stmt = conn.prepareStatement(USER_INSERT);
			
			//JDBC 3�ܰ�: Statement ����
			//? �� ����
			stmt.setString(1, id);
			stmt.setString(2, password);
			stmt.setString(3, name);
			stmt.setString(4, role);
			
			//JDBC 4�ܰ�: sql ����
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, stmt);
		}
	}
	//ȸ�� ���(vo ���� ��)
	public void insertUser(UserVO vo) {
		try {
			conn = JDBCUtill.getConnection();			
			stmt = conn.prepareStatement(USER_INSERT);
			
			//JDBC 3�ܰ�: Statement ����
			//? �� ����
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getRole());
			
			//JDBC 4�ܰ�: sql ����
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, stmt);
		}
	}
	//ȸ�� ��� ��ȸ
	public void getUserList() {
		try {
			conn = JDBCUtill.getConnection();
			stmt = conn.prepareStatement(USER_LIST);
			
			//JDBC 4�ܰ�: sql ����
			rs = stmt.executeQuery();
			
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
			JDBCUtill.close(rs, conn, stmt);
		}
	}
	//ȸ�� ��� ��ȸ(vo ���� �� ����Ʈ���� �����ϵ��� ����)
	public List<UserVO> getUserListVO(){
		List<UserVO> userList = new ArrayList<UserVO>();

		try {
			conn = JDBCUtill.getConnection();
			stmt = conn.prepareStatement(USER_LIST);
			
			//JDBC 4�ܰ�: sql ����
			rs = stmt.executeQuery();
			
			System.out.println("[USER ���]");
			while(rs.next()){// ���� ���ڵ带 Ȯ��
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
