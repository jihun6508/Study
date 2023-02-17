package com.saamz.web.user;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saamz.web.biz.user.UserDAO;
import com.saamz.web.biz.user.UserVO;

/**
 * Servlet implementation class InsertUserServlet
 */
public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String encoding;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		encoding = config.getInitParameter("boardEncoding");
		System.out.println("---> Encoding : "+ encoding);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ڵ� ó��(�ѱ� �����°� ����)
//		request.setCharacterEncoding("UTF-8");  //init-param�� ����ϱ� ��
		request.setCharacterEncoding(encoding); //init() ���� �ʱ�ȭ �ϴ� ���
		
		/*
		 * init()�� �ƴ� service���� �ʱ�ȭ�ϴ� ���.
		ServletConfig config = getServletConfig();
		encoding = config.getInitParameter("boardEncoding");
		System.out.println("---> Encoding : "+ encoding);
		*/
		
		//1. ����� ���� ���� (login html �� name���� ��Ī)
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String role = request.getParameter("role");
		String selfInfo = request.getParameter("selfInfo");
		String[] languages = request.getParameterValues("languages");
		String age = request.getParameter("age");

		System.out.println("���̵� : "+id);
		System.out.println("��й�ȣ : "+password);
		System.out.println("�̸� : "+name);
		System.out.println("���� : "+role);
		System.out.println("�ڱ� �Ұ� : "+selfInfo);
		System.out.println("��� ����");
		for(String language : languages) {
			System.out.print(language +", ");
		}
		System.out.println();
		System.out.println("���� : "+age);

		//2. DB ���� ó��
		UserVO vo= new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		vo.setName(name);
		vo.setRole(role);
		
		UserDAO dao = new UserDAO();
		dao.insertUser(vo);
		
		//3. ȭ�� �̵�
		response.sendRedirect("login.html");
	}

}