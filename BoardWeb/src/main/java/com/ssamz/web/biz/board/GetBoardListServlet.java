package com.ssamz.web.biz.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetBoardListServlet
 */
//글 목록 구현

@WebServlet("/getBoardList.do")
public class GetBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	
	public void init() {
		System.out.println("init호출");
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 상태 정보 체크
		//0-1 쿠키 형태로 사용한 경우
		/*
		Cookie[] cookieList = request.getCookies();
		if(cookieList == null) {
			response.sendRedirect("/login.html");
		} else {
			String userId = null;
			
			for(Cookie cookie : cookieList) {
				if(cookie.getName().equals("userId")) {
					userId = cookie.getValue();
				}
			}
			if(userId ==null) {
				response.sendRedirect("/login.html");
			}
		}
		*/

		//0-2.세션 기능 테스트
		/*
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		if(session.isNew()) {
			System.out.println("===> 처음 생성된 세션 : "+sessionId);
		} else {
			System.out.println("---> 재사용중인 세션"+sessionId);
		}
		*/
		//0-3. 세션을 이용한 상태 정보 체크 구현  -> 인증 데이터가 없으면 로그인 창으로 리다이렉트됨
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		if(userId == null) {
			response.sendRedirect("/");
		}
		
		//1. DB 연동 처리
		BoardVO vo = new BoardVO();
		
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		
		//2. 응답 화면 구성
		//서버 연동 확인 및 기능 테스트용 코드
		/*
		response.setContentType("text/html; charset=EUC-KR");
		PrintWriter out = response.getWriter();
		
		out.println("<h1>게시글 목록</h1>");
		out.println("<hr>");
		
		for(BoardVO board : boardList) {
			out.println("---> "+ board.toString()+"</br>");
		}
		
		out.close();
		*/
		
		response.setContentType("text/html; charset=EUC-KR");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		
		out.println("<title>글 목록</title>");
		out.println("</head>");

		out.println("<body>");
		out.println("<center>");
		out.println("<h1>게시글 목록</h1>");
		
		String userName = (String) session.getAttribute("userNAme");
		out.println("<h3>"+userName +"님 로그인을 환영합니다....");
//		out.println("<h3>테스터님 로그인 환영합니다......");

		out.println("<a href='logout.do'>Log-out</a></h3>");
		
		out.println("<table border='1' cellpadding='0' cellspacing='0' width='700'>");
		out.println("<tr>");
		out.println("<th bgcolor='orange' width='100'>번호</th>");
		out.println("<th bgcolor='orange' width='200'>제목</th>");
		out.println("<th bgcolor='orange' width='150'>작성자</th>");
		out.println("<th bgcolor='orange' width='150'>등록일</th>");
		out.println("<th bgcolor='orange' width='100'>조회수</th>");
		out.println("</tr>");
		
		for(BoardVO board : boardList) {
			out.println("<tr>");
			out.println("<td>"+board.getSeq()+"</td>");
			out.println("<td align='left'><a href='getBoard.do?seq="+board.getSeq()+"'>" + board.getTitle() + "</a></td>");
			out.println("<td>"+board.getWriter()+"</td>");
			out.println("<td>"+board.getRegDate()+"</td>");
			out.println("<td>"+board.getCnt()+"</td>");
			out.println("</tr>");
		}
		
		out.println("</table>");
		out.println("<br>");
		out.println("<a href='insertBoard.html'>새글 등록</a>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
