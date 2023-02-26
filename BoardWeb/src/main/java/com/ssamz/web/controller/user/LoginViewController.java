package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.web.controller.Controller;

public class LoginViewController implements Controller {

	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		System.out.println("로그인 화면으로 이동");
		//화면 이동
		return "login";
	}

}
