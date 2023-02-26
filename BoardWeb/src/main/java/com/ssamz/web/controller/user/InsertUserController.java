package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.web.biz.user.UserDAO;
import com.ssamz.web.biz.user.UserVO;
import com.ssamz.web.controller.Controller;

public class InsertUserController implements Controller {
	
	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		// TODO Auto-generated method stub
		System.out.println("ȸ�� ���� ȭ������ �̵�");
		
		System.out.println("ȸ������ ó��");
		//1. ����� ���� ���� (login html �� name���� ��Ī)
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String role = request.getParameter("role");


		//2. DB ���� ó��
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
