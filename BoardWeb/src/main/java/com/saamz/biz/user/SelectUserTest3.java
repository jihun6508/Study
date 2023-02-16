package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.saamz.biz.common.JDBCUtill;

public class SelectUserTest3 {

	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		
		dao.getUserList();
	}
}
