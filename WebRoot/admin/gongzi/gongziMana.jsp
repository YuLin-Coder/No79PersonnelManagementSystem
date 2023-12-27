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
		
		<script language="JavaScript" src="<%=path %>/js/popup_shuaxin.js" type="text/javascript"></script>
		
        <script language="javascript">
           function gongziDel(id)
           {
                var url="<%=path %>/gongzi?type=gongziDel&id="+id;
                window.location.href=url;
           }
           
           function gongziAdd()
           {
              var url="<%=path %>/admin/gongzi/gongziAdd.jsp";
              window.location.href=url;
           }
        </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="14" background="<%=path %>/img/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="4%">序号</td>
					<td width="10%">员工姓名</td>
					<td width="10%">工资月份</td>
					<td width="10%">基本工资</td>
					
					<td width="10%">业绩奖金</td>
					<td width="10%">扣除款项</td>
					<td width="10%">备注信息</td>
					<td width="10%">实发工资</td>
					
					<td width="10%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.gongziList}" var="gongzi" varStatus="ss">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						 ${ss.index+1}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${gongzi.yuangong.xingming}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${gongzi.yuefen}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${gongzi.jiben}
					</td>
					
					<td bgcolor="#FFFFFF" align="center">
						 ${gongzi.jiangjin}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${gongzi.kouchu}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${gongzi.beizhu}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${gongzi.zong}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<input type="button" value="删除" onclick="gongziDel(${gongzi.id})"/>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 11px;">
			  <tr>
			    <td>
			      <input type="button" value="添加工资信息" style="width: 123px;" onclick="gongziAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
