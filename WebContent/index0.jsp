<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.lanqiao.paging.vo.EmpVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <%
List<EmpVo> listVO = new ArrayList<EmpVo>();
for(int i=0;i<14;i++){	
	EmpVo e1 = new EmpVo("张三丰"+i,"道士"+i,"张翠山"+i,"1400年12月"+(i+1)+"日","8888."+i,"无","10","20");
	listVO.add(e1);
}
%> --%>
<%
List<EmpVo> listVO = (List<EmpVo>)request.getAttribute("listVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工信息查询</title>
</head>
<body>
	<form method="post" action="http://localhost:8080/paging/search.do">
		<input type="text"/>
		<input type="submit" value="查询">
	</form>
	<table border="1">
		<tr>
			<th>EMPNO</th>
			<th>ENAME</th>
			<th>JOB</th>
			<th>MGR</th>
			<th>HIREDATE</th>
			<th>SAL</th>
			<th>COMM</th>
			<th>DEPTNO</th>
		</tr>
		<%
		for(EmpVo ee1:listVO){
		%>
		<tr>
			<td><%=ee1.getEmpno() %></td>
			<td><%=ee1.getEname() %></td>
			<td><%=ee1.getJob() %></td>
			<td><%=ee1.getMgr() %></td>
			<td><%=ee1.getHiredate() %></td>
			<td><%=ee1.getSal() %></td>
			<td><%=ee1.getComm() %></td>
			<td><%=ee1.getDeptno() %></td>
		</tr>
		<%
		}%>
	</table>

</body>
</html>