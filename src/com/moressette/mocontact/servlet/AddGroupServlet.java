package com.moressette.mocontact.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moressette.mocontact.dao.ContactDao;
import com.moressette.mocontact.dao.impl.ContactDaoimpl;

/**
 * Servlet implementation class AddGroupServlet
 */
@WebServlet("/AddGroupServlet")
public class AddGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		/*Get form info*/
		String gname = request.getParameter("groupname");
		/*Get uid*/
		int uid = Integer.parseInt(request.getSession().getAttribute("uid").toString());
		/*Get Reverse MAP */
		Map<String,Integer> userGroupsR = (Map<String,Integer>)request.getSession().getAttribute("userGroupsR");
		/*Validate gname*/
		boolean flag=userGroupsR.containsKey(gname);
		if(flag==false){
		    ContactDao contactdao = new ContactDaoimpl();
		    boolean result = contactdao.addGroups(gname, uid);
		    if(result==true){
				response.sendRedirect("ManageGroupsServlet");
		    }else{
		    	out.println("<script>alert('修改失败');window.location.href=('ManageGroupsServlet')</script>");
		    }
		}else{
	    	out.println("<script>alert('群组已存在');window.location.href=('ManageGroupsServlet')</script>");
		}
	}

}
