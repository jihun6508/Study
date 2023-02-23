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
		// 0. ���� ���� üũ
		// AuthenticationFilter�� ��� �����Ͽ� �ڵ� �ּ�ó��
		/*
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			response.sendRedirect("/");
		}
		*/
		
		// 1. ����� �Է� ���� ����
		String seq = request.getParameter("seq"); // ���ڿ�
		// 2.DB ���� ó��
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq)); // ���ڿ��� ���ڷ� ��ȯ

		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = boardDAO.getBoard(vo);
		// 3. ���� ȭ�� ����(�˻� ��� ȭ�� ���)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>�� ��</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>�� �� </h1>");
		out.println("<a href='logout.do'>Log-out</a></h3>");
		out.println("<hr>");
		out.println("<form action='updateBoard.do' method='post'>");
		out.println("<input name='seq' type='hidden' value='" + board.getSeq() + "'/>");
		out.println("<table border='1' cellpadding='0' cellspacing='0'>");
		out.println("<tr>");
		out.println("<td bgcolor='orange' width='70'>����</td>");
		out.println("<td align='left'><input name='title' type='text' value=' " + board.getTitle() + "'/></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td bgcolor='orange'>�ۼ���</td>");
		out.println("<td align='left'>" + board.getWriter() + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td bgcolor='orange'>����</td>");
		out.println("<td align='left'><textarea name='content' cols='40' rows='10'>" + board.getContent()
				+ "</textarea></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td bgcolor='orange'>�����</td>");
		out.println("<td align='left'>" + board.getRegDate() + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td bgcolor='orange'>��ȸ��</td>");
		out.println("<td align='left'>" + board.getCnt() + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center'>");
		out.println("<input type='submit' value='�� ����'/>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("<hr>");
		out.println("<a href='insertBoard.html'>�۵��</a>&nbsp;&nbsp;&nbsp;");

		// admin ������ ���� ������Ը� �ۻ��� ��ũ�� ���̰� ��
		HttpSession session = request.getSession(); //��������üũ �ּ�ó���� ���� session ��ü ���� ����
		
		//UserVO�� ������ ���ǿ� ������ ���
//		String userRole = (String) session.getAttribute("userRole");
//		if (userRole.equals("ADMIN")) {
//			out.println("<a href='deleteBoard.do?seq=" + board.getSeq() + "'>�ۻ���</a>&nbsp;&nbsp;&nbsp;");
//		}
		
		//UserVO�� ��°�� �޾ƿ��� ���
		UserVO user = (UserVO) session.getAttribute("user");
		if (user.getRole().equals("ADMIN")) {
			out.println("<a href='deleteBoard.do?seq=" + board.getSeq() + "'>�ۻ���</a>&nbsp;&nbsp;&nbsp;");
		}		
		
//      out.println("<a href='deleteBoard.do?seq=" + board.getSeq() +"'>�ۻ���</a>&nbsp;&nbsp;&nbsp;");

		out.println("<a href='getBoardList.do'>�۸��</a>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		out.close();

	}

}