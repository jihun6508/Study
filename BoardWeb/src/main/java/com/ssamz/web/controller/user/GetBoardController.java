package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.web.biz.board.BoardDAO;
import com.ssamz.web.biz.board.BoardVO;
import com.ssamz.web.controller.Controller;

public class GetBoardController implements Controller {

	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		System.out.println("글 상세조회 처리");
		// 1. 사용자 입력 정보 추출
		String seq = request.getParameter("seq");
		// 2.DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq)); // 클릭한 일련번호의 값을 찾아서 처리

		BoardDAO boardDAO = new BoardDAO(); // 모델 DAO
		BoardVO board = boardDAO.getBoard(vo);
		//3. 화면 이동
		request.setAttribute("board", board);

		return "getBoard";
	}

}
