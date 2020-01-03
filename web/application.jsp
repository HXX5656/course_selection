<%--
  Created by IntelliJ IDEA.
  User: huxiaoxiao
  Date: 2019/12/22
  Time: 上午8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>申请界面</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="#">选课系统</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#">教师</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">学生</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">管理员</a>
            </li>
        </ul>
    </div>
</nav>
<div class="col-2"></div>
<div class="col-8" style="float: left">
    <div class="text-left" id="id" style=background-color:lavender>学号：</div>
    <div class="text-left" id="course" style=background-color:lavender>申请课程：</div>
    <div class="text-left" id="reason" style=background-color:lavender><label for="simplify">申请理由：</label>
        <input type="text" id="simplify" /></div>
    <div class="text-left" id="bth" style=background-color:lavender><button id="sub" type="button">提交申请</button></div>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="http://malsup.github.io/jquery.form.js"></script>
<script type="text/javascript">

    if(sessionStorage.getItem("course_code") === null) {
        $(location).attr("href",sessionStorage.getItem("role"));
    } else {
        var course_code=sessionStorage.getItem("course_code");
        var student_id = sessionStorage.getItem("userID");
        $("#id").html($("#id").html()+student_id);
        $("#course").html($("#course").html()+course_code);
        var semester=sessionStorage.getItem("semester");
        var year=sessionStorage.getItem("year");
        $("#sub").click(function () {
            var reason=$("#simplify").val();
            var date = new Date();
            var date_day = date .toLocaleDateString(); //获取当前日期
            alert(date_day);
            $.ajax(
                {
                    url:"/Servlet",
                    type:"post",
                    async:false,
                    data:{"type":"apply","student_id":student_id, "year":year,"semester":semester
                        ,"course_code":course_code,"reason":reason,"date":date_day},
                    dataType:"text",
                    success:function(data) {
                        if(data === "1")
                            alert("您的申请成功提交");
                        else if (data === "0")
                            alert("申请失败哦，已退课程是不能选课事务申请的哦");
                        else
                            alert("您已申请过，不要重复申请");
                        $(location).attr("href",sessionStorage.getItem("role"));
                    }
                }
            );
        });
    }
</script>
</body>
</html>
