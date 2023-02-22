package com.ssamz.web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

public class CharacterEncodingFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//서블릿이 실행되기 전에 인코딩을 처리
		ServletContext context = request.getServletContext();
		String encoding = context.getInitParameter("boardEncoding");
		request.setCharacterEncoding(encoding);
		
	}
}
