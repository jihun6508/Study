package com.saamz.biz.user;

public class UpdateUserTest2 {
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		//������Ʈ�� ��� Ȯ��
		dao.getUserList();
		
		//������Ʈ
		dao.updateUser("�������̸�", "GUEST", "ssamz4");
		//������Ʈ �� ��� Ȯ��
		dao.getUserList();
	}
}
	

