package com.saamz.web.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saamz.web.biz.user.UserDAO;
import com.saamz.web.biz.user.UserVO;



@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
		System.out.println("==>LoginServlet ȣ��");
    }
    
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//HTTP ��û ����
		System.out.println("service() ȣ��");
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
		
		//1. ����� �Է� ���� ����
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		//2. DB ���� ó��
		UserVO vo = new UserVO();
		vo.setId(id);
		
		UserDAO dao = new UserDAO();
		UserVO user = dao.getUser(vo);

		
		//3.����ȭ�� ����
		//���� �޽����� ���� ���ڵ� ����
		response.setContentType("text/html;charset=UTF-8");
		//HTTP ���� �������� message-body�� ����� ��� ��Ʈ�� ȹ��
		PrintWriter out = response.getWriter();
		
		//�޽��� ���
		if(user !=null) {
			if(user.getPassword().equals(password)) {
				out.println(user.getName() + "�� �α��� ȯ��<br>");
				out.println("<a href='/getBoardList.do'> �� ��� �̵� </a>");
				
			} else {
				out.println("��й�ȣ �����Դϴ�.<br>");
				out.println("<a href='/'>�ٽ� �α��� </a>");
			}
		} else {
			System.out.println("���̵� �����Դϴ�.<br>");
			System.out.println("<a href='/'>�ٽ� �α��� </a>");
		}
	}
}
