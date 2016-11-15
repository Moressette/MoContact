package com.moressette.mocontact.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moressette.mocontact.dao.ContactDao;
import com.moressette.mocontact.dao.impl.ContactDaoimpl;
import com.moressette.mocontact.entity.Contact;

/**
 * Servlet implementation class SearchContactServlet
 */
@WebServlet("/SearchContactServlet")
public class SearchContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchContactServlet() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if(request.getSession().getAttribute("username") == null){
			out.println("<script type='text/javascript'>alert('您还没有登录，请先登录');window.location.href='login.jsp'; </script>");
		}
		
		String cname = request.getParameter("search");
		request.getSession().setAttribute("searchInfo",cname);
		int uid = Integer.parseInt(request.getSession().getAttribute("uid").toString());
		ContactDao contactdao = new ContactDaoimpl();
		List<Contact> contactlist = contactdao.searchContact(cname, uid);
		if (!contactlist.isEmpty()){
			request.getSession().setAttribute("searchcontactlist", contactlist); //set to session
			out.print("<script>window.location.href=('search.jsp');</script>");
		}else{
			out.print("<script>alert('联系人未找到');window.history.go(-1);</script>");
		}
	}

}
