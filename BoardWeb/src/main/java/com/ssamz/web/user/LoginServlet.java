package com.ssamz.web.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssamz.web.biz.user.UserDAO;
import com.ssamz.web.biz.user.UserVO;



@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
		System.out.println("==>LoginServlet 호출");
    }
    
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//HTTP 요청 구조
    	/*
		System.out.println("service() 호출");
		System.out.println("------------Start Line----------");
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String protocol = request.getProtocol();
		System.out.println(method + " "+uri + " " + protocol);
		System.out.println("------------Message Header----------");
		System.out.println("Host: " + request.getHeader("host"));
		System.out.println("Connection : "+ request.getHeader("connection"));
		System.out.println("User-Agent : "+ request.getHeader("user-agent"));
		System.out.println("Accept : "+ request.getHeader("accept"));
		System.out.println("Accept-Encoding : "+ request.getHeader("accept-encoding"));
		System.out.println("Accep-Language : "+ request.getHeader("accept-languge"));
		*/
		
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
		PrintWriter out = response.getWriter();
		
		//메시지 출력
		if(user !=null) {
			if(user.getPassword().equals(password)) {
				//쿠키를 적용한 경우
				//상태 정보를 쿠키에 저장하여 전송한다.
				/*
				Cookie userId = new Cookie("userId", user.getId());
				response.addCookie(userId);
				*/
				//세션으로 구현
				HttpSession session = request.getSession();
//				session.setMaxInactiveInterval(600);//세션 유효시간을 10분으로 설정
				//유저 정보를 개별로 저장
//				session.setAttribute("userId", user.getId());
//				session.setAttribute("userName", user.getName());
//				session.setAttribute("userRole", user.getRole());
				
				//유저 정보를 userVO 형태로 통째로 저장
				session.setAttribute("user", user);
				
				
				//글 목록 화면에서 사용할 데이터를 HttpServletRequest에 등록 (리디렉트시 소멸)
//				request.setAttribute("welcomeMessage", "님 환영합니다.");
				
				//글 목록 화면에서 사용할 데이터를 Session에 등록(리디렉트 해도 소멸되지 않음)
//				session.setAttribute("welcomeMessage", "님 환영합니다.");
				
				//글 목록 화면에서 사용할 데이터를 ServletContext에 등록
//				ServletContext context = getServletContext();
//				context.setAttribute("welcomeMessage", "님 환영합니다.");
								
				//글 목록 화면으로 포워딩
				RequestDispatcher dispatcher = request.getRequestDispatcher("getBoardList.do");
				dispatcher.forward(request, response);

				//글 목록 화면으로 리디렉트 ->httpSubletRequest는 리디렉트 과정에서 소멸
//				response.sendRedirect("getBoardList.do");

				
			} else {
				out.println("비밀번호 오류입니다.<br>");
				out.println("<a href='/'>다시 로그인 </a>");
			}
		} else {
			out.println("아이디 오류입니다.<br>");
			out.println("<a href='/'>다시 로그인 </a>");
		}
	}
}
