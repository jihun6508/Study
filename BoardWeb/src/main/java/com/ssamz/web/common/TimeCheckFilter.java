package com.ssamz.web.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class TimeCheckFilter
 */
@WebFilter(urlPatterns = "*.do")
public class TimeCheckFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public TimeCheckFilter() {
    	System.out.println("===> TimeCheckFilter ����");
    }
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("---> doFilter() ȣ��");
		
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		long startTime = System.currentTimeMillis();
		System.out.println("---[���� ó��]---");
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		System.out.println("---[���� ó��]---");
		
		long endTime = System.currentTimeMillis();
		
		System.out.println(path + "���� ���࿡ �ҿ�� �ð� : "+(endTime - startTime)+"(ms)��");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("--->init()ȣ��");
	}
	
	public void destroy() {
		System.out.println("---> destroy() ȣ��");
	}
}
