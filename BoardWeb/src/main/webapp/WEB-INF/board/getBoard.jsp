<%@page import="com.ssamz.web.biz.board.BoardDAO"%>
<%@page import="com.ssamz.web.biz.board.BoardVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>

<%
	// 1. 컨트롤러(Serlet)가 모델(DAO)을 이용하여 request에 등록한 글 목록을 꺼낸다.
	BoardVO board = (BoardVO) request.getAttribute("board");
	//3. 화면 이동
%>

<%@ include file="../layout/header.jsp"%>

<center>
	<form action='updateBoard.do' method='post'>
		<input name='seq' type='hidden' value="<%=board.getSeq()%>" />
		<table border='1' cellpadding='0' cellspacing='0'>

			<tr>
				<td bgcolor='orange' width='70'>제목</td>
				<td align='left'><input name='title' type='text'
					value="<%=board.getTitle()%>" /></td>
			</tr>
			<tr>
				<td bgcolor='orange'>작성자</td>
				<td align='left'><%=board.getWriter()%></td>
			</tr>
			<tr>
				<td bgcolor='orange'>내용</td>
				<td align='left'><textarea name='content' cols='40' rows='10'>
		<%=board.getContent()%></textarea></td>
			</tr>
			<tr>
				<td bgcolor='orange'>등록일</td>
				<td align='left'><%=board.getRegDate()%></td>
			</tr>
			<tr>
				<td bgcolor='orange'>조회수</td>
				<td align='left'><%=board.getCnt()%></td>
			</tr>
			<tr>
				<td colspan='2' align='center'><input type='submit'
					value='글 수정' /></td>
			</tr>
		</table>
	</form>
	<br>
	<%
	if (user.getRole().equals("ADMIN")) {
	%>
	<a href="deleteBoard.do?seq=<%=board.getSeq()%>">글삭제</a>
	<%
	}
	%>

</center>
<%@ include file="../layout/footer.jsp"%>
