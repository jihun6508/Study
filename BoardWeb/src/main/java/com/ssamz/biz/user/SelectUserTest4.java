package com.ssamz.biz.user;


import java.util.List;

import com.ssamz.web.biz.user.UserDAO;
import com.ssamz.web.biz.user.UserVO;

//VO ���� ���� �� getlistVO�� ���Ϻ� �� Ȯ��
public class SelectUserTest4 {

	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		
		List<UserVO> userList= dao.getUserList();
		
		System.out.println("��ü ȸ�� �� : "+userList.size());
		
		System.out.println("[ȸ���� ����]");
		for(UserVO user : userList) {
			System.out.println(user.getName() + "�� ����: "+ user.getRole());			
		}
		
	}
}
