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
		//���ǿ� userId ������ ��ϵǾ� �ִ��� Ȯ���Ѵ�.
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
//		//���ǿ� ���� ������ �����ؾ� ���� ����
//		if(session.getAttribute("userId")==null) {
//			res.sendRedirect("/");
//		} else {
//			chain.doFilter(request, response);
//		}
		
		//���ǿ� userVO�� ��°�� �������� ���� �ڵ�
		if(session.getAttribute("user")==null) {
			res.sendRedirect("/");
		} else {
			chain.doFilter(request, response);
		}
	}
	
}
