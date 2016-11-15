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
 * Servlet implementation class ModifyContactServlet
 */
@WebServlet("/ModifyContactServlet")
public class ModifyContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyContactServlet() {
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
		
		int cid = Integer.parseInt(request.getParameter("id"));
		Contact contact = new Contact();
		ContactDao contactdao = new ContactDaoimpl();
		contact = contactdao.findContactByCid(cid);
		request.getSession().setAttribute("contact", contact);
		response.sendRedirect("modifycontact.jsp");
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
		Contact con = (Contact)request.getSession().getAttribute("contact");
		int cid = con.getCid();
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
		contact.setCid(cid);
		ContactDao contactdao = new ContactDaoimpl();
		boolean flag = contactdao.modifyContact(contact);
		if(flag == true){
			out.println("<script>window.location.href=('MainServlet');</script>");
		}
		if(flag == false){
			out.println("<script>alert('联系人修改失败');window.location.href=('javascript:history.back()');</script>");
		}
	}

}
