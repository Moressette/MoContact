<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
response.setContentType("text/html;charset=utf-8");
response.setCharacterEncoding("UTF-8");
request.setCharacterEncoding("UTF-8"); 
if((String)session.getAttribute("username") == null){    
	out.println("<script type='text/javascript'>alert('您还没有登录，请先登录');window.location.href='login.jsp'; </script>");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=1000, initial-scale=1.0, maximum-scale=1.0">
    
    <!-- Loading Bootstrap -->
    <link href="dist/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom JS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- Loading Flat UI -->
    <link href="dist/css/flat-ui.css" rel="stylesheet">

	<!-- Web shortcut -->
	<link rel="shortcut icon" href="img/favicon.ico"/>
	<link rel="Bookmark" href="img/favicon.ico" />
    
    <title>MoContact</title>
  </head>
  
  
  <body style="overflow-x:hidden">
    <!-- HEADER -->
    <div class="header header-color">
        <div class="container">
            <center><font style="color:#ffffff;font-size:50px;font-family:微软雅黑">MoContact 个人通讯录</font></center>
        </div>
    </div>
    <!-- HEADER END -->

	<!-- Navbar -->
    <div class="collapse navbar-default navbar-collapse" id="navbar-collapse-5">
      <ul class="nav navbar-nav">
        <li><a href="MainServlet" style="font-family:微软雅黑;font-weight:400;font-size:18px;">所有联系人</a></li>
        <li><a href="newcontact.jsp" style="font-family:微软雅黑;font-weight:400;font-size:18px;">新建联系人</a></li>
        <!-- Groups Button -->
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-family:微软雅黑;font-weight:400;font-size:18px;">查看群组<b class="caret"></b></a>
          <ul class="dropdown-menu" style="width:100px;">
			<li><a href="ManageGroupsServlet">群组管理</a></li>
            <li class="divider"></li>
            <c:forEach var="item" items="${userGroups}"> 
                <li><a href="ShowSingleGroupServlet?gid=${item.key}">${item.value}</a></li>
			</c:forEach>
          </ul>
        </li>
        <!-- Groups Button END -->
      </ul>
      <form class="navbar-form navbar-left" action="SearchContactServlet" method="post" role="search" id="searchform" onsubmit="return validate(this);">
        <div class="form-group">
          <div class="input-group">
            <input class="form-control" id="search" name="search" type="search" placeholder="联系人搜索">
            <span class="input-group-btn">
              <button type="submit" class="btn">
                <span class="fui-search"></span>
              </button>
            </span>
          </div>
        </div>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-family:微软雅黑;font-weight:400;font-size:18px;"><%= session.getAttribute("realname")%> <b class="caret"></b></a>
          <ul class="dropdown-menu" style="width:100px;">
            <li><a href="modifyuser.jsp">账号设置</a></li>
            <li class="divider"></li>
            <li><a href="LogoutServlet">用户注销</a></li>
          </ul>
        </li>
      </ul>
    </div>
    <!-- Navbar END -->
    
  <!-- ModifyContact form -->
    <div class="container">
        <div class="row">
        <div class="col-md-12" style="margin-top:80px">
            <font style="font-family:微软雅黑;font-size:30px;font-weight:500;"><center>编辑联系人</center></font>
          <form class="form-horizontal modifyuser-control" role="form" id="modifycontactForm" name="modifycontactForm" action="ModifyContactServlet" method="post" onsubmit="return validateform(this);">
           
            <div class="form-group">
              <label for="cname" class="col-lg-3 control-label">姓名</label>
              <div class="col-lg-7">
                <input type="text" class="form-control" id="cname" name="cname" value="${contact.cname}">
              </div>
            </div>
			
			<div class="form-group">
           	 <label for="csex" class="col-lg-3  control-label">性别</label>
             <div class="col-sm-1">
                <label class="radio" style="color:#2f4154;font-family:微软雅黑;font-size:14px;font-weight:500;margin-top:12px">
                  <input type="radio" name="csex" id="csex" value="male" class="custom-radio" <c:if test="${contact.csex== '男'}">checked="checked"</c:if>>
                  <span class="icons">
                    <span class="icon-unchecked"></span>
                    <span class="icon-checked"></span>
                  </span>男
                </label>
              </div>
              <div class="col-sm-1  col-lg-offset-2">
                <label class="radio" style="color:#2f4154;font-family:微软雅黑;font-size:14px;font-weight:500;margin-top:12px">
                  <input type="radio" name="csex" id="csex" value="female" class="custom-radio" <c:if test="${contact.csex== '女'}">checked="checked"</c:if>>
                  <span class="icons">
                    <span class="icon-unchecked"></span>
              	    <span class="icon-checked"></span>
            	  </span>女
                </label>
              </div>
            </div>
           
            <div class="form-group">
	          <label for="gid" class="col-lg-3 control-label">群组</label>
	          <div class="col-lg-7">
	            <select data-toggle="select" class="form-control select select-default " tabindex="-1" title="" name="gid" id="gid">
	              <c:forEach var="item" items="${userGroups}"> 
	                <option value="${item.key}" <c:if test="${contact.gid== item.key}">selected</c:if>>${item.value}</option>
				  </c:forEach>
	        	</select>
	          </div>
	      </div>    
            
            <div class="form-group">
              <label for="cphone" class="col-lg-3 control-label">移动电话</label>
              <div class="col-lg-7">
                <input type="text" class="form-control" id="cphone" name="cphone" value="${contact.cphone}">
              </div>
            </div>
            
            <div class="form-group">
              <label for="ctel" class="col-lg-3 control-label">固定电话</label>
              <div class="col-lg-7">
                <input type="text" class="form-control" id="ctel" name="ctel" value="${contact.ctel}">
              </div>
            </div>
            
            <div class="form-group">
              <label for="cemail" class="col-lg-3 control-label">电子邮箱</label>
              <div class="col-lg-7">
                <input type="text" class="form-control" id="cemail" name="cemail" value="${contact.cemail}">
              </div>
            </div>
            
            <div class="form-group">
              <label for="cqq" class="col-lg-3 control-label">QQ</label>
              <div class="col-lg-7">
                <input type="text" class="form-control" id="cqq" name="cqq" value="${contact.cqq}">
              </div>
            </div>
            
            <div class="form-group">
              <label for="cwork" class="col-lg-3 control-label">所在单位</label>
              <div class="col-lg-7">
                <input type="text" class="form-control" id="cwork" name="cwork" value="${contact.cwork}">
              </div>
            </div>
            
            <div class="form-group">
              <label for="caddress" class="col-lg-3 control-label">联系地址</label>
              <div class="col-lg-7">
                <input type="text" class="form-control" id="caddress" name="caddress" value="${contact.caddress}">
              </div>
            </div>
            

            <div class="form-group">
              <div class="col-lg-offset-1">
                <button type="submit" class="btn btn-primary">确认修改</button>
                <button type="reset" class="btn btn-primary" style="margin-left:90px">还原修改</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!-- ModifyUser form END -->
    
    
    <!--Footer-->
    <div class="row">
    <div class="admin-footer">
           <center>
            <p>
                Powered by <a href="http://v3.bootcss.com/" target="_Blank">Bootstrap</a> | Designed by <a href="http://moressette.com" target="_Blank">Moressette</a> | <a href="http://blog.csdn.net/wangcong9614/" target="_Blank">blog</a> & <a href="http://github.com/Moressette" target="_Blank">Github</a> <br>
                Special thanks to <a href="http://glyphicons.com/">glyphicons</a> | Promote <a href="https://developer.mozilla.org/zh-CN/" target="_Blank">MDN</a> | <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/deed.zh_TW" target="_Blank">Creative Commons</a>
            </p>
            </center>

    </div>
      </div>
    <!--Footer END-->
    
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalTitle" style="font-family:微软雅黑"></h4>
          </div>
          <div class="modal-body" style="font-family:微软雅黑">
          	<span id="myModalContainer"></span>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal END -->
    
    <!-- Custom JS -->
	<script type="text/javascript">
    <%String title = "用户名错误";%>
    <%String container = "用户名不能为空";%>
	function validate(searchform) {
		if (searchform.search.value == "") {
			document.getElementById("myModalTitle").innerHTML = "检索失败";
			document.getElementById("myModalContainer").innerHTML = "请输入要检索的内容";
               $("#myModal").modal();
			return false;
		}
		return true;
	}
	</script>
	
	<script type="text/javascript">
	function validateform(modifycontactForm) {
		if (modifycontactForm.cname.value == "") {
			document.getElementById("myModalTitle").innerHTML = "修改失败";
			document.getElementById("myModalContainer").innerHTML = "请输入联系人姓名";
               $("#myModal").modal();
			return false;
		}
		if((!document.getElementsByName("csex")[0].checked)&&(!document.getElementsByName("csex")[1].checked)){
        	document.getElementById("myModalTitle").innerHTML = "修改失败";
			document.getElementById("myModalContainer").innerHTML = "请选择联系人性别";
			$("#myModal").modal();
            return false;
        }
		
		if(isNaN(modifycontactForm.cphone.value)) {
        	document.getElementById("myModalTitle").innerHTML = "修改失败";
			document.getElementById("myModalContainer").innerHTML = "移动电话只能包含数字";
			$("#myModal").modal();
			return false;
        }
		
		if(document.getElementById("gid").value=="none") {
        	document.getElementById("myModalTitle").innerHTML = "修改失败";
			document.getElementById("myModalContainer").innerHTML = "请选择联系人群组";
			$("#myModal").modal();
			return false;
        }
		

		return true;
	}
	</script>
    <!-- Custom JS END -->
    
    <!-- Loading JS -->
    <script src="dist/js/vendor/jquery.min.js"></script>
    <script src="dist/js/flat-ui.min.js"></script>
    <script src="dist/js/application.js"></script>
    <!-- Loading JS -->
  </body>
</html>