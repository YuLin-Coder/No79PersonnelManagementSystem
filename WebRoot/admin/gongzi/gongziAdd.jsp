<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 

<%
    String path = request.getContextPath();
    java.util.Calendar   c   =   java.util.Calendar.getInstance();  
	c.add(c.MONTH,-1);//得到上个月的月份     
	java.util.Date   d   =   c.getTime();  
	String s= new SimpleDateFormat("yyyy-MM").format(d);  
   
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
        
        
		<link rel="stylesheet" href="<%=path %>/js/selectMonth.css" type="text/css"></link>
		<script type="text/javascript" src="<%=path %>/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/selectMonth.js"></script>
        
        <script type="text/javascript">
		    function yuangongSelect()
		    {
		         var strUrl = "<%=path %>/yuangong?type=yuangongSelect";
                 var ret = window.showModalDialog(strUrl,"","dialogWidth:960px; dialogHeight:300px; dialogLeft: status:no; directories:yes;scrollbars:yes;Resizable=no;");
                 
                 document.getElementById("yuangong_id").value=ret[0];
                 document.getElementById("xxx").value=ret[1];
		    }
		    
		    
		    function check()
		    {
		    
		        if(document.formAdd.yuangong_id.value=="")
		        {
		           alert("请选择员工");
		           return false;
		        }
		        if(document.formAdd.yuefen.value=="")
		        {
		           alert("请选择考勤月份");
		           return false;
		        }
		        document.formAdd.submit();
		    } 
		</script>
	</head>

	<body leftmargin="2" topmargin="9" background='<%=path %>/images/allbg.gif'>
			<form action="<%=path %>/gongzi?type=gongziAdd" name="formAdd" method="post">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#EEF4EA">
					        <td colspan="3" background="<%=path %>/images/wbg.gif" class='title'><span>&nbsp;</span></td>
					    </tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="12%" bgcolor="#FFFFFF" align="right">
						         员工选择：
						    </td>
						    <td width="88%" bgcolor="#FFFFFF" align="left">
						        <input type="text" name="xxx" id="xxx" style="width: 330px;" readonly="readonly"/>
						        <input type="hidden" name="yuangong_id" id="yuangong_id" size="60"/>
						        <input type="button" value="选择" onclick="yuangongSelect()"/>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="12%" bgcolor="#FFFFFF" align="right">
						        工资月份：
						    </td>
						    <td width="88%" bgcolor="#FFFFFF" align="left">
						        <input id="val2" name="yuefen" value="<%=s %>" onclick="showTime(this,'div2',null,'n');" style="width: 330px;" readonly="readonly"/>
						        <div id='div2' class="div1" style="position:absolute;"> </div>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="12%" bgcolor="#FFFFFF" align="right">
						            基本工资：
						    </td>
						    <td width="88%" bgcolor="#FFFFFF" align="left">
						       <input type="text" name="jiben" style="width: 330px;" value="2000" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/> 
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="12%" bgcolor="#FFFFFF" align="right">
						            业绩奖金：
						    </td>
						    <td width="88%" bgcolor="#FFFFFF" align="left">
						       <input type="text" name="jiangjin" style="width: 330px;" value="200" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/> 
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="12%" bgcolor="#FFFFFF" align="right">
						            扣除款项：
						    </td>
						    <td width="88%" bgcolor="#FFFFFF" align="left">
						       <input type="text" name="kouchu" style="width: 330px;" value="0" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/> 
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="12%" bgcolor="#FFFFFF" align="right">
						              备注信息：
						    </td>
						    <td width="88%" bgcolor="#FFFFFF" align="left">
						        <input type="text" name="beizhu" style="width: 330px;"/>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="12%" bgcolor="#FFFFFF" align="right">
						        &nbsp;
						    </td>
						    <td width="88%" bgcolor="#FFFFFF" align="left">
						       <input type="button" value="提交" onclick="check()"/>&nbsp; 
						       <input type="reset" value="重置"/>&nbsp;
						    </td>
						</tr>
					 </table>
			</form>
   </body>
</html>
