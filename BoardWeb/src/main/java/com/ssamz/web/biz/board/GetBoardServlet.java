package com.ssamz.web.biz.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssamz.web.biz.user.UserVO;


@WebServlet("/getBoard.do")
public class GetBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0. 상태 정보 체크
		// AuthenticationFilter로 기능 이전하여 코드 주석처리
		/*
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			response.sendRedirect("/");
		}
		*/
		
		// 1. 사용자 입력 정보 추출
		String seq = request.getParameter("seq"); // 문자열
		// 2.DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq)); // 문자열을 숫자로 변환

		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = boardDAO.getBoard(vo);
		// 3. 응답 화면 구성(검색 결과 화면 출력)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>글 상세</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>글 상세 </h1>");
		out.println("<a href='logout.do'>Log-out</a></h3>");
		out.println("<hr>");
		out.println("<form action='updateBoard.do' method='post'>");
		out.println("<input name='seq' type='hidden' value='" + board.getSeq() + "'/>");
		out.println("<table border='1' cellpadding='0' cellspacing='0'>");
		out.println("<tr>");
		out.println("<td bgcolor='orange' width='70'>제목</td>");
		out.println("<td align='left'><input name='title' type='text' value=' " + board.getTitle() + "'/></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td bgcolor='orange'>작성자</td>");
		out.println("<td align='left'>" + board.getWriter() + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td bgcolor='orange'>내용</td>");
		out.println("<td align='left'><textarea name='content' cols='40' rows='10'>" + board.getContent()
				+ "</textarea></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td bgcolor='orange'>등록일</td>");
		out.println("<td align='left'>" + board.getRegDate() + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td bgcolor='orange'>조회수</td>");
		out.println("<td align='left'>" + board.getCnt() + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center'>");
		out.println("<input type='submit' value='글 수정'/>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("<hr>");
		out.println("<a href='insertBoard.html'>글등록</a>&nbsp;&nbsp;&nbsp;");

		// admin 권한을 가진 사람에게만 글삭제 링크를 보이게 함
		HttpSession session = request.getSession(); //상태정보체크 주석처리에 따라 session 객체 별도 선언
		
		//UserVO를 개별로 세션에 저장한 경우
//		String userRole = (String) session.getAttribute("userRole");
//		if (userRole.equals("ADMIN")) {
//			out.println("<a href='deleteBoard.do?seq=" + board.getSeq() + "'>글삭제</a>&nbsp;&nbsp;&nbsp;");
//		}
		
		//UserVO를 통째로 받아오는 경우
		UserVO user = (UserVO) session.getAttribute("user");
		if (user.getRole().equals("ADMIN")) {
			out.println("<a href='deleteBoard.do?seq=" + board.getSeq() + "'>글삭제</a>&nbsp;&nbsp;&nbsp;");
		}		
		
//      out.println("<a href='deleteBoard.do?seq=" + board.getSeq() +"'>글삭제</a>&nbsp;&nbsp;&nbsp;");

		out.println("<a href='getBoardList.do'>글목록</a>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		out.close();

	}

}