<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setContentType("text/html;charset=utf-8");
response.setCharacterEncoding("UTF-8");
request.setCharacterEncoding("UTF-8"); 
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
	
    <title>MoContact-用户注册</title>
  </head>
  
  
  <body onLoad="createCode();">
    <!-- HEADER -->
    <div class="header header-color">
        <div class="container">
            <center><font style="color:#ffffff;font-size:50px;font-family:微软雅黑">MoContact 个人通讯录</font></center>
        </div>
    </div>
    <!-- HEADER END -->

    <!--Register Form-->
    <div class="container">
      <div class="row">
        <div class="login-control">
          <form name="registerform" action="RegisterServlet" method="post" role="form" onsubmit="return validate(this);">
            <div class="login-form">
              <!--Username-->
              <div class="form-group">
                <input type="text" class="form-control login-field" placeholder="用户名(不能为空)" name="username" id="username" />
                <label class="login-field-icon fui-user" for="username"></label>
              </div>
              <!--Username END-->

              <!--Password-->
              <div class="form-group">
                <input type="password" class="form-control login-field" placeholder="密码(长度在8~12之间)" name="password" id="password" />
                <label class="login-field-icon fui-lock" for="password"></label>
              </div>
              <!--Password END-->
              
              <!--Confirm pwd-->
              <div class="form-group">
                <input type="password" class="form-control login-field" placeholder="确认密码(必须与密码一致)" name="confirm_pwd" id="confirm_pwd" />
                <label class="login-field-icon fui-lock" for="confirm_pwd"></label>
              </div>
              <!--Confirm pwd END-->
              
              <!--Realname-->
              <div class="form-group">
                <input type="text" class="form-control login-field" placeholder="真实姓名(不能为空)" name="realname" id="realname" />
                <label class="login-field-icon fui-new" for="realname"></label>
              </div>
              <!--Realname END-->
              
              <!-- Captcha -->
              <div class="form-group">
                <input type="text" class="form-control login-field" placeholder="验证码" name="inputCode"  id="inputCode" />
                <div class="code" id="checkCode" onclick="createCode()" ></div>
                <a class="login-link" href="#" onclick="createCode()" style="margin-top: 20px;">看不清换一张</a>
              </div>
              <!-- Captcha END -->
              <!-- Submit button -->
              <button type="submit" class="btn btn-primary btn-lg btn-block">注册</button>
              <!-- Login button END -->
              <div class="form-group">
              <a class="login-link" href="login.jsp">已经有账号?点此登录</a></div>
            </div>
          </form>
        </div>
      </div>
    </div>
	<!-- Register form END -->
	
    <!--Footer-->
    <div class="footer">
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
    <!-- Create captcha -->  
    <script type="text/javascript">
    var code;
    function createCode() 
    {
      code = "";
      var codeLength = 6; //验证码的长度
      var checkCode = document.getElementById("checkCode");
      var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); 
      for(var i = 0; i < codeLength; i++) 
      {
        var charNum = Math.floor(Math.random() * 52);
        code += codeChars[charNum];
      }
      if(checkCode) 
      {
        checkCode.className = "code";
        checkCode.innerHTML = code;
      }
    }  
    </script>
    <!-- Create captcha END -->  
    
    <!-- Validate Form -->
    <script type="text/javascript">
    function validate(registerform) {
      if (registerform.username.value == "") {
        document.getElementById("myModalTitle").innerHTML = "注册失败";
        document.getElementById("myModalContainer").innerHTML = "用户名不能为空";
        $("#myModal").modal();
        return false;
      }
      var a = registerform.username.value;
      var b = /^[0-9a-zA-Z]*$/g;
      if ( (b.test(a)) == false ) {
          document.getElementById("myModalTitle").innerHTML = "注册失败";
          document.getElementById("myModalContainer").innerHTML = "用户名只能包含数字和字母";
          $("#myModal").modal();
          return false;
       }
      if (registerform.password.value == "") {
        document.getElementById("myModalTitle").innerHTML = "注册失败";
        document.getElementById("myModalContainer").innerHTML = "密码不能为空";
        $("#myModal").modal();
        return false;
      }
      if(registerform.password.value.length<8 || registerform.password.value.length>20){
        document.getElementById("myModalTitle").innerHTML = "注册失败";
        document.getElementById("myModalContainer").innerHTML = "密码必须在8-20位之间";
        $("#myModal").modal();
        return false;
      }
      if (registerform.password.value != registerform.confirm_pwd.value ){
        document.getElementById("myModalTitle").innerHTML = "注册失败";
        document.getElementById("myModalContainer").innerHTML = "两次密码输入不一致";
        $("#myModal").modal();
        return false;
      }
      if (registerform.realname.value == "") {
        document.getElementById("myModalTitle").innerHTML = "注册失败";
        document.getElementById("myModalContainer").innerHTML = "真实姓名不能为空";
        $("#myModal").modal();
        return false;
      }
      var inputCode=document.getElementById("inputCode").value;
      if(inputCode.length <= 0) 
      {
        document.getElementById("myModalTitle").innerHTML = "注册失败";
        document.getElementById("myModalContainer").innerHTML = "验证码不能为空";
        $("#myModal").modal();
        return false;
      }
      if(inputCode.toUpperCase() != code.toUpperCase()) 
      {
        document.getElementById("myModalTitle").innerHTML = "注册失败";
        document.getElementById("myModalContainer").innerHTML = "验证码输入错误";
        $("#myModal").modal();
        createCode();
        return false;
      } 
      return true;
    }  
	</script>
    <!-- Validate Form -->
    
    <!-- Custom JS END -->
    
    
    <!-- Loading JS -->
    <script src="dist/js/vendor/jquery.min.js"></script>
    <script src="dist/js/flat-ui.min.js"></script>
    <script src="dist/js/application.js"></script>
    <!-- Loading JS -->
  </body>
</html>