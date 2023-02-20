package com.ssamz.web.biz.board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateBoard.do")
public class UpdateBoardServlet extends HttpServlet {
	static final long serialVersionUID = 1L;
	private String encoding;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. ����� �Է� ���� ����
		ServletContext context = getServletContext();
		this.encoding = context.getInitParameter("boardEncoding");
		request.setCharacterEncoding(encoding);
		String title = request.getParameter("title");
		String seq = request.getParameter("seq");
		String content = request.getParameter("content");

		//2. DB ���� ó��
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setSeq(Integer.parseInt(seq));
		vo.setContent(content);
		
		BoardDAO boardDAO = new BoardDAO();
		//2-1. ��� ȣ��
		boardDAO.updateBoard(vo);
		
		//3. ȭ�� �̵�
		response.sendRedirect("getBoardList.do");
	}
}
