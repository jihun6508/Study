package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.saamz.biz.common.JDBCUtill;
//dao, vo ����
public class InsertUserTest3 {
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		//������ ��� Ȯ��
		dao.getUserList();
		
		//����
		UserVO vo = new UserVO();
		vo.setName("�ؿ���");
		vo.setRole("USER");
		vo.setId("ssamz5");
		vo.setPassword("ssamz123");
		dao.insertUser(vo);
		
		//���� �� ��� Ȯ��
		dao.getUserList();
	}
}
