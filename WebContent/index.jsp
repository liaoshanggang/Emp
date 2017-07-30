<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js" ></script>
		<script>
		function query(){
			$.post("EmpSearchAction",{"hah":"hahh"},function(data){
				//alert(data);
				//1、从servlet中获取的数据
				 //2、将数据以table的形式展现（动态生成表格）
				 //3、生成表头
				 //var obj = JSON.parse(data);
				 alert(data);
				/* strHeader='<table style="border-collapse:collapse;border:1px solid #999;text-align:center;">'+
				 '<tr>'+
				 '<th>员工编号</th><th>姓名</th><th>岗位</th><th>上级</th>'+
				 '<th>入职日期</th><th>工资</th><th>奖金</th><th>部门</th>'+
				 '<th>删除操作</th><th>更新操作</th></tr>';
				  //生成表格内容
				  strTR='';
				  $.each(data, function(index,emp) {
				  	strTR+='<tr>';
				  	strTD='';
				  	$.each(emp, function(i,e) {
				  		strTD+='<td style="border:1px solid #333;width:70px;">'+e+'</td>';
				  	});
				  	strTD += '<td style="border:1px solid #333;width:70px;"><button onclick="removeTD(this)">删除</button></td>';
				  	strTD += '<td style="border:1px solid #333;width:70px;"><button onclick="updateTD(this)">更新</button></td>';
				  	strTR+=strTD+'</tr>';
				  });
				  var _strHTML = strHeader+strTR+'</table>';
				  $(function(){
				  	$('#list').append(_strHTML);
				  });
				  function removeTD(nowTD){
				  	$(nowTD).parent().parent().remove();
				  }
				  function updateTD(nowTD){
				  	$(nowTD).parent().parent().remove();
				  } */
			})
		}
		</script>
	</head>
	<body>
		<div>
			<span>雇员编号:</span><input type="text" id="empNo" name="empNo" value="8001" />
			<span>雇员名:</span><input type="text" id="ename" name="ename" value="SMITH" />
			<span>部门编号:</span><input type="text" id="deptno" name="deptno" value="30" />
			<input type="submit" value="查询" id="submit" onclick="query()" />
		</div>
		<div id="list"></div>
	</body>
</html>