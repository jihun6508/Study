package com.saamz.biz.user;


import java.util.List;

//VO ���� ���� �� getlistVO�� ���Ϻ� �� Ȯ��
public class SelectUserTest4 {

	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		
		List<UserVO> userList= dao.getUserListVO();
		
		System.out.println("��ü ȸ�� �� : "+userList.size());
		
		System.out.println("[ȸ���� ����]");
		for(UserVO user : userList) {
			System.out.println(user.getName() + "�� ����: "+ user.getRole());			
		}
		
	}
}
