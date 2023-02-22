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
//�� ��� ����

@WebServlet("/getBoardList.do")
public class GetBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	
	public void init() {
		System.out.println("initȣ��");
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. ���� ���� üũ
		//0-1 ��Ű ���·� ����� ���
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

		//0-2.���� ��� �׽�Ʈ
		/*
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		if(session.isNew()) {
			System.out.println("===> ó�� ������ ���� : "+sessionId);
		} else {
			System.out.println("---> �������� ����"+sessionId);
		}
		*/
		//0-3. ������ �̿��� ���� ���� üũ ����  -> ���� �����Ͱ� ������ �α��� â���� �����̷�Ʈ��
		HttpSession session = request.getSession();

		String userId = (String) session.getAttribute("userId");
		if(userId == null) {
			response.sendRedirect("/");
		}
		
		//1. ����� �Է� ���� ����
		//1-1.���ڵ� ó��
		//encoidng doFilter�� �̵� �� �ּ�ó��
		/*
		ServletContext context = request.getServletContext();
		String encoding = context.getInitParameter("boardEncoding");
		request.setCharacterEncoding(encoding);
		*/
		
		//1-2. �Է� ���� ȹ��
		String searchCondition = request.getParameter("searchCondition");
		String searchKeyword = request.getParameter("searchKeyword");
		
		//null check
		if(searchCondition==null) searchCondition = "TITLE";
		if(searchKeyword == null) searchKeyword = "";  
		
		//���ǿ� �˻� ���� ���� ����
		session.setAttribute("condition", searchCondition);
		session.setAttribute("keyword", searchKeyword);
		
		//2. DB ���� ó��
		BoardVO vo = new BoardVO();
		vo.setSearchCondition(searchCondition);
		vo.setSearchKeyword(searchKeyword);
		
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		
		//3. ���� ȭ�� ����
		//���� ���� Ȯ�� �� ��� �׽�Ʈ�� �ڵ�
		/*
		response.setContentType("text/html; charset=EUC-KR");
		PrintWriter out = response.getWriter();
		
		out.println("<h1>�Խñ� ���</h1>");
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
		
		out.println("<title>�� ���</title>");
		out.println("</head>");

		out.println("<body>");
		out.println("<center>");
		out.println("<h1>�Խñ� ���</h1>");
		
		
		
		
		//���� ���̵� �������� �α��� ���� ǥ��
		String userName = (String) session.getAttribute("userName");
//		out.println("<h3>"+userName +"�� �α����� ȯ���մϴ�....");
		
		//session�� �������� �޽��� �Ѹ�
//		String welcomeMessage = (String) session.getAttribute("welcomeMessage");
		
		//HttpServletRequest���� �޾Ƽ� �Ѹ�
//		String welcomeMessage = (String) request.getAttribute("welcomeMessage");

		//ServletContext���� �޾Ƽ� �Ѹ�
		//encoding �ּ�ó���� ���� context ��ü ������ ���ϰ� �Ǿ� �ڵ� ��� �Ұ�, �ּ� ó��
//		String welcomeMessage = (String) context.getAttribute("welcomeMessage");
//		out.println("<h3>" + userName + welcomeMessage);
		
		//�ؽ�Ʈ�� ����
//		out.println("<h3>�׽��ʹ� �α��� ȯ���մϴ�......");
		
		//�˻�ȭ��
		out.println("<!--�˻� ����-->");
		out.println("<form action='getBoardList.do' method='post'");
		out.println("<table border='1' cellpadding='0' cellspacing='0' width='700'>");
		out.println("<tr>");
		out.println("<td align='right'>");
		out.println("<select name='searchCondition'>");

		//���� �ڵ�
//		out.println("<option value='TITLE'>����");
//		out.println("<option value='CONTENT'>����");

		
		//�˻����� ���� �⺻ ���õ� �˻� �ɼ� ���� ����
		String condition = (String) session.getAttribute("condition");
		if(condition.equals("TITLE")) {
			out.println("<option value='TITLE' selected>����");
		} else {
			out.println("<option value='TITLE'>����");
		}
		
		if(condition.equals("CONTENT")) {
			out.println("<option value='CONTENT' selected>����");
		} else {
			out.println("<option value='CONTENT'>����");
		}
		out.println("</selection>");
		
		out.println("<input name='searchKeyword' type='text' value='"+session.getAttribute("keyword")+"'/>");

//		out.println("<input name='searchKeyword' type='text'/>");
		out.println("<input type='submit' value='�˻�'/>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("<!--�˻� ����-->");

		
		
		//�α׾ƿ�
		out.println("<a href='logout.do'>Log-out</a></h3>");
		
		//�޴���
		out.println("<table border='1' cellpadding='0' cellspacing='0' width='700'>");
		out.println("<tr>");
		out.println("<th bgcolor='orange' width='100'>��ȣ</th>");
		out.println("<th bgcolor='orange' width='200'>����</th>");
		out.println("<th bgcolor='orange' width='150'>�ۼ���</th>");
		out.println("<th bgcolor='orange' width='150'>�����</th>");
		out.println("<th bgcolor='orange' width='100'>��ȸ��</th>");
		out.println("</tr>");
		
		//�� ����Ʈ
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
		out.println("<a href='insertBoard.html'>���� ���</a>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
