package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.saamz.biz.common.JDBCUtill;
//dao만 적용
public class InsertUserTest2 {
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		//삽입전 목록 확인
		dao.getUserList();
		
		//삽입
		dao.insertUser("ssamz4", "ssamz123", "쌤즈", "ADMIN");
		//삽입 후 목록 확인
		dao.getUserList();
	}
}
