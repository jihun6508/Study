package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssamz.web.biz.user.UserDAO;
import com.ssamz.web.biz.user.UserVO;
import com.ssamz.web.controller.Controller;

public class LoginController implements Controller {

	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		System.out.println("로그인 처리");
		//1. 사용자 입력 정보 추출
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		//2. DB 연동 처리
		UserVO vo = new UserVO();
		vo.setId(id);

		UserDAO dao = new UserDAO();
		UserVO user = dao.getUser(vo);


		//3.응답화면 구성
		//응답 메시지에 대한 인코딩 설정
		HttpSession session = request.getSession();
		if(user != null && user.getPassword().equals(password)) {
			//유저 정보를 userVO 형태로 통째로 저장
			session.setAttribute("user", user);
			//글 목록 화면으로 이동
			return "/getBoardList.do";
		} else {
			return "/loginView.do";
		}
	}

}
