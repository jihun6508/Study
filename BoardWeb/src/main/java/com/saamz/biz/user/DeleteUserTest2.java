package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.saamz.biz.common.JDBCUtill;

public class DeleteUserTest2 {

	public static void main(String[] args) {		UserDAO dao = new UserDAO();
	//������Ʈ�� ��� Ȯ��
	dao.getUserList();
	
	//������Ʈ
	dao.deleteUser("ssamz4");
	//������Ʈ �� ��� Ȯ��
	dao.getUserList();
	}

}
