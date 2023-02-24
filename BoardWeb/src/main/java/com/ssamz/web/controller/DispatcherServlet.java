package com.ssamz.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssamz.web.biz.board.BoardDAO;
import com.ssamz.web.biz.board.BoardVO;
import com.ssamz.web.biz.user.UserDAO;
import com.ssamz.web.biz.user.UserVO;
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//사용자 요청 path 추출
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		if(path.equals("/login.do")) {
			System.out.println("로그인 처리");
			//1. 사용자 입력 정보 추출
			String id = request.getParameter("id");
			String password = request.getParameter("password");

			//2. DB 연동 처리
			UserVO vo = new UserVO();
			vo.setId(id);

			UserDAO dao = new UserDAO();
			UserVO user = dao.getUser(vo);


			//3.응답화면 구성
			//응답 메시지에 대한 인코딩 설정
			HttpSession session = request.getSession();
			if(user != null && user.getPassword().equals(password)) {
				//유저 정보를 userVO 형태로 통째로 저장
				session.setAttribute("user", user);
				//글 목록 화면으로 이동
				RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			}
			
		} else if(path.equals("/insertUser.do")) {
			System.out.println("회원가입 처리");
			//1. 사용자 정보 추출 (login html 의 name값과 매칭)
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String role = request.getParameter("role");


			//2. DB 연동 처리
			UserVO vo= new UserVO();
			vo.setId(id);
			vo.setPassword(password);
			vo.setName(name);
			vo.setRole(role);
			
			UserDAO dao = new UserDAO();
			dao.insertUser(vo);
			
			//3. 화면 이동
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			dispatcher.forward(request, response);

		} else if(path.equals("/logout.do")) {
			System.out.println("로그아웃 처리");
			HttpSession session = request.getSession();
			session.invalidate();

			response.sendRedirect("/");

		} else if(path.equals("/insertBoard.do")) {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
			
		} else if(path.equals("/updateBoard.do")) {
			System.out.println("글 수정 처리");
			String title = request.getParameter("title");
			String seq = request.getParameter("seq");
			String content = request.getParameter("content");

			//2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setSeq(Integer.parseInt(seq));
			vo.setContent(content);

			BoardDAO boardDAO = new BoardDAO();
			//2-1. 기능 호출
			boardDAO.updateBoard(vo);

			//3. 화면 이동
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
			
		} else if(path.equals("/deleteBoard.do")) {
			System.out.println("글 삭제 처리");
			// 1. 사용자 입력 정보 추출 => 사용자 입력 한글인코딩, 사용자 입력을 getParameter로 가져오기
			String seq = request.getParameter("seq");

			//2. DB 연동 처리 : VO는 값을 저장하거나 가져오는 역할을 한다. 
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq)); // 문자로되어 있는 일련번호를 숫자로 변환 

			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(vo);

			// 3. 화면 이동 : 서버가 응답을 한 후 보내주는 데이터
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
			
		} else if(path.equals("/getBoard.do")) {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
			
		}  else if(path.equals("/getBoardList.do")) {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
			
		}
		
		
	}
}
