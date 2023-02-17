package com.saamz.web.biz.user;

import java.util.ArrayList;
import java.util.List;

public class InsertTest {
	public static void main(String[] args) {
		UserVO vo = new UserVO();
		vo.setId("t4ID");
		vo.setPassword("tPassword");
		vo.setRole("tRole");
		vo.setName("t4Name");
		
		UserDAO dao = new UserDAO();
		
		List<UserVO> userList = dao.getUserList();
		
		for(UserVO user : userList) {
			System.out.println("아이디: " + user.getId()+" 이름: " + user.getName()+ "역할 : "+ user.getRole());
		}
		
		
		
		dao.insertUser(vo);
		
		vo.setId("t4ID");
		vo.setPassword("ttPassword");
		vo.setRole("ttRole");
		vo.setName("ttName");
		
		dao.updateUser(vo);
		
		for(UserVO user : userList) {
			System.out.println("아이디: " + user.getId()+" 이름: " + user.getName()+ "역할 : "+ user.getRole());
		}
	}
}
