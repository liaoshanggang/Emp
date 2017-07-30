<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" href="dist/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>雇员信息CRUD</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function query() {
	//先获得table下的thead再获得下一个同胞再添加
	var empno = $("#empno").val();
	var deptno = $("#deptno").val();
	//alert(empno+"hello"+deptno);
	$.post("EmpServlet?m=queryEmp",{
		"empno":empno,
		"deptno":deptno
	},function(data){
		var empObj = JSON.parse(data);
		var empsList=empObj.emps;
		//alert(empsList);
		strTR='';
		$.each(empsList, function(index,emp) {
		  	strTR+='<tr>';
		  	strTD='';
		  	strTD+='<td>'+emp.empno+'</td>';
		  	strTD+='<td>'+emp.ename+'</td>';
		  	strTD+='<td>'+emp.job+'</td>';
		  	strTD+='<td>'+emp.mgr+'</td>';
		  	strTD+='<td>'+emp.hiredate+'</td>';
		  	strTD+='<td>'+emp.sal+'</td>';
		  	strTD+='<td>'+emp.comm+'</td>';
		  	strTD+='<td>'+emp.deptno+'</td>';
		  	strTD += '<td><button onclick="removeTD(this)">删除</button></td>';
		  	strTD += '<td><button onclick="updateTD(this)">更新</button></td>';
		  	strTR+=strTD+'</tr>';
		  });
		//alert(strTR);
		$("#Tb_Emp").children().next().append(strTR);
	});
}
function removeTD(nowTr) {
	var empno = $(nowTr).parent().parent().children().first().text();
	$.post("EmpServlet?m=deleteEmp",{
		"empno":empno
	},function(data){
		var status = JSON.parse(data);
		var delStatus = status.delStatus;
		if(delStatus==="success"){
	 		$(nowTr).parent().parent().remove();
	 		alert("删除成功！");
		}else{
			alert("删除失败！");
		}
	});
}
function updateTD(nowTr){
	
}
</script>
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
		<form onsubmit="javascript:return false" class="form-inline">
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
		<input type="submit" class="btn btn-default btn-lg" value="query"
			onclick="query()">
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
		<table id="Tb_Emp"
			class="table  table-condensed table-bordered table-hover table-striped">
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
					<th>删除操作</th>
					<th>更新操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>

	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.min.js"></script>
</body>
</html>