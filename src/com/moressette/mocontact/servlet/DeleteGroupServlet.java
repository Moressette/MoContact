package com.moressette.mocontact.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moressette.mocontact.dao.ContactDao;
import com.moressette.mocontact.dao.impl.ContactDaoimpl;

/**
 * Servlet implementation class DeleteGroupServlet
 */
@WebServlet("/DeleteGroupServlet")
public class DeleteGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if(request.getSession().getAttribute("username") == null){
			out.println("<script type='text/javascript'>alert('您还没有登录，请先登录');window.location.href='login.jsp'; </script>");
		}
		int gid = Integer.parseInt(request.getParameter("gid"));
		if(gid == 0){
			out.println("<script>alert('无法删除该分组');window.location.href=('ManageGroupsServlet')</script>");
		}
		int uid = Integer.parseInt(request.getSession().getAttribute("uid").toString()); 
		ContactDao contactdao = new ContactDaoimpl();
		boolean flag = contactdao.hasNoContactByGidAndUid(gid,uid);
		if(flag==true){
			boolean result = contactdao.deleteGroup(gid);
			if(result == true){
				response.sendRedirect("ManageGroupsServlet");
			}else{
				out.println("<script>alert('删除失败');window.location.href=('ManageGroupsServlet')</script>");
			}
		}else{
			out.println("<script>alert('无法删除，群组中有联系人');window.location.href=('ManageGroupsServlet')</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
