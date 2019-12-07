<%-- Created by IntelliJ IDEA. --%>
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


  <form id="uploadForm" action="" method="post" enctype="multipart/form-data">
    <table>
      <tr>
        <td>上传文件：</td>
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
  </div>
  <button id="uploadFile">自动导入</button>
  </body>
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="http://malsup.github.io/jquery.form.js"></script>
  <script type="text/javascript">
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