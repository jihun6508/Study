package com.saamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.saamz.biz.common.JDBCUtill;
//dao, vo 적용
public class InsertUserTest3 {
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		//삽입전 목록 확인
		dao.getUserList();
		
		//삽입
		UserVO vo = new UserVO();
		vo.setName("쌔엠즈");
		vo.setRole("USER");
		vo.setId("ssamz5");
		vo.setPassword("ssamz123");
		dao.insertUser(vo);
		
		//삽입 후 목록 확인
		dao.getUserList();
	}
}
