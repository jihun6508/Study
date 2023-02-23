package com.ssamz.web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/getBoardList.do", "/getBoard.do", "/deleteBoard.do"})
public class AuthenticationFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//세션에 userId 정보가 등록되어 있는지 확인한다.
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
//		//세션에 개별 정보를 저장해쓸 때의 ㅋ드
//		if(session.getAttribute("userId")==null) {
//			res.sendRedirect("/");
//		} else {
//			chain.doFilter(request, response);
//		}
		
		//세션에 userVO를 통째로 저장햇을 때의 코드
		if(session.getAttribute("user")==null) {
			res.sendRedirect("/");
		} else {
			chain.doFilter(request, response);
		}
	}
	
}
