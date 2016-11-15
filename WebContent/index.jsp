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
    
    <title>MoContact-所有联系人</title>
  </head>
  
  
  <body>
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
        <li class="active"><a href="MainServlet" style="font-family:微软雅黑;font-weight:400;font-size:18px;">所有联系人</a></li>
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
        <!-- sex filter -->
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-family:微软雅黑;font-weight:400;font-size:18px;">性别筛选<b class="caret"></b></a>
          <ul class="dropdown-menu" style="width:100px;">
			<li><a href="allmale.jsp">男</a></li>
      		<li><a href="allfemale.jsp">女</a></li>
          </ul>
        </li>
        <!-- sex filter END -->
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
    
    <!-- contact list -->
    <table class="table table-striped table-hover">
    <th>姓名</th>
    <th>性别</th>
    <th>移动电话</th>
    <th>固定电话</th>
    <th>Email</th>
    <th>QQ</th>
    <th>所在单位</th>
    <th>联系地址</th>
    <th>群组</th>
    <th>操作</th>
    <c:forEach items="${contactlist}" var="contact">
      <tr>
        <td>${contact.cname }</td>
        <td>${contact.csex }</td>
        <td>${contact.cphone }</td>
        <td>${contact.ctel }</td>
        <td>${contact.cemail }</td>
        <td>${contact.cqq }</td>
        <td>${contact.cwork }</td>
        <td>${contact.caddress }</td>
        <td>${contact.gname }</td>
        <td>
          <a href="ModifyContactServlet?id=${contact.cid }">编辑</a>&nbsp;&nbsp;&nbsp;
          <a href="DeleteContactServlet?id=${contact.cid }" onclick="javascript:return del()">删除</a>
        </td>
      </tr>
    </c:forEach>
    </table>
    <!-- contact list END -->
    
    <!--Footer-->
    <div class="admin-footer">
        <div class="container">
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
    function del(){
	  if(confirm("确定删除吗")){
    	return true;
      }else{
    	return false;
      }
	}
	</script>
	
	<script type="text/javascript">
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
    <!-- Custom JS END -->
    
    <!-- Loading JS -->
    <script src="dist/js/vendor/jquery.min.js"></script>
    <script src="dist/js/flat-ui.min.js"></script>
    <script src="dist/js/application.js"></script>
    <!-- Loading JS -->
  </body>
</html>