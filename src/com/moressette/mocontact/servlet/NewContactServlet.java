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
import com.moressette.mocontact.entity.Contact;

/**
 * Servlet implementation class NewContactServlet
 */
@WebServlet("/NewContactServlet")
public class NewContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewContactServlet() {
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
		/*Get Form info*/
		String cname = request.getParameter("cname");
		String getCsex = request.getParameter("csex");
		String csex;
		if(getCsex.equals("male")){
			csex = "男";
		}else{
			csex = "女";
		}
		int gid = Integer.parseInt(request.getParameter("gid"));
		String cphone = request.getParameter("cphone");
		String ctel = request.getParameter("ctel");
		String cemail = request.getParameter("cemail");
		String cqq = request.getParameter("cqq");
		String cwork = request.getParameter("cwork");
		String caddress = request.getParameter("caddress");
		int uid = Integer.parseInt(request.getSession().getAttribute("uid").toString());
		Contact contact = new Contact();
		contact.setCname(cname);
		contact.setCsex(csex);
		contact.setCphone(cphone);
		contact.setCtel(ctel);
		contact.setCemail(cemail);
		contact.setCqq(cqq);
		contact.setCwork(cwork);
		contact.setCaddress(caddress);
		contact.setGid(gid);
		contact.setUid(uid);
		ContactDao contactdao = new ContactDaoimpl();
		boolean flag = contactdao.newContact(contact);
		if(flag == true){
			out.println("<script>window.location.href=('MainServlet');</script>");
		}
		if(flag == false){
			request.getSession().setAttribute("result", "新建联系人失败");
			request.getSession().setAttribute("resultImg", "img/failed.png");
			request.getSession().setAttribute("resultAddr", "newcontact.jsp");
			out.println("<script>window.location.href=('result.jsp');</script>");
		}
	}

}
