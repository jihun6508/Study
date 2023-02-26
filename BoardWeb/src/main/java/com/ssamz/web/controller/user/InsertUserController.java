package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.web.biz.user.UserDAO;
import com.ssamz.web.biz.user.UserVO;
import com.ssamz.web.controller.Controller;

public class InsertUserController implements Controller {
	
	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		// TODO Auto-generated method stub
		System.out.println("회원 가입 화면으로 이동");
		
		System.out.println("회원가입 처리");
		//1. 사용자 정보 추출 (login html 의 name값과 매칭)
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String role = request.getParameter("role");


		//2. DB 연동 처리
		UserVO vo= new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		vo.setName(name);
		vo.setRole(role);
		
		UserDAO dao = new UserDAO();
		dao.insertUser(vo);
		
		
		return "index";
	}

}
