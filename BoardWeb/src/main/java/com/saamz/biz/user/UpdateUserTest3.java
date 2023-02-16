package com.saamz.biz.user;

public class UpdateUserTest3 {
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		//업데이트전 목록 확인
		dao.getUserList();
		
		//업데이트
		UserVO vo = new UserVO();
		vo.setName("수정 후 이름");
		vo.setRole("USER");
		vo.setId("ssamz3");
		dao.updateUser(vo);
		//업데이트 후 목록 확인
		dao.getUserList();
	}
}
	

