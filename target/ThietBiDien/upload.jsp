<%--
  Created by IntelliJ IDEA.
  User: Chris
  Date: 8/7/2020
  Time: 7:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload file</title>
</head>
<body>
    <h1>Demo Servlet Upload File</h1>
    <form method="post" action="upload-file/" enctype="multipart/form-data">
        <label>Select file to upload</label><br>
        <input type="file" name="File" multiple="multiple"/> <br/>
        <input type="submit" value="upload">
    </form>

</body>
</html>
