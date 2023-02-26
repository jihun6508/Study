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
		System.out.println("글 목록 검섹 처리");
		//1. 사용자 입력 정보 추출
		String searchCondition = request.getParameter("searchCondition");
		String searchKeyword = request.getParameter("searchKeyword");
		
		//null check
		if(searchCondition==null) searchCondition = "TITLE";
		if(searchKeyword == null) searchKeyword = "";  
		
		//세션에 검색 관련 정보 저장
		HttpSession session = request.getSession();
		session.setAttribute("condition", searchCondition);
		session.setAttribute("keyword", searchKeyword);
		
		//2. DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		
		BoardDAO dao = new BoardDAO();
		List<BoardVO> boardList = dao.getBoardList(vo);
		
		//3. 화면 이동
		request.setAttribute("boardList", boardList);

		return "getBoardList";
	}

}
