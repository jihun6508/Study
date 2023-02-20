package com.ssamz.web.biz.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/deleteBoard.do")
public class DeleteBoardServlet extends HttpServlet {
	static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. ���� ���� üũ
		// 0-1 ��Ű ���·� ����
		/*
		Cookie[] cookieList = request.getCookies();
		if(cookieList == null) {
			response.sendRedirect("/login.html");
		} else {
			String userId = null;
			
			for(Cookie cookie : cookieList) {
				if(cookie.getName().equals("userId")){
					userId = cookie.getValue();
				}
			}
			if(userId == null) {
				response.sendRedirect("/login.html");
			}
		}
		*/
		//0-2. ���� ���·� ����
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		if(userId ==null) {
			response.sendRedirect("/");
		}
		
		// 1. ����� �Է� ���� ���� => ����� �Է� �ѱ����ڵ�, ����� �Է��� getParameter�� ��������
		String seq = request.getParameter("seq");
		
		//2. DB ���� ó�� : VO�� ���� �����ϰų� �������� ������ �Ѵ�. 
		BoardVO vo= new BoardVO();
		vo.setSeq(Integer.parseInt(seq)); // ���ڷεǾ� �ִ� �Ϸù�ȣ�� ���ڷ� ��ȯ 

		BoardDAO boardDAO = new BoardDAO();
		boardDAO.deleteBoard(vo);
		
		// 3. ȭ�� �̵� : ������ ������ �� �� �����ִ� ������
		RequestDispatcher dispatcher = request.getRequestDispatcher("getBoardList.do");
		dispatcher.forward(request, response);		
	}
}
