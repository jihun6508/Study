package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.web.biz.board.BoardDAO;
import com.ssamz.web.biz.board.BoardVO;
import com.ssamz.web.controller.Controller;

public class GetBoardController implements Controller {

	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		System.out.println("�� ����ȸ ó��");
		// 1. ����� �Է� ���� ����
		String seq = request.getParameter("seq");
		// 2.DB ���� ó��
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq)); // Ŭ���� �Ϸù�ȣ�� ���� ã�Ƽ� ó��

		BoardDAO boardDAO = new BoardDAO(); // �� DAO
		BoardVO board = boardDAO.getBoard(vo);
		//3. ȭ�� �̵�
		request.setAttribute("board", board);

		return "getBoard";
	}

}
