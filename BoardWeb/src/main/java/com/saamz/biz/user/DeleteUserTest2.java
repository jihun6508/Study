package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.saamz.biz.common.JDBCUtill;

public class DeleteUserTest2 {

	public static void main(String[] args) {		UserDAO dao = new UserDAO();
	//업데이트전 목록 확인
	dao.getUserList();
	
	//업데이트
	dao.deleteUser("ssamz4");
	//업데이트 후 목록 확인
	dao.getUserList();
	}

}
