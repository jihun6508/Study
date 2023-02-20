package com.ssamz.web.biz.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteBoard.do")
public class DeleteBoardServlet extends HttpServlet {
	static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. ����� �Է� ���� ���� => ����� �Է� �ѱ����ڵ�, ����� �Է��� getParameter�� ��������
		String seq = request.getParameter("seq");
		
		//2. DB ���� ó�� : VO�� ���� �����ϰų� �������� ������ �Ѵ�. 
		BoardVO vo= new BoardVO();
		vo.setSeq(Integer.parseInt(seq)); // ���ڷεǾ� �ִ� �Ϸù�ȣ�� ���ڷ� ��ȯ 

		BoardDAO boardDAO = new BoardDAO();
		boardDAO.deleteBoard(vo);
		
		// 3. ȭ�� �̵� : ������ ������ �� �� �����ִ� ������
		response.sendRedirect("getBoardList.do");
		
	}
}
