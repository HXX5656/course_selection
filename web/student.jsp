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
    <title>学生</title>
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
                <a class="nav-link" href="logout.jsp">登出</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="index.jsp">修改密码</a>
            </li>
        </ul>
    </div>
</nav>
<br>

<div class="col-2" style="float: left">
    <div class="text-left" id="name" style=background-color:lavender>学生姓名：</div>
    <div class="text-left" id="id" style=background-color:lavender>学号：</div>
    <div class="text-left" id="gpa" style=background-color:lavender>GPA：</div>
    <div class="text-left" id="credits" style=background-color:lavender>总学分：</div>
</div>


<div class="col-8">
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#demo">课程列表</button>
    <div id="demo" class="collapse">
    </div>
    <br>
    <br>
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#chosen">已选课程</button>
    <div  id="chosen"  class="collapse">
    </div>
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#app">我的申请</button>
    <div  id="app"  class="collapse">
    </div>
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#exam">我的考试</button>
    <div  id="exam"  class="collapse">
    </div>

</div>

</div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="http://malsup.github.io/jquery.form.js"></script>
<script type="text/javascript">
    if(sessionStorage.getItem("role") !== "student.jsp") {
        $(location).attr("href",sessionStorage.getItem("role"));
    } else {
        var id = sessionStorage.getItem("userID");
        $.ajax({
            url:"/Servlet",
            type:"post",
            async:false,
            data:{"type":"student_info","student_id":id, "year":"2019","semester":"1"},
            dataType:"json",
            success:function(data) {
                getCourseTable(data);
            }
        });
        $.ajax({
            url:"/Servlet",
            type:"post",
            async:false,
            data:{"type":"courseList","student_id":sessionStorage.getItem("userID"), "year":"2019","semester":"1"},
            dataType:"json",
            success:function(data) {
                getCourseList(data);
            }
        });
        $.ajax({
            url:"/Servlet",
            type:"post",
            async:false,
            data:{"type":"student_applications","student_id":sessionStorage.getItem("userID")},
            dataType:"json",
            success:function(data) {
                getApplicationLists(data);
            }
        });
        $.ajax({
            url:"/Servlet",
            type:"post",
            async:false,
            data:{"type":"my_exam","student_id":sessionStorage.getItem("userID")},
            dataType:"json",
            success:function(data) {
                getExamLists(data);
            }
        });

    }
    function getExamLists(data) {
        var len = Object.keys(data).length;
        var html = "<tr><th>考试码</th><th>考试类型</th><th>详情</th></tr>";
        for (var i=0;i<len;i++) {
            var tmp1 = "";
            var tmp = data[i+""];
            tmp1+="<td>"+tmp["exam_id"]+"</td><td>"+tmp["type"]
                +"</td><td>"+tmp["value"]+"</td>";
            // tmp+="<td><button type='button' class='access' value='"+key+"' "+(canBeDone?"":"disabled")+">同意</button></td>";
            // tmp+="<td><button type='button' class='refuse' value='"+key+"' "+(canBeDone?"":"disabled")+">驳回</button></td>";
            tmp1 = "<tr>"+tmp1+"</tr>";
            html+=tmp1;
        }
        html="<table border=\"1px\" cellspacing=\"0px\" style=\"border-collapse:collapse\">"+html+"</table>";
        $("#exam").append(html);
    }
    function getCourseTable(data) {
        $("#id").html($("#id").html()+sessionStorage.getItem("userID"));
        var json = data;
        var len = Object.keys(json).length;
        var credit = 0;
        var total_grades_mother = 0;
        var total_grade_son = 0;
        $("#name").html($("#name").html()+data["student_name"]);
        var html = "<tr><th>课程代码</th><th>课程名称</th><th>课程学分</th><th>课时安排</th><th>上课学期</th><th>选课人数</th><th>成绩</th></tr>";
        for (var i = 0;i<len - 1;i++) {
            var item = json[i+""];
            var tmp = "";
            tmp += "<td>"+item["course_code"]+"</td><td>"+item["course_name"]+"</td><td>"+item["course_credit"]+"</td><td>";
            var arr = item["time_place"];
            var arr_len = Object.keys(arr).length;
            for(var j = 0;j<arr_len;j++) {
                var jtem = arr[j+""];
                tmp += "周"+jtem["day"]+"第"+jtem["step"]+"节 H"+jtem["room"]+";<br>";
            }
            tmp+="</td><td>"+item["year"]+"/"+item["semester"]+"</td><td>"+item["selected_persons"]+"/"+item["max_members"]+"</td>";
            tmp+="<td>"+item["grade"]+"</td>";
            if(item["grade"] !== null)
            {
                total_grade_son+=parseInt(item["grade"])*parseFloat(item["course_credit"]);
                total_grades_mother+=parseInt(item["course_credit"]);
            }
            credit += parseInt(item["course_credit"]);
            tmp = "<tr>"+tmp+"</tr>";
            html+=tmp;
        }
        html="<table border=\"1px\" cellspacing=\"0px\" style=\"border-collapse:collapse\">"+html+"</table>";
        // alert("son/mother:"+total_grade_son+"/"+total_grades_mother);
        $("#gpa").html($("#gpa").html()+(total_grades_mother===0?"0":(total_grade_son/total_grades_mother)+""));
        $("#credits").html($("#credits").html()+credit);
        $("#chosen").append(html);
    }
    function getCourseList(data) {
        //var course_list = JSON.parse(data.replace(/\r\n/g, "<br>"));
        var json = data;
        var len = Object.keys(json).length;
        var html = "<tr><th>课程代码</th><th>课程名称</th><th>课程学分</th><th>课时安排</th><th>选课人数</th><th>操作</th></tr>";
        for (var i = 0;i<len;i++) {
            var item = json[i+""];
            var tmp = "";
            tmp += "<td>"+item["course_code"]+"</td><td>"+item["course_name"]+"</td><td>"+item["course_credit"]+"</td><td>";
            var arr = item["time_place"];
            var arr_len = Object.keys(arr).length;
            for(var j = 0;j<arr_len;j++) {
                var jtem = arr[j+""];
                tmp += "周"+jtem["day"]+"第"+jtem["step"]+"节 H"+jtem["room"]+";<br>";
            }
            tmp+="</td><td>"+item["selected_persons"]+"/"+item["max_members"]+"</td>";
            tmp+="<td><button type='button' class='btn_submit' id='"+item["course_code"]+"'";
            if(!(item["status"]==="提交选课申请"||item["status"]==="选课"||item["status"]==="退课")) {
                tmp+="disabled='true'"
            }
            tmp += ">"+item["status"]+"</button></td>";
            tmp = "<tr>"+tmp+"</tr>";
            html+=tmp;
        }
        html="<table border=\"1px\" cellspacing=\"0px\" style=\"border-collapse:collapse\">"+html+"</table>";
        $("#demo").append(html);

    }
    $(".btn_submit").click(function operate(){
        var code=$(this).attr("id");
        var type=$(this).html()==="提交选课申请"?"apply":"select_drop";
        var course_id = code.substr(2,6);
        var section_id = code.substr(9,2);
        if(type === "apply") {
            sessionStorage.setItem("course_code",code);
            sessionStorage.setItem("semester","1");
            sessionStorage.setItem("year","2019");
            $(location).attr("href","application.jsp");
        } else {
        $.ajax({
            url:"/Servlet",
            type:"post",
            async:false,
            data:{"type":type,"student_id":sessionStorage.getItem("userID"),
                "year":"2019","semester":"1","course_id":course_id,"section_id":section_id},
            dataType:"text",
            success:function(data) {
                alert(data);
                if(data === "1") {
                    alert("您的操作已成功");
                } else if(data === "-2"){
                    alert("您的选退课操作失败了,当前不在选课时间段内");
                } else {
                    alert("您的操作失败");
                }
                $(location).attr("href",sessionStorage.getItem("role"));
            }
        });}
    });
    function getApplicationLists(data) {
        var html = "<tr><th>申请码</th><th>申请课程</th><th>上课学期</th><th>申请理由</th><th>申请状态</th></tr>";
        for (var key in data) {
            var tmp = "";
            var canBeDone = false;
            tmp+="<td>"+key+"</td><td>"+data[key]["course_code"]
                +"</td><td>"+data[key]["year"]+"/"+data[key]["semester"]+"</td><td>"+data[key]["reason"]+"</td>";
            if(data[key]["status"] === "0") {
                canBeDone = true;
                tmp+="<td>未处理</td>";
            } else if(data[key]["status"] === "1") {
                tmp+="<td>申请已通过</td>";
            } else {
                tmp+="<td>申请已被驳回</td>";
            }
            // tmp+="<td><button type='button' class='access' value='"+key+"' "+(canBeDone?"":"disabled")+">同意</button></td>";
            // tmp+="<td><button type='button' class='refuse' value='"+key+"' "+(canBeDone?"":"disabled")+">驳回</button></td>";
            tmp = "<tr>"+tmp+"</tr>";
            html+=tmp;
        }
        html="<table border=\"1px\" cellspacing=\"0px\" style=\"border-collapse:collapse\">"+html+"</table>";
        $("#app").append(html);
    }
</script>
</html>
