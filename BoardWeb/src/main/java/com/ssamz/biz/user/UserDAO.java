package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	//sql ����
	private String USER_INSERT = "insert into appusers";
	private String USER_DELETE;
	private String USER_UPDATE;
	private String USER_LIST;
	
	
	//���� ����
	Connection conn=null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	
	//���� �߰�
	//���� ����
	//���� ������Ʈ
	//���� ��� ����
}
