package com.saamz.biz.user;

public class UpdateUserTest2 {
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		//업데이트전 목록 확인
		dao.getUserList();
		
		//업데이트
		dao.updateUser("수정된이름", "GUEST", "ssamz4");
		//업데이트 후 목록 확인
		dao.getUserList();
	}
}
	

