package com.ssamz.web.biz.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
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
		
		//1. 사용자 입력 정보 추출
		//1-1.인코딩 처리
		//encoidng doFilter로 이동 후 주석처리
		/*
		ServletContext context = request.getServletContext();
		String encoding = context.getInitParameter("boardEncoding");
		request.setCharacterEncoding(encoding);
		*/
		
		//1-2. 입력 정보 획득
		String searchCondition = request.getParameter("searchCondition");
		String searchKeyword = request.getParameter("searchKeyword");
		
		//null check
		if(searchCondition==null) searchCondition = "TITLE";
		if(searchKeyword == null) searchKeyword = "";  
		
		//세션에 검색 관련 정보 저장
		session.setAttribute("condition", searchCondition);
		session.setAttribute("keyword", searchKeyword);
		
		//2. DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setSearchCondition(searchCondition);
		vo.setSearchKeyword(searchKeyword);
		
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		
		//3. 응답 화면 구성
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
		
		
		
		
		//세션 아이디를 바탕으로 로그인 정보 표시
		String userName = (String) session.getAttribute("userName");
//		out.println("<h3>"+userName +"님 로그인을 환영합니다....");
		
		//session을 바탕으로 메시지 뿌림
//		String welcomeMessage = (String) session.getAttribute("welcomeMessage");
		
		//HttpServletRequest에서 받아서 뿌림
//		String welcomeMessage = (String) request.getAttribute("welcomeMessage");

		//ServletContext에서 받아서 뿌림
		//encoding 주석처리에 따라 context 객체 생성을 안하게 되어 코드 사용 불가, 주석 처리
//		String welcomeMessage = (String) context.getAttribute("welcomeMessage");
//		out.println("<h3>" + userName + welcomeMessage);
		
		//텍스트만 구현
//		out.println("<h3>테스터님 로그인 환영합니다......");
		
		//검색화면
		out.println("<!--검색 시작-->");
		out.println("<form action='getBoardList.do' method='post'");
		out.println("<table border='1' cellpadding='0' cellspacing='0' width='700'>");
		out.println("<tr>");
		out.println("<td align='right'>");
		out.println("<select name='searchCondition'>");

		//기존 코드
//		out.println("<option value='TITLE'>제목");
//		out.println("<option value='CONTENT'>내용");

		
		//검색값에 따라 기본 선택된 검색 옵션 값을 수정
		String condition = (String) session.getAttribute("condition");
		if(condition.equals("TITLE")) {
			out.println("<option value='TITLE' selected>제목");
		} else {
			out.println("<option value='TITLE'>제목");
		}
		
		if(condition.equals("CONTENT")) {
			out.println("<option value='CONTENT' selected>내용");
		} else {
			out.println("<option value='CONTENT'>내용");
		}
		out.println("</selection>");
		
		out.println("<input name='searchKeyword' type='text' value='"+session.getAttribute("keyword")+"'/>");

//		out.println("<input name='searchKeyword' type='text'/>");
		out.println("<input type='submit' value='검색'/>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("<!--검색 종료-->");

		
		
		//로그아웃
		out.println("<a href='logout.do'>Log-out</a></h3>");
		
		//메뉴바
		out.println("<table border='1' cellpadding='0' cellspacing='0' width='700'>");
		out.println("<tr>");
		out.println("<th bgcolor='orange' width='100'>번호</th>");
		out.println("<th bgcolor='orange' width='200'>제목</th>");
		out.println("<th bgcolor='orange' width='150'>작성자</th>");
		out.println("<th bgcolor='orange' width='150'>등록일</th>");
		out.println("<th bgcolor='orange' width='100'>조회수</th>");
		out.println("</tr>");
		
		//글 리스트
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
