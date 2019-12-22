<%--
  Created by IntelliJ IDEA.
  User: huxiaoxiao
  Date: 2019/12/22
  Time: 下午10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登出</title>
</head>
<body>

</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="http://malsup.github.io/jquery.form.js"></script>
<script type="text/javascript">
    sessionStorage.setItem("userID","");
    sessionStorage.setItem("role","index.jsp");
    $(location).attr("href",sessionStorage.getItem("role"));
</script>
</html>
