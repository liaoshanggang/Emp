<%@page import="org.lanqiao.paging.vo.EmpVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
<script type="text/javascript">
//0、定义函数
    function check(){
    	//0,创建XMLHttpRequest对象
    	var xhr=new XMLHttpRequest();
    	//1、处理事件：onreadystatechange
    	xhr.onreadystatechange=function(){
    		if(xhr.readyState==4){
    			if(xhr.status==200){
    				
    				var $span=document.getElementById("msg");//dom
    				$span.innerHTML=xhr.responseText;
    				$span.style.fontSize="78px";
    				$span.style.color="red";
    			}
    			
    		}
    	}
    	var url="${pageContext.request.contextPath}/ajax.jsp";
    	var _deptno=document.getElementById("deptno").value;
    	var _beginSal=document.getElementById("beginSal").value;
    	var _endSal=document.getElementById("endSal").value;
    	var param="deptno="+_deptno+"&beginSal="+_beginSal+"&endSal="+_endSal;
    	url=url+"?"+param+"&t="+new Date().getTime();
    	
    	//2、建立连接：xmlHttp.open("GET","time.asp",true);
    	xhr.open("GET",url,true);
    	
    	//3、发送数据
    	xhr.send(null);

    	

    	
    }

</script>
<title>员工信息查询</title>
</head>
<body>
    <!--定位div-->
    <div id="container">
      <div id="search">
			<form>
			   请选择部门:
			   <select name="deptno" id="deptno">
			      <option value="0">-请选择-</option>			   
			      <option value="10">财务部</option>
			      <option value="20">研发部</option>
			      <option value="30">销售部</option>
			      <option value="40">运营部</option>
			   </select>
			   
			  请输入工资范围:
			   <input type="text" name="beginSal" id="beginSal"/>
			   ~
			   <input type="text" name="endSal" id="endSal"/>
			   
			   <input type="button" value="查询" onclick="check()">
			</form>
		
		</div>
		
		<!-- 显示数据的地方 -->
		<span id="msg"></span>
		
	</div>
</body>
</html>