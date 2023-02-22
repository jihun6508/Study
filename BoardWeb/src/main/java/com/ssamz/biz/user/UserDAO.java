package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	//sql 구문
	private String USER_INSERT = "insert into appusers";
	private String USER_DELETE;
	private String USER_UPDATE;
	private String USER_LIST;
	
	
	//공통 변수
	Connection conn=null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	
	//유저 추가
	//유저 삭제
	//유저 업데이트
	//유저 목록 추출
}
