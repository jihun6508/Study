package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssamz.web.biz.board.BoardDAO;
import com.ssamz.web.biz.board.BoardVO;
import com.ssamz.web.controller.Controller;

public class LogoutController implements Controller {
	
	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		System.out.println("�α׾ƿ� ó��");
		HttpSession session = request.getSession();
		session.invalidate();

		// 3. ȭ�� �̵�


		return "index";
	}

}
