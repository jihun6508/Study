<%@ page contentType="text/html; charset=UTF-8"
	errorPage="errors/boardError.jsp"%>

<%@ include file="../layout/header.jsp"%>
<center>
	<h1>회원가입</h1>
	<hr>
	<form action="insertUser_proc.jsp" method="post">
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange" width="90">아이디</td>
				<td><input type="text" name="id" size="12" /></td>
			</tr>
			<tr>
				<td bgcolor="orange">비밀번호</td>
				<td><input type="password" name="password" size="12" /></td>
			</tr>
			<tr>
				<td bgcolor="orange">이름</td>
				<td><input type="text" name="name" size="30" /></td>
			</tr>
			<tr>
				<td bgcolor="orange">권한</td>
				<td><input type="radio" name="role" value="USER" checked />USER
					<input type="radio" name="role" value="ADMIN" />ADMIN</td>
			</tr>
			<tr>
				<td bgcolor="orange">자기 소개</td>
				<td><textarea name="selfInfo" rows="5" cols="30">여기에 입력하세요.</textarea></td>
			</tr>
			<tr>
				<td bgcolor="orange">언어 경험</td>
				<td><input type="checkbox" checked name="languages" value="C">C
					<input type="checkbox" name="languages" value="Python">Python
					<input type="checkbox" checked name="languages" value="Java">Java
					<input type="checkbox" name="languages" value="Go">Go <input
					type="checkbox" name="languages" value="Javascript">Javascript
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">나이</td>
				<td><select name="age">
						<option>--선택--
						<option value="10대">10대
						<option value="20대">20대
						<option value="30대">30대
						<option value="40대">40대
						<option value="50대">50대
						<option value="60대 이상">60대 이상
				</select></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="회원가입" /> <input type="reset" value="취소" /></td>
			</tr>
		</table>
	</form>
</center>
<%@ include file='../layout/footer.jsp'%>
