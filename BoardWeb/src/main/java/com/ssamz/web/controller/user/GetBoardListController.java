package com.ssamz.web.controller.user;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssamz.web.biz.board.BoardDAO;
import com.ssamz.web.biz.board.BoardVO;
import com.ssamz.web.controller.Controller;

public class GetBoardListController implements Controller {

	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		System.out.println("�� ��� �˼� ó��");
		//1. ����� �Է� ���� ����
		String searchCondition = request.getParameter("searchCondition");
		String searchKeyword = request.getParameter("searchKeyword");
		
		//null check
		if(searchCondition==null) searchCondition = "TITLE";
		if(searchKeyword == null) searchKeyword = "";  
		
		//���ǿ� �˻� ���� ���� ����
		HttpSession session = request.getSession();
		session.setAttribute("condition", searchCondition);
		session.setAttribute("keyword", searchKeyword);
		
		//2. DB ���� ó��
		BoardVO vo = new BoardVO();
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		
		BoardDAO dao = new BoardDAO();
		List<BoardVO> boardList = dao.getBoardList(vo);
		
		//3. ȭ�� �̵�
		request.setAttribute("boardList", boardList);

		return "getBoardList";
	}

}
