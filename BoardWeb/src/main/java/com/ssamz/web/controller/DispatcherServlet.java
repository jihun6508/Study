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
		
		//����� ��û path ����
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		if(path.equals("/login.do")) {
			System.out.println("�α��� ó��");
			//1. ����� �Է� ���� ����
			String id = request.getParameter("id");
			String password = request.getParameter("password");

			//2. DB ���� ó��
			UserVO vo = new UserVO();
			vo.setId(id);

			UserDAO dao = new UserDAO();
			UserVO user = dao.getUser(vo);


			//3.����ȭ�� ����
			//���� �޽����� ���� ���ڵ� ����
			HttpSession session = request.getSession();
			if(user != null && user.getPassword().equals(password)) {
				//���� ������ userVO ���·� ��°�� ����
				session.setAttribute("user", user);
				//�� ��� ȭ������ �̵�
				RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			}
			
		} else if(path.equals("/insertUser.do")) {
			System.out.println("ȸ������ ó��");
			//1. ����� ���� ���� (login html �� name���� ��Ī)
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String role = request.getParameter("role");


			//2. DB ���� ó��
			UserVO vo= new UserVO();
			vo.setId(id);
			vo.setPassword(password);
			vo.setName(name);
			vo.setRole(role);
			
			UserDAO dao = new UserDAO();
			dao.insertUser(vo);
			
			//3. ȭ�� �̵�
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			dispatcher.forward(request, response);

		} else if(path.equals("/logout.do")) {
			System.out.println("�α׾ƿ� ó��");
			HttpSession session = request.getSession();
			session.invalidate();

			response.sendRedirect("/");

		} else if(path.equals("/insertBoard.do")) {
			System.out.println("�� ��� ó��");
			//1. ����� ���� ����

			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");

			//2. DB ���� ó��
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);

			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(vo);

			//3. ȭ�� �̵�
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
			
		} else if(path.equals("/updateBoard.do")) {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
			
		} else if(path.equals("/deleteBoard.do")) {
			System.out.println("�� ���� ó��");
			// 1. ����� �Է� ���� ���� => ����� �Է� �ѱ����ڵ�, ����� �Է��� getParameter�� ��������
			String seq = request.getParameter("seq");

			//2. DB ���� ó�� : VO�� ���� �����ϰų� �������� ������ �Ѵ�. 
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq)); // ���ڷεǾ� �ִ� �Ϸù�ȣ�� ���ڷ� ��ȯ 

			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(vo);

			// 3. ȭ�� �̵� : ������ ������ �� �� �����ִ� ������
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
			
		} else if(path.equals("/getBoard.do")) {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
			
		}  else if(path.equals("/getBoardList.do")) {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
			
		}
		
		
	}
}
