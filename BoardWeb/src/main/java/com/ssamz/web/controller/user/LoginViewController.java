package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.web.controller.Controller;

public class LoginViewController implements Controller {

	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		System.out.println("�α��� ȭ������ �̵�");
		//ȭ�� �̵�
		return "login";
	}

}
