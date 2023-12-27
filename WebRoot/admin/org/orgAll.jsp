<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=path %>/css/dtree.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" src="<%=path %>/js/dtree.js" type="text/javascript"></script>
		<title></title>
		
		
		<script type="text/javascript">
		    function onClickTreeNode(org_id,org_mingcheng,leixing)
		    {
		         /* if(leixing=="111")
		         {
		             alert("1111");
		         }
		         else
		         { */
		             var str=new Array(2);
	                 str[0]=org_id;
	                 str[1]=org_mingcheng;
	           		 window.returnValue = str;
	           		 window.close();
		         /*  } */
		    }
		</script>
	</head>

	<BODY>
		<div class="dtree">
			<script type="text/javascript">
				d = new dTree('d');
		        d.add(0,-1,'树形图');//必须有这句
				//d.add(1,0,'Node 1','example01.html');
				//d.add(2,0,'Node 2','example01.html');
				//d.add(7,0,'Node 4','#');
				<c:forEach items="${requestScope.orgList}" var="org" varStatus="ss">
		            d.add(${org.org_id},<c:if test="${org.p_org_id==''}">0</c:if><c:if test="${org.p_org_id !=''}">${org.p_org_id}</c:if>,'${org.org_mingcheng}',"javaScript:onClickTreeNode('${org.org_id}','${org.org_mingcheng}','${org.leixing}');",'',null,null,null,'yes');
		        </c:forEach>
				document.write(d);
			</script>
		</div>
	</body>
</html>
