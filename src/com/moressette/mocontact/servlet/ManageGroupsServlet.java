package com.moressette.mocontact.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moressette.mocontact.dao.ContactDao;
import com.moressette.mocontact.dao.impl.ContactDaoimpl;
import com.moressette.mocontact.entity.Contact;

/**
 * Servlet implementation class ManageGroupsServlet
 */
@WebServlet("/ManageGroupsServlet")
public class ManageGroupsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageGroupsServlet() {
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
		
		if(request.getSession().getAttribute("username") == null){
			out.println("<script type='text/javascript'>alert('您还没有登录，请先登录');window.location.href='login.jsp'; </script>");
		}
		/**
		 * Show all contacts
		 */
		int uid = Integer.parseInt(request.getSession().getAttribute("uid").toString());
		//System.out.println("uid="+uid);
		ContactDao contactdao = new ContactDaoimpl();
//		List<Contact> contactlist = contactdao.findAllContacts(uid);
//		if (contactlist.isEmpty()){
//			out.print("<script>window.location.href=('hello.jsp');</script>");
//		}
//		request.getSession().setAttribute("contactlist", contactlist); //set contactlist to session
		/**
		 * find all groups
		 */
		Map<Integer,String> userGroups = new HashMap<Integer, String>();
		userGroups = contactdao.findUserGroups(uid);
		request.getSession().setAttribute("userGroups", userGroups);
		/**
		 * Set reverse map
		 */
		Map<String,Integer> userGroupsR = new HashMap<String,Integer>();
		userGroupsR = contactdao.findUserGroupsR(uid);
		request.getSession().setAttribute("userGroupsR", userGroupsR);
		
		out.print("<script>window.location.href=('groups.jsp');</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
