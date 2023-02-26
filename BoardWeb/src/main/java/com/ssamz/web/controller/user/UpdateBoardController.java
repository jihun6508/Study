package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.web.biz.board.BoardDAO;
import com.ssamz.web.biz.board.BoardVO;
import com.ssamz.web.controller.Controller;

public class UpdateBoardController implements Controller {
	
	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		System.out.println("�� ���� ó��");
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

		return "getBoardList.do";
	}

}
