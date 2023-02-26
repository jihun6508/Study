package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.web.biz.board.BoardDAO;
import com.ssamz.web.biz.board.BoardVO;
import com.ssamz.web.controller.Controller;

public class DeleteBoardController implements Controller {
	
	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		System.out.println("�� ���� ó��");
		// 1. ����� �Է� ���� ���� => ����� �Է� �ѱ����ڵ�, ����� �Է��� getParameter�� ��������
		String seq = request.getParameter("seq");

		//2. DB ���� ó�� : VO�� ���� �����ϰų� �������� ������ �Ѵ�. 
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq)); // ���ڷεǾ� �ִ� �Ϸù�ȣ�� ���ڷ� ��ȯ 

		BoardDAO boardDAO = new BoardDAO();
		boardDAO.deleteBoard(vo);

		// 3. ȭ�� �̵� : ������ ������ �� �� �����ִ� ������


		return "getBoardList.do";
	}

}
