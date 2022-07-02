<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.study.jsp.dao.FileDAO" %>
    <%@ page import="com.study.jsp.dto.FileDto" %>
    <%@ page import="java.io.File" %>
    <%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
	<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	
	String directory = application.getRealPath("/fileFolder/");
	int maxSize = 1024* 1024 *100; //100M
	
	String encoding = "UTF-8";
	
	MultipartRequest multipartRequest = new MultipartRequest(request,directory, maxSize, encoding,
			 new DefaultFileRenamePolicy());
	
	String fileName = multipartRequest.getOriginalFileName("file");
	String fileRealName = multipartRequest.getFilesystemName("file");
	
	
	FileDAO dao = FileDAO.getInstance();
	dao.upload(fileName, fileRealName);
	out.write("파일명 : " + fileName +"<br>");
	out.write("실제 파일명 : " + fileRealName  +"<br>");
	%>  
	<script>
	 history.go(-1);
	</script>
</body>
</html>