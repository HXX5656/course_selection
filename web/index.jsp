<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="http://malsup.github.io/jquery.form.js"></script>


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
  <button id="uploadFile">确定</button>
  <!-- servlet/DownLoadServlet -->
  上传的文件<a id="download" href=""></a>

  <a>bbbb</a>
  </body>
  <script type="text/javascript">
      $("#uploadFile").click(function test2() {
          alert("ok");
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
                  alert("t");
                  var result = eval(data);
                  var filePath = result[0].filePath;
                  //alert(filePath);
                  var fileName = result[0].imageName;
                  $("#download").attr("href", "servlet/DownLoadServlet?filePath=" + filePath);
                  document.getElementById("download").innerHTML = fileName;

                  //上传文件后得到路径，然后处理数据插入数据库表中
                  importExcel(filePath);
              }
          });
      });
      function importExcel(filePath){
          $.ajax({
              url:"/Servlet",
              type:"post",
              data:{"filePath":filePath,"type":"import"},
              success:function(data){
                  alert("okk");
              }
          });
      }


  </script>
</html>