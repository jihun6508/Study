<%@page import="com.ssamz.web.biz.board.BoardDAO"%>
<%@page import="com.ssamz.web.biz.board.BoardVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>

<%
// 1. 사용자 입력 정보 추출
String seq = request.getParameter("seq");
// 2.DB 연동 처리
BoardVO vo = new BoardVO();
vo.setSeq(Integer.parseInt(seq)); // 클릭한 일련번호의 값을 찾아서 처리

BoardDAO boardDAO = new BoardDAO(); // 모델 DAO
BoardVO board = boardDAO.getBoard(vo);
//3. 화면 이동
%>

<%@ include file="../layout/header.jsp"%>

<center>
	<form action='updateBoard_proc.jsp' method='post'>
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
	<a href="deleteBoard_proc.jsp?seq=<%=board.getSeq()%>">글삭제</a>
	<%
	}
	%>

</center>
<%@ include file="../layout/footer.jsp"%>
