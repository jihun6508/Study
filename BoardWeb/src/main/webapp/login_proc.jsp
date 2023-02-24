<%@page import="com.ssamz.web.biz.user.UserVO"%>
<%@page import="com.ssamz.web.biz.user.UserDAO"%>
<%@ page contentType="text/html; charset=UTF-8"
	errorPage="errors/boardError.jsp"%>

<%
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
response.setContentType("text/html;charset=UTF-8");
//HTTP 응답 프로토콜 message-body와 연결된 출력 스트림 획득

//메시지 출력
if(user !=null) {
	if(user.getPassword().equals(password)) {
		session = request.getSession();
		
		//유저 정보를 userVO 형태로 통째로 저장
		session.setAttribute("user", user);
		
		
		//글 목록 화면으로 리디렉트 ->httpSubletRequest는 리디렉트 과정에서 소멸
		response.sendRedirect("/index.jsp");

		
	} else {
		out.println("비밀번호 오류입니다.<br>");
		out.println("<a href='/'>다시 로그인 </a>");
	}
} else {
	out.println("아이디 오류입니다.<br>");
	out.println("<a href='/'>다시 로그인 </a>");
}


%>