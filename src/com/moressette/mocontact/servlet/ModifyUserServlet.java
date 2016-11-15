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
 * Servlet implementation class ModifyUserServlet
 */
@WebServlet("/ModifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if(request.getSession().getAttribute("username") == null){
			out.println("<script type='text/javascript'>alert('您还没有登录，请先登录');window.location.href='login.jsp'; </script>");
		}
		/*
		int uid = Integer.parseInt(request.getSession().getAttribute("uid").toString());
		UserDao userdao = new UserDaoimpl();
		User user = userdao.findUserInfo(uid);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if(request.getSession().getAttribute("username") == null){
			out.println("<script type='text/javascript'>alert('您还没有登录，请先登录');window.location.href='login.jsp'; </script>");
		}
		
		int uid = Integer.parseInt(request.getSession().getAttribute("uid").toString());
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		String realname = request.getParameter("realname");
		UserDao userdao = new UserDaoimpl();
		boolean flag = userdao.modifyUser(uid, username, pwd, realname);
		if(flag){
			request.getSession().invalidate();
			request.getSession().setAttribute("result", "用户信息修改成功，请重新登录");
			request.getSession().setAttribute("resultImg", "img/completed.png");
			request.getSession().setAttribute("resultAddr", "login.jsp");
		}else{
			request.getSession().setAttribute("result", "用户信息修改失败");
			request.getSession().setAttribute("resultImg", "img/failed.png");
			request.getSession().setAttribute("resultAddr", "MainServlet");
		}
		out.println("<script>window.location.href=('result.jsp');</script>");
	}

}
