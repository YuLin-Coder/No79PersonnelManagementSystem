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
		<script language="JavaScript" src="<%=path %>/js/public.js" type="text/javascript"></script>
		<title></title>
		<script type="text/javascript">
		    var org_id=null;
		    var leixing=null;
		    function onClickTreeNode(nodeId,org1)
		    {
		        org_id=nodeId;
		        leixing=leixing1;
		    }
		    
		    function org_ding_add()
		    {
				 var url="<%=path %>/admin/org/org_ding_add.jsp";
				 window.location.href=url;
		    }
		    
		    
		    function org_xia_add()
		    {
				if(org_id==null)
				{
				     alert("请点击选择上级部门");
				     return false;
				}
				if(leixing=="111")
				{
				     alert("请选择5555");
				     return false;
				}
				else
				{
				     var url="<%=path %>/admin/org/org_xia_add.jsp?p_org_id="+org_id;
				     window.location.href=url;
				}
		    }
		    
		    function orgDel()
		    {
				if(org_id==null)
				{
				     alert("请点击选择部门");
				}
				else
				{
				     if(confirm('您确定删除吗？谨慎操作啊'))
		             {
		                  var url="<%=path %>/org?type=orgDel&org_id="+org_id;
				          window.location.href=url;
		             }
				}
		    }
		    
		    function orgPre()
		    {
				if(org_id==null)
				{
				     alert("请点击选择部门");
				}
				else
				{
		                  var url="<%=path %>/org?type=orgPre&org_id="+org_id;
				          window.location.href=url;
				}
		    }
		</script>
	</head>

	<BODY>
	<div class="body-box">
		<div class="dtree">
			<script type="text/javascript">
				d = new dTree('d');
		        d.add(0,-1,'树形图');//必须有这句
				//d.add(1,0,'Node 1','example01.html');
				//d.add(2,0,'Node 2','example01.html');
				//d.add(7,0,'Node 4','#');
				<c:forEach items="${requestScope.orgList}" var="org" varStatus="ss">
		            d.add(${org.org_id},<c:if test="${org.p_org_id==''}">0</c:if><c:if test="${org.p_org_id !=''}">${org.p_org_id}</c:if>,'${org.org_mingcheng}',"javaScript:onClickTreeNode('${org.org_id}','${org.leixing}')","",null,null,null,'no');
		        </c:forEach>
				document.write(d);
			</script>
		</div>
		<br>
		&nbsp;&nbsp;
		<input type="button" class="ButtonCss" value="添加部门" onclick="org_ding_add()" style="width:100px;">
		<input type="button" class="ButtonCss" value="删除" onclick="orgDel()" style="width:100px;">
		
	</div>
	</body>
</html>
