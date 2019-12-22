<%--
  Created by IntelliJ IDEA.
  User: huxiaoxiao
  Date: 2019/12/19
  Time: 下午6:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员导入界面</title>
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
<div class="col-12">
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#import">一键导入</button>
    <div id = "import" class="collapse">
<form id="uploadForm" action="" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>管理员界面</td>
            <td>
                <input type="file" name="fileName" id="fileName"/>
            </td>
        </tr>
    </table>
</form>
<div class="container">
    <label class="radio-inline"><input type="radio" name="optradio" value="student" checked>学生</label>
    <label class="radio-inline"><input type="radio" name="optradio" value="teacher">教师</label>
    <label class="radio-inline"><input type="radio" name="optradio" value="course">课程</label>
    <label class="radio-inline"><input type="radio" name="optradio" value="grade">成绩</label>
    <label class="radio-inline"><input type="radio" name="optradio" value="department">院系</label>
    <label class="radio-inline"><input type="radio" name="optradio" value="classroom">教室</label>
</div>
<button id="uploadFile">自动导入</button>
</div>
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#student">查看学生信息</button>
    <div id = "student" class="collapse">

    </div>
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#teacher">查看教师信息</button>
    <div id = "teacher" class="collapse">

    </div>
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#course">查看课程信息</button>
    <div id = "course" class="collapse">

    </div>
    <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#status">改变系统状态</button>
    <div id = "status" class="collapse">
        <div id="status_box"><input type='text' id='canSelect' placeholder="1代表可以选课，0反之"><input type="text" id="canDelete" placeholder="1代表可以删课，0反之">
            <input type="text" id="semester" placeholder="1为春季学期，2为秋季学期"><input type="text" id="year" placeholder="学年">
            <button type="button" id="change" onclick="submit()">提交</button>
    </div>
