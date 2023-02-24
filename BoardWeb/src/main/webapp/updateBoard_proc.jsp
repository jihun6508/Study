<%@page import="com.ssamz.web.biz.board.BoardDAO"%>
<%@page import="com.ssamz.web.biz.board.BoardVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>


<%
//1. 사용자 입력 정보 추출
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
RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
dispatcher.forward(request, response);
%>