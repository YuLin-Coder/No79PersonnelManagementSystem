<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		
        <script language="javascript">
           function chuqinDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   var url="<%=path %>/chuqin?type=chuqinDel&id="+id;
                   
                   window.location.href=url;
               }
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="18" background="<%=path %>/images/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
				    <td width="4%">序号</td>
					<td width="10%">员工</td>
					<td width="10%">日期</td>
					<td width="10%">签到时间</td>
					
					<td width="10%">签到属性</td>
					<td width="10%">签退时间</td>
					<td width="10%">签退属性</td>
					<!-- <td width="10%">操作</td> -->
		        </tr>	
				<c:forEach items="${requestScope.chuqinList}" var="chuqin" varStatus="ss">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${ss.index+1}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${chuqin.yuangong.xingming}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${chuqin.riqi}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${chuqin.qiandaoshi}
					</td>
					
					
					<td bgcolor="#FFFFFF" align="center">
						${chuqin.qiandaoshuxing}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${chuqin.qiantuishi}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${chuqin.qiantuishuxing}
					</td>
					<%-- <td bgcolor="#FFFFFF" align="center">
						<input type="button" onclick="chuqinDel(${chuqin.id})" value="删除"/>
					</td> --%>
				</tr>
				</c:forEach>
			</table>
			
	</body>
</html>