</div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="http://malsup.github.io/jquery.form.js"></script>
<script type="text/javascript">
    function submit() {
        var canSelect = $("#canSelect").val();
        var canDelete = $("#canDelete").val();
        var semester = $("#semester").val();
        var year = $("#year").val();
        $.ajax({
            url:"/Servlet",
            type:"post",
            async:false,
            data:{"type":"status","select":canSelect,"delete":canDelete,"semester":semester,"year":year},
            dataType:"json",
            success:function(data) {
                if(data == "0") {
                    alert("状态设置成功");
                } else {
                    alert("状态设置失败");
                }
            }
        });

    }
    if(sessionStorage.getItem("role") !== "admin.jsp") {
        $(location).attr("href",sessionStorage.getItem("role"));
    } else {
        $.ajax({
            url:"/Servlet",
            type:"post",
            async:false,
            data:{"type":"a_teachers"},
            dataType:"json",
            success:function(data) {
                getTeacherInfo(data);
            }
        });
        $.ajax({
            url:"/Servlet",
            type:"post",
            async:false,
            data:{"type":"a_students"},
            dataType:"json",
            success:function(data) {
                getStudentInfo(data);
            }
        });
        $("#s_sub").click(function () {
            var student_id = $("#s_id").val();
            var student_name = $("#s_name").val();
            var s_en = $("#s_en").val();
            var s_le = $("#s_le").val();
            var s_dep = $("#s_dep").val();
            $.ajax({
                url:"/Servlet",
                type:"post",
                async:false,
                data:{"type":"add_student","student_id":student_id,"student_name":student_name,
                "enter_time":s_en,"gradu_time":s_le,"department":s_dep},
                dataType:"json",
                success:function(data) {
                    alert(data);
                    if (data == "0") {
                        alert("您的操作已成功");
                    }
                    else {
                        alert("您的操作失败了");
                    }
                    $(location).attr("href",sessionStorage.getItem("role"));
                }
            });
        });
        $("#t_sub").click(function () {
            var student_id = $("#t_id").val();
            var student_name = $("#t_name").val();
            var s_en = $("#t_en").val();
            var s_le = $("#t_le").val();
            var s_dep = $("#t_dep").val();
            $.ajax({
                url:"/Servlet",
                type:"post",
                async:false,
                data:{"type":"add_teacher","teacher_id":student_id,"teacher_name":student_name,
                    "enter_time":s_en,"leave_time":s_le,"department":s_dep},
                dataType:"json",
                success:function(data) {
                    alert(data);
                    if (data == "0") {
                        alert("您的操作已成功");
                    }
                    else {
                        alert("您的操作失败了");
                    }
                    $(location).attr("href",sessionStorage.getItem("role"));
                }
            });
        });
        function sub() {
            // alert("方法调用饿了");
                var course_code = $("#course_code").val();
                var course_name = $("#course_name").val();
                var credit = $("#credit").val();
                var teacher_id = $("#teacher_id").val();
                var time_place = $("#time-place").val();
                var hours = $("#hours").val();
                var max = $("#max").val();
                var exam_time = $("#exam_time").val();
                var exam_type = $("#exam_type").val();
                $.ajax({
                    url:"/Servlet",
                    type:"post",
                    async:false,
                    data:{"type":"add_course","course_code":course_code,"course_name":course_name,"credit":credit,"teacher_id":teacher_id,
                    "time-place":time_place,"hours":hours,"max":max,"exam_time":exam_time,"exam_type":exam_type},
                    dataType:"text",
                    success:function(data) {
                        if(data == "0") {
                            alert("操作成功");
                        } else {
                            alert("操作失败");
                        }
                    }
                });
        }

        $.ajax({
            url:"/Servlet",
            type:"post",
            async:false,
            data:{"type":"a_courses"},
            dataType:"json",
            success:function(data) {
                getCourseLists(data);
            }
        });
    }
    function getCourseLists(data) {
        // alert(JSON.stringify(data));
        $("#course").html("");
        var json = data;
        var len = Object.keys(json).length;
        // var radios = "";
        var html = "<tr><th>课程代码</th><th>课程名称</th><th>课程学分</th><th>课时安排</th><th>上课学期</th><th>选课人数</th></tr>";
        for (var i = 0;i<len;i++) {
            var item = json[i+""];
            var tmp = "";
            // cr_list[""+i]=item["semester"]+"/"+item["year"]+" "+item["course_code"];
            // radios += "<label class='radio-inline'><input type='radio' name='optradio' value='"+item["semester"]+"/"+item["year"]+" "+item["course_code"]+"' "
            //     + (i === 0?"checked":"")+">"+item["semester"]+"/"+item["year"]+" "+item["course_code"]+"</label>";
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
        // alert(html);
        html+="<div id='course_box'><label>增加课程：</label><input type='text' id='course_code' placeholder='course_code'>" +
            "<input type='text' id='course_name' placeholder='course_name'><input type='text' id='credit' placeholder='credit'>" +
            "<input type='text' id='teacher_id' placeholder='teacher_id'>" + "<input type='text' id='time-place' placeholder='time-place'>" +
            "<input type='text' id='hours' placeholder='hours'><input type='text' id='max' placeholder='max'>" +
            "<input type='text' id='exam_time' placeholder='exam_time'><input type='text' id='exam_type' placeholder='exam_type'><button type='button' id='course_submit' onclick='sub()'>提交</button></div>";
        $("#course").append(html);
        // $("#radio_box").append(radios);
        // show_student_list();
    }
    function getTeacherInfo(data) {
        $("#teacher").html("");
        var len = Object.keys(data).length;
        var html = "<tr><th>工号</th><th>教师姓名</th><th>入职时间</th><th>离职时间</th><th>院系编号</th></tr>";
        for (var i = 0;i<len;i++) {
            var item = data[i+""];
            html+="<tr><td>"+item["teacher_id"]+"</td><td>"+item["teacher_name"]+"</td><td>"+item["enter_time"]
                +"</td><td>"+item["leave_time"]+"</td><td>"+item["department"]+"</td></tr>";
        }
        html="<table border=\"1px\" cellspacing=\"0px\" style=\"border-collapse:collapse\">"+html+"</table>";
        html+="<div id='teacher_box'><label>增加或修改教师信息：</label><input type='text' id='t_id'>" +
            "<input type='text' id='t_name'><input type='text' id='t_en'><input type='text' id='t_le'>" +
            "<input type='text' id='t_dep'><button type='button' id='t_sub'>提交</button></div>";
        $("#teacher").append(html);
    }
    function getStudentInfo(data) {
        $("#student").html("");
        var len = Object.keys(data).length;
        var html = "<tr><th>学号</th><th>学生姓名</th><th>入学时间</th><th>毕业时间</th><th>院系编号</th></tr>";
        for (var i = 0;i<len;i++) {
            var item = data[i+""];
            html+="<tr><td>"+item["student_id"]+"</td><td>"+item["student_name"]+"</td><td>"+item["enter_time"]
                +"</td><td>"+item["gradu_time"]+"</td><td>"+item["department"]+"</td></tr>";
        }
        html="<table border=\"1px\" cellspacing=\"0px\" style=\"border-collapse:collapse\">"+html+"</table>";
        html+="<div id='student_box'><label>增加或修改学生信息：</label><input type='text' id='s_id'>" +
            "<input type='text' id='s_name'><input type='text' id='s_en'><input type='text' id='s_le'>" +
            "<input type='text' id='s_dep'><button type='button' id='s_sub'>提交</button></div>";
        $("#student").append(html);
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
        var name=$("input:radio:checked").val();
        $.ajax({
            url:"/Servlet",
            type:"post",
            data:{"filePath":filePath,"type":"import","name":name},
            success:function(data){
                alert("okk");
            }
        });
    }


</script>
</html>
