package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.web.biz.board.BoardDAO;
import com.ssamz.web.biz.board.BoardVO;
import com.ssamz.web.controller.Controller;

public class InsertBoardController implements Controller {
	
	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		System.out.println("글 등록 처리");
		//1. 사용자 정보 추출

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		//2. DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setContent(content);

		BoardDAO boardDAO = new BoardDAO();
		boardDAO.insertBoard(vo);

		//3. 화면 이동

		return "getBoardList.do";
	}

}
