<%@page import="com.ssamz.web.biz.board.BoardDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ssamz.web.biz.board.BoardVO"%>

<%@ page contentType="text/html; charset=UTF-8"
	errorPage="errors/boardError.jsp"%>

<%@ include file='../layout/header.jsp'%>


<%
	//1. 컨트롤러(Servlet)가 모델(DAO)을 이용하여 request에 등록한 글 목록을 꺼낸다.
	List<BoardVO> boardList = (List)request.getAttribute("boardList");
	
	//3. 화면 이동
	
%>
<center>

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
				<td align="right">
				<select name="searchCondition">
						<option value="TITLE" selected>제목
						<c:if test="${condition == 'TITLE'}">selected</c:if>제목
						<option value="COTNET">내용
						<c:if test="${condition == 'CONTENT'}">selected</c:if>내용
				</select>
				<input
					name="searchKeyword" value="${keyword }" /> <input type="submit"
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
		<c:forEach	var="board" items="${boardList }">
		<tr>
			<td>${board.seq}</td>
			<td align="left"><a href="getBoard.do?seq=${board.seq }">${board.title }</a></td>
			<td>${board.writer }</td>
			<td>${board.regDate }</td>
			<td>${board.cnt }</td>
		</tr>
		</c:forEach>

	</table>
</center>

<%@ include file='../layout/footer.jsp'%>