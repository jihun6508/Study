package com.saamz.biz.user;

public class UpdateUserTest3 {
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		//������Ʈ�� ��� Ȯ��
		dao.getUserList();
		
		//������Ʈ
		UserVO vo = new UserVO();
		vo.setName("���� �� �̸�");
		vo.setRole("USER");
		vo.setId("ssamz3");
		dao.updateUser(vo);
		//������Ʈ �� ��� Ȯ��
		dao.getUserList();
	}
}
	

