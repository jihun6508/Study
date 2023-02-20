package com.ssamz.web.user;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.web.biz.user.UserDAO;
import com.ssamz.web.biz.user.UserVO;

/**
 * Servlet implementation class InsertUserServlet
 */
//@WebServlet(urlPatterns = "/insertUser.do", initParams = @WebInitParam(name = "boardEncoding", value = "UTF-8"))

@WebServlet(urlPatterns = "/insertUser.do")

public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String encoding;
//어노테이션 구현을 위해 주석처리	
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		// TODO Auto-generated method stub
//		encoding = config.getInitParameter("boardEncoding");
//		System.out.println("---> Encoding : "+ encoding);
//	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩 처리(한글 깨지는것 수정)
//		request.setCharacterEncoding("UTF-8");  //init-param을 사용하기 전
//		request.setCharacterEncoding(encoding); //init() 에서 초기화 하는 경우

		ServletContext context = getServletContext();
		encoding = context.getInitParameter("boardEncoding");
		System.out.println("---> Encoding : " + encoding);
		request.setCharacterEncoding(encoding); 
		/*
		 * init()이 아닌 service에서 초기화하는 경우.
		ServletConfig config = getServletConfig();
		encoding = config.getInitParameter("boardEncoding");
		System.out.println("---> Encoding : "+ encoding);
		*/
		
		//1. 사용자 정보 추출 (login html 의 name값과 매칭)
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String role = request.getParameter("role");
		String selfInfo = request.getParameter("selfInfo");
		String[] languages = request.getParameterValues("languages");
		String age = request.getParameter("age");

		System.out.println("아이디 : "+id);
		System.out.println("비밀번호 : "+password);
		System.out.println("이름 : "+name);
		System.out.println("권한 : "+role);
		System.out.println("자기 소개 : "+selfInfo);
		System.out.println("언어 경험");
		for(String language : languages) {
			System.out.print(language +", ");
		}
		System.out.println();
		System.out.println("나이 : "+age);

		//2. DB 연동 처리
		UserVO vo= new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		vo.setName(name);
		vo.setRole(role);
		
		UserDAO dao = new UserDAO();
		dao.insertUser(vo);
		
		//3. 화면 이동
		response.sendRedirect("login.html");
	}

}
