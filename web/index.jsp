<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录界面</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- 新 Bootstrap4 核心 CSS 文件 -->

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>

    <!-- bootstrap.bundle.min.js 用于弹窗、提示、下拉菜单，包含了 popper.min.js -->
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>

    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>

  </head>
  <body>
  <form style="margin-left: 500px;margin-top: 200px">
    <div class="form-group">
      <label for="username">账户</label>
      <input type="text" id="username" />
    </div>
    <div class="form-group">
      <label for="password">密码</label>
      <input type="password" id="password" />
    </div>
    <button id="btn" type="button">登录</button>
  </form>




  </body>
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="http://malsup.github.io/jquery.form.js"></script>
  <script type="text/javascript">
    // 使用ajax的jquery传数据，url、type、dataType都与这个相同，data里是传进去的json数据，type是servlet用来判断的类型
    if(sessionStorage.getItem("userID") === "" || sessionStorage.getItem("userID") === null){
        $("#btn").html("登录");
        $("#btn").click(function login() {
            var usrname = $("#username").val();
            var pwd = $("#password").val();
            $.ajax({
                url:"/Servlet",
                type:"post",
                async:false,
                data:{"account":usrname,"password":pwd, "type":"login"},
                dataType:"json",
                success:function(data) {
                    // var filePath = result[0].filePath;
                    var status = data["result"];
                    alert(status);
                    var success = data["success"];
                    if (success) {
                        var role = "admin.jsp";
                        if(usrname.substr(0,1) === "S") {
                            usrname=usrname.substr(1);
                            alert(usrname);
                            role = "student.jsp";
                        }
                        if (usrname.substr(0,1) === "T") {
                            usrname=usrname.substr(1);
                            alert(usrname);
                            role = "teacher.jsp";
                        }
                        sessionStorage.setItem("userID",usrname);
                        sessionStorage.setItem("role",""+role);

                        $(location).attr("href",role);
                    }
                }
            });
        });
    } else {
        $("#btn").html("修改密码");
        $("#btn").click(function () {
            var usrname = $("#username").val();
            var pwd = $("#password").val();
            $.ajax({
                url:"/Servlet",
                type:"post",
                async:false,
                data:{"account":usrname,"password":pwd, "type":"modify"},
                dataType:"json",
                success:function(data) {
                    // var filePath = result[0].filePath;
                    var status = data["result"];
                    alert(status);
                    var success = data["success"];
                    if (success) {
                        // var role = "admin.jsp";
                        // if(usrname.substr(0,1) === "S") {
                        //     usrname=usrname.substr(1);
                        //     alert(usrname);
                        //     role = "student.jsp";
                        // }
                        // if (usrname.substr(0,1) === "T") {
                        //     usrname=usrname.substr(1);
                        //     alert(usrname);
                        //     role = "teacher.jsp";
                        // }
                        // sessionStorage.setItem("userID",usrname);
                        // sessionStorage.setItem("role",""+role);

                        $(location).attr("href",sessionStorage.getItem("role"));
                    }
                }
            });
        });
    }

  </script>

</html>