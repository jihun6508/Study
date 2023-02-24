<%@page import="com.ssamz.web.biz.board.BoardDAO"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ssamz.web.biz.board.BoardVO" %>

<%@ page contentType="text/html; charset=UTF-8" errorPage="errors/boardError.jsp"%>

<%@ include file='../layout/header.jsp' %>


	<%
		//1. 사용자 입력 정보 추출
		String searchCondition = request.getParameter("searchCondition");
		String searchKeyword = request.getParameter("searchKeyword");
		
		//null check
		if(searchCondition==null) searchCondition = "TITLE";
		if(searchKeyword == null) searchKeyword = "";  
		
		//세션에 검색 관련 정보 저장
		session = request.getSession();
		session.setAttribute("condition", searchCondition);
		session.setAttribute("keyword", searchKeyword);
		
		//2. DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		
		BoardDAO dao = new BoardDAO();
		List<BoardVO> boardList = dao.getBoardList(vo);
		
		//3. 화면 이동
	
	%>
	<center>
	<h1><font color='green'>게스트</font>님 환영합니다</h1>
	
	<%--
	<% HttpSession session = request.getSession(); %>
	세션 아이디 : <%=session.getId() %>
	 --%>
<%-- 	<%
		for(BoardVO board : boardList){
			out.print(board.getSeq() + " : " + board.getTitle() + " : " + board.getContent() + "<br>");
		}
		
	%> --%>

		<!-- 검색 시작 -->
		<form action="/index.jsp" method="post">
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right"><select name="searchCondition">
							<option value="TITLE">제목
							<option value="COTNET">내용
					</select> <input name="searchKeyword" type="text" /> <input type="submit"
						value="검색" /></td>
				</tr>
			</table>
		</form>
		<!-- 검색 종료 -->

		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<th bgcolor="orange" width="100">번호</th>
				<th bgcolor="orange" width="200">제목</th>
				<th bgcolor="orange" width="150">작성자</th>
				<th bgcolor="orange" width="150">등록일</th>
				<th bgcolor="orange" width="100">조회수</th>
			</tr>

			<%
			for (BoardVO board : boardList) {
			%>

			<tr>
				<td><%=board.getSeq()%></td>
				<td align="left"><a href="getBoard.jsp?seq=<%=board.getSeq()%>"><%=board.getTitle()%></a></td>
				<td><%=board.getWriter()%></td>
				<td><%=board.getRegDate()%></td>
				<td><%=board.getCnt()%></td>
			</tr>
			<%
			}
			%>

		</table>
	</center>

<%@ include file='../layout/footer.jsp' %>