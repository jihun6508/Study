package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssamz.web.biz.user.UserDAO;
import com.ssamz.web.biz.user.UserVO;
import com.ssamz.web.controller.Controller;

public class LoginController implements Controller {

	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		System.out.println("�α��� ó��");
		//1. ����� �Է� ���� ����
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		//2. DB ���� ó��
		UserVO vo = new UserVO();
		vo.setId(id);

		UserDAO dao = new UserDAO();
		UserVO user = dao.getUser(vo);


		//3.����ȭ�� ����
		//���� �޽����� ���� ���ڵ� ����
		HttpSession session = request.getSession();
		if(user != null && user.getPassword().equals(password)) {
			//���� ������ userVO ���·� ��°�� ����
			session.setAttribute("user", user);
			//�� ��� ȭ������ �̵�
			return "/getBoardList.do";
		} else {
			return "/loginView.do";
		}
	}

}
