<%--
  Created by IntelliJ IDEA.
  User: 北林旺
  Date: 2019/12/8
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师</title>
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
<br>

<div class="col-2" style="float: left">
    <div class="text-left" style=background-color:lavender>教师姓名：</div>
    <div clas="text-left" style=background-color:lavender>工号：</div>
    <div clas="text-left" style=background-color:lavender>院系：</div>
    <div clas="text-left" style=background-color:lavender>入职时间：</div>
</div>


<div class="col-2" style="float: right">
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#demo">负责课程</button>
    <div id="demo" class="collapse">
        课程1<br>
        课程2
    </div>
    <br>
    <br>
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#chosen">申请列表</button>
    <div  id="chosen"  class="collapse">
        课程1<br>
        课程2
    </div>

</div>

</div>
</body>
</html>
