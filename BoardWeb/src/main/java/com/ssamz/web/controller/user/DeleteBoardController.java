package com.ssamz.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.web.biz.board.BoardDAO;
import com.ssamz.web.biz.board.BoardVO;
import com.ssamz.web.controller.Controller;

public class DeleteBoardController implements Controller {
	
	public String handleRequest(HttpServletRequest request, HttpServletResponse Response) {
		System.out.println("글 삭제 처리");
		// 1. 사용자 입력 정보 추출 => 사용자 입력 한글인코딩, 사용자 입력을 getParameter로 가져오기
		String seq = request.getParameter("seq");

		//2. DB 연동 처리 : VO는 값을 저장하거나 가져오는 역할을 한다. 
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq)); // 문자로되어 있는 일련번호를 숫자로 변환 

		BoardDAO boardDAO = new BoardDAO();
		boardDAO.deleteBoard(vo);

		// 3. 화면 이동 : 서버가 응답을 한 후 보내주는 데이터


		return "getBoardList.do";
	}

}
