<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<html>

	<head>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
		<title>&nbsp;</title>
	</head>

	<frameset rows="140,*,60" framespacing=0 frameborder="no">

		<frame name="banner" scrolling="no" noresize src="<%=path %>/ayuangong/top.jsp">

		<frameset cols="168,*" framespacing=0 frameborder="no"
			id="mainframeChange">
			<frame name="contents" scrolling="no" noresize src="<%=path %>/ayuangong/left.jsp">
			<frame name="main" src="<%=path %>/ayuangong/right.jsp">
		</frameset>
		<frame name="banner1" scrolling="no" noresize src="<%=path %>/ayuangong/footer.jsp">
		<noframes>
			<body LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH="0" MARGINHEIGHT="0">
			</body>
		</noframes>
	</frameset>
</html>