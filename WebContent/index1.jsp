<%@page import="java.util.List"%>
<%@page import="org.lanqiao.paging.vo.EmpVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	List<EmpVo> listVO = (List<EmpVo>) request.getAttribute("listVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="dist/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>
				Welcome to the employee information query. <small></small>
			</h1>
		</div>
	</div>
	<div class="container">
		<form method="post" action="http://localhost:8080/paging/search.do"
			class="form-inline">
			<div class="form-group">
				<label class="control-label" for="empno">empno：</label> <input
					type="text" class="form-control input-lg" id="empno"
					placeholder="please input empno" />
			</div>
			<div class="form-group">
				<label class="control-label" for="deptno">deptno：</label> <input
					type="text" class="form-control input-lg" id="deptno"
					placeholder="please input deptno" />
			</div>
			<input type="submit" class="btn btn-default btn-lg" value="query">
		</form>
		<ul class="pagination pagination-lg">
			<li><a href="#">&laquo;</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">6</a></li>
			<li><a href="#">7</a></li>
			<li><a href="#">8</a></li>
			<li><a href="#">9</a></li>
			<li><a href="#">10</a></li>
			<li><a href="#">&raquo;</a></li>
		</ul>
		<table
			class="table  table-condensed table-bordered table-hover table-striped">
			<!--<caption>员工信息查询</caption>-->
			<thead>
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
			</thead>
			<tbody>
				<c:forEach items="${listVO }" var="emp">
					<tr>
						<td><c:out value="${emp.empno }" /></td>
						<td><c:out value="${emp.ename }" /></td>
						<td><c:out value="${emp.job }" /></td>
						<td><c:out value="${emp.mgr }" /></td>
						<td><c:out value="${emp.hiredate }" /></td>
						<td><c:out value="${emp.sal }" /></td>
						<td><c:out value="${emp.comm }" /></td>
						<td><c:out value="${emp.deptno }" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	
	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.min.js"></script>
</body>
</html>
