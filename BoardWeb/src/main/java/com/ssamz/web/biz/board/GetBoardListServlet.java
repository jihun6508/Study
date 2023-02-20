package com.ssamz.web.biz.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		//1. DB ���� ó��
		BoardVO vo = new BoardVO();
		
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		
		//2. ���� ȭ�� ����
////		���� ���� Ȯ�� �� ��� �׽�Ʈ�� �ڵ�
//		response.setContentType("text/html; charset=EUC-KR");
//		PrintWriter out = response.getWriter();
//		
//		out.println("<h1>�Խñ� ���</h1>");
//		out.println("<hr>");
//		
//		for(BoardVO board : boardList) {
//			out.println("---> "+ board.toString()+"</br>");
//		}
//		
//		out.close();
		
		response.setContentType("text/html; charset=EUC-KR");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		
		out.println("<title>�� ���</title>");
		out.println("</head>");

		out.println("<body>");
		out.println("<center>");
		out.println("<h1>�Խñ� ���</h1>");
		out.println("<h3>�׽��ʹ� �α��� ȯ���մϴ�......");
		out.println("<a href='logout.do'>Log-out</a></h3>");
		
		out.println("<table border='1' cellpadding='0' cellspacing='0' width='700'>");
		out.println("<tr>");
		out.println("<th bgcolor='orange' width='100'>��ȣ</th>");
		out.println("<th bgcolor='orange' width='200'>����</th>");
		out.println("<th bgcolor='orange' width='150'>�ۼ���</th>");
		out.println("<th bgcolor='orange' width='150'>�����</th>");
		out.println("<th bgcolor='orange' width='100'>��ȸ��</th>");
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
		out.println("<a href='insertBoard.html'>���� ���</a>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
