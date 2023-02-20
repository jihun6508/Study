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
		// 1. 사용자 입력 정보 추출 => 사용자 입력 한글인코딩, 사용자 입력을 getParameter로 가져오기
		String seq = request.getParameter("seq");
		
		//2. DB 연동 처리 : VO는 값을 저장하거나 가져오는 역할을 한다. 
		BoardVO vo= new BoardVO();
		vo.setSeq(Integer.parseInt(seq)); // 문자로되어 있는 일련번호를 숫자로 변환 

		BoardDAO boardDAO = new BoardDAO();
		boardDAO.deleteBoard(vo);
		
		// 3. 화면 이동 : 서버가 응답을 한 후 보내주는 데이터
		response.sendRedirect("getBoardList.do");
		
	}
}
