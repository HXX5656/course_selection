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
    <div class="text-left" id="name" style=background-color:lavender>教师姓名：</div>
    <div class="text-left" id="id"  style=background-color:lavender>工号：</div>
    <div class="text-left" id="dep" style=background-color:lavender>院系：</div>
    <div class="text-left" id="time"  style=background-color:lavender>入职时间：</div>
</div>


<div class="col-8">
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#demo">负责课程</button>
    <div id="demo" class="collapse">
    </div>
    <br>
    <br>
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#chosen">申请列表</button>
    <div  id="chosen"  class="collapse">
    </div>
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#look">查看课程花名册</button>
    <div  id="look"  class="collapse">
        <div class="container" id="radio_box">
        </div>
        <div class="container" id="stu_table">
        </div>
    </div>
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#import">一键登分</button>
    <div id = "import" class="collapse">
        <form id="uploadForm" action="" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>界面</td>
                    <td>
                        <input type="file" name="fileName" id="fileName"/>
                    </td>
                </tr>
            </table>
        </form>
        <div class="container">
            <input type="text" placeholder="course_code:" id="codes">
        </div>
        <button id="uploadFile">自动导入</button>
    </div>


</div>

</div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="http://malsup.github.io/jquery.form.js"></script>
<script type="text/javascript">
    var cr_list ={"type":"applications"};
    if(sessionStorage.getItem("role") !== "teacher.jsp") {
        $(location).attr("href",sessionStorage.getItem("role"));
    } else {
        var id = sessionStorage.getItem("userID");
        $.ajax({
            url:"/Servlet",
            type:"post",
            async:false,
            data:{"type":"teacher_info","teacher_id":id},
            dataType:"json",
            success:function(data) {
                getTeacherInfo(data);
            }
        });
        $.ajax({
            url:"/Servlet",
            type:"post",
            async:false,
            data:{"type":"teacher_courses","teacher_id":id},
            dataType:"json",
            success:function(data) {
                getCourseLists(data);
                $.ajax({
                    url:"/Servlet",
                    type:"post",
                    async:false,
                    data:cr_list,
                    dataType:"json",
                    success:function(data) {
                        getApplicationLists(data);
                    }
                });
            }
        });
        $(".access").click(function () {
            var app_id = $(this).val();
            $.ajax({
                url:"/Servlet",
                type:"post",
                async:false,
                data:{"type":"confirm_app","app_id":app_id},
                dataType:"text",
                success:function(data) {
                    if(data === "1") {
                        alert("您的操作已成功");
                    } else if(data === "-2"){
                        alert("您的操作失败了,当前不在选课时间段内");
                    } else {
                        alert("您的操作失败");
                    }
                    $(location).attr("href",sessionStorage.getItem("role"));
            } }
            );
        });
        $(".refuse").click(function () {
            var app_id = $(this).val();
            $.ajax({
                url:"/Servlet",
                type:"post",
                async:false,
                data:{"type":"refuse_app","app_id":app_id},
                dataType:"text",
                success:function(data) {
                    if(data === "1") {
                        alert("您的操作已成功");
                    } else if(data === "-2"){
                        alert("您的操作失败了,当前不在选课时间段内");
                    } else {
                        alert("您的操作失败");
                    }
                    $(location).attr("href",sessionStorage.getItem("role"));
                } }
            );
        });
    }
    function getApplicationLists(data) {
        var html = "<tr><th>申请码</th><th>学生学号</th><th>申请课程</th><th>上课学期</th><th>申请理由</th><th>申请状态</th><th>同意</th><th>驳回</th></tr>";
        for (var key in data) {
            var tmp = "";
            var canBeDone = false;
            tmp+="<td>"+key+"</td><td>"+data[key]["student_id"]+"</td><td>"+data[key]["course_code"]
                +"</td><td>"+data[key]["year"]+"/"+data[key]["semester"]+"</td><td>"+data[key]["reason"]+"</td>";
            if(data[key]["status"] === "0") {
                canBeDone = true;
                tmp+="<td>未处理</td>";
            } else if(data[key]["status"] === "1") {
                tmp+="<td>申请已通过</td>";
            } else {
                tmp+="<td>申请已被驳回</td>";
            }
            tmp+="<td><button type='button' class='access' value='"+key+"' "+(canBeDone?"":"disabled")+">同意</button></td>";
            tmp+="<td><button type='button' class='refuse' value='"+key+"' "+(canBeDone?"":"disabled")+">驳回</button></td>";
            tmp = "<tr>"+tmp+"</tr>";
            html+=tmp;
        }
        html="<table border=\"1px\" cellspacing=\"0px\" style=\"border-collapse:collapse\">"+html+"</table>";
        $("#chosen").append(html);
    }
    function getTeacherInfo(data) {
        var teacher_id = data["teacher_id"];
        var teacher_name = data["teacher_name"];
        var enter_time = data["enter_time"];
        var dep = data["department"];
        $("#id").html($("#id").html()+teacher_id);
        $("#name").html($("#name").html()+teacher_name);
        $("#time").html($("#time").html()+enter_time);
        $("#dep").html($("#dep").html()+dep);
    }
    function getCourseLists(data) {
        alert(JSON.stringify(data));
        var json = data;
        var len = Object.keys(json).length;
        var radios = "";
        var html = "<tr><th>课程代码</th><th>课程名称</th><th>课程学分</th><th>课时安排</th><th>上课学期</th><th>选课人数</th></tr>";
        for (var i = 0;i<len;i++) {
            var item = json[i+""];
            var tmp = "";
            cr_list[""+i]=item["semester"]+"/"+item["year"]+" "+item["course_code"];
            radios += "<label class='radio-inline'><input type='radio' name='optradio' value='"+item["semester"]+"/"+item["year"]+" "+item["course_code"]+"' "
                + (i === 0?"checked":"")+">"+item["semester"]+"/"+item["year"]+" "+item["course_code"]+"</label>";
            tmp += "<td>"+item["course_code"]+"</td><td>"+item["course_name"]+"</td><td>"+item["course_credit"]+"</td><td>";
            var arr = item["time_place"];
            var arr_len = Object.keys(arr).length;
            for(var j = 0;j<arr_len;j++) {
                var jtem = arr[j+""];
                tmp += "周"+jtem["day"]+"第"+jtem["step"]+"节 H"+jtem["room"]+";<br>";
            }
            tmp+="</td><td>"+item["year"]+"/"+item["semester"]+"</td><td>"+item["selected_persons"]+"/"+item["max_members"]+"</td>";
            tmp = "<tr>"+tmp+"</tr>";
            html+=tmp;
        }
        html="<table border=\"1px\" cellspacing=\"0px\" style=\"border-collapse:collapse\">"+html+"</table>";
        // alert("son/mother:"+total_grade_son+"/"+total_grades_mother);
        alert(html);
        $("#demo").append(html);
        $("#radio_box").append(radios);
        show_student_list();
    }
    $("input[type=radio][name=optradio]").change(show_student_list());
    function show_student_list() {
        var value_course = $("input:radio:checked").val();
        var semester = value_course.substr(0,1);
        var year = value_course.substr(2,4);
        var course_code = value_course.substr(7);
        alert("展示的时候："+course_code);
        $.ajax({
            url:"/Servlet",
            type:"post",
            async:false,
            data:{"type":"student_list","course_code":course_code,"semester":semester,"year":year},
            dataType:"json",
            success:function(data) {
                getStudentLists(data);
            }
        });
    }
    function getStudentLists(data) {
        $("#stu_table").html("");
        var len = Object.keys(data).length;
        var html = "<tr><th>学号</th><th>学生姓名</th></tr>";
        for (var i = 0;i<len;i++) {
            var item = data[i+""];
            html+="<tr><td>"+item["student_id"]+"</td><td>"+item["student_name"]+"</td></tr>"
        }
        html="<table border=\"1px\" cellspacing=\"0px\" style=\"border-collapse:collapse\">"+html+"</table>";
        $("#stu_table").append(html);
    }
    $("#uploadFile").click(function test2() {
        var form = new FormData(document.getElementById("uploadForm"));
        $.ajax({
            contentType:"multipart/form-data",
            url:"/UploadHandleServlet",
            type:"post",
            async:false,
            data:form,
            dataType:"json",
            processData: false,  // 告诉jQuery不要去处理发送的数据
            contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
            success:function(data) {
                var result = eval(data);
                var filePath = result[0].filePath;
                //alert(filePath);
                //var fileName = result[0].imageName;
                //$("#download").attr("href", "servlet/DownLoadServlet?filePath=" + filePath);
                //document.getElementById("download").innerHTML = fileName;

                //上传文件后得到路径，然后处理数据插入数据库表中
                importExcel(filePath);
            }
        });
    });
    function importExcel(filePath){
        var name="grade";
        var c_code = $("#codes").val();
        var teacher_id = sessionStorage.getItem("userID");
        $.ajax({
            url:"/Servlet",
            type:"post",
            data:{"filePath":filePath,"type":"import","name":name,"course_code":c_code,"teacher_id":teacher_id},
            success:function(data){
                if(data == "-2")
                    alert("数据冲突，插入失败");
                else  if (data=="-1")
                    alert("操作失败");
                else
                    alert("操作成功");
            }
        });
    }
</script>
</html>
