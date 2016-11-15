package com.moressette.mocontact.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moressette.mocontact.dao.UserDao;
import com.moressette.mocontact.dao.impl.UserDaoimpl;
import com.moressette.mocontact.entity.User;



/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		String realname = request.getParameter("realname");
		User user = new User(username,pwd,realname);
		UserDao userdao = new UserDaoimpl();
		boolean flag = userdao.register(user);
		if(flag){
			request.getSession().setAttribute("result", "注册成功");
			request.getSession().setAttribute("resultImg", "img/completed.png");
			request.getSession().setAttribute("resultAddr", "login.jsp");
		}else
		{
			request.getSession().setAttribute("result", "注册失败 用户名已存在");
			request.getSession().setAttribute("resultImg", "img/failed.png");
			request.getSession().setAttribute("resultAddr", "register.jsp");
		}
		out.println("<script>window.location.href=('result.jsp');</script>");
	}

}
