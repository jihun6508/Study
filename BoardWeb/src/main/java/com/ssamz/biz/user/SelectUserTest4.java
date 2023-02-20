package com.ssamz.biz.user;


import java.util.List;

import com.ssamz.web.biz.user.UserDAO;
import com.ssamz.web.biz.user.UserVO;

//VO 패턴 적용 밑 getlistVO의 패턴별 값 확인
public class SelectUserTest4 {

	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		
		List<UserVO> userList= dao.getUserList();
		
		System.out.println("전체 회원 수 : "+userList.size());
		
		System.out.println("[회원의 권한]");
		for(UserVO user : userList) {
			System.out.println(user.getName() + "의 권한: "+ user.getRole());			
		}
		
	}
}
