package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.web.controller.Controller;

public class InsertUserViewController implements Controller {
	
	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		// TODO Auto-generated method stub
		System.out.println("회원 가입 화면으로 이동");
		return "inserUser";
	}

}
