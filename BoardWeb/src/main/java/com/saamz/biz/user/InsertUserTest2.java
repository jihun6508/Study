package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.saamz.biz.common.JDBCUtill;
//dao�� ����
public class InsertUserTest2 {
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		//������ ��� Ȯ��
		dao.getUserList();
		
		//����
		dao.insertUser("ssamz4", "ssamz123", "����", "ADMIN");
		//���� �� ��� Ȯ��
		dao.getUserList();
	}
}
