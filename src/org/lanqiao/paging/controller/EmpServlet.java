package org.lanqiao.paging.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.lanqiao.paging.service.EmpService;
import org.lanqiao.paging.service.impl.EmpServiceImpl;
import org.lanqiao.paging.vo.EmpVo;

import com.alibaba.fastjson.JSONObject;

public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1、获得请求参数
		String m = request.getParameter("m");
		String returnPage = "/WEB-INF/view/EmpIndex.jsp";
		
		//2、分发控制处理，把数据渲染到页面
		if ("login".equals(m)) {
			returnPage = loginCtrl(request, response);
			System.out.println("login");
		} else if ("register".equals(m)) {
			returnPage = registerCtrl(request, response);
			System.out.println("register");
		}else if ("addEmp".equals(m)) {
			returnPage = addEmp(request, response);
			System.out.println("add");
		} else if ("deleteEmp".equals(m)) {
			returnPage = deleteEmp(request, response);
			System.out.println("delete");
			return ;
		} else if ("updateEmp".equals(m)) {
			returnPage = updateEmp(request, response);
			System.out.println("update");
		} else if ("queryEmp".equals(m)) {
			returnPage = query(request, response);
			System.out.println("query");
			return;
		}
		
		//3、把数据渲染到页面后，请求转发页面
		request.getRequestDispatcher(returnPage).forward(request, response);
	}
	
	private String query(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		//1、获取前台字符串数据
		//雇员号，雇员名，部门号，部门名称来查询
		String empno = request.getParameter("empno");
		//String ename = request.getParameter("ename");
		String deptno = request.getParameter("deptno");
		EmpVo eVo = new EmpVo();
		eVo.setEmpno(empno);
		//eVo.setEname(ename);
		eVo.setDeptno(deptno);
		
		//2、使用后台addEmp服务
		//业务有n个，
		//（1）根据部门编号号查询该部门信息
		//（2）根据雇员编号查询部门信息
		//（3）根据雇员名查询用户信息
		//（4）根据部门名查询该部门信息
		//（5）仅仅显示数据库里面的信息
		EmpService es = new EmpServiceImpl();
		List<EmpVo> evos = new ArrayList<EmpVo>();
		if (!StringUtils.isEmpty(empno)) {// 1、只填了empNO和同时填了empNO和deptNo的情况
			//（2）根据雇员编号查询部门信息
		} else if (StringUtils.isEmpty(empno) && !StringUtils.isEmpty(deptno)) {// 2、只填了deptNO的情况
			//（4）根据部门名查询该部门信息
		} else {//什么都不填的情况
			//（5）仅仅显示数据库里面的信息
			String strPageSize = request.getParameter("pageSize");
			String strCurrentPage = request.getParameter("currentPage");
			//默认显示1页10条
			int pageSize = 14;
			int currentPage = 1;
			if(strCurrentPage!=null){
				pageSize = Integer.parseInt(strPageSize);
			}
			if(strCurrentPage!=null){			
				currentPage = Integer.parseInt(strCurrentPage);
			}
			evos = es.query(pageSize, currentPage);
		}

		//{"emps":[{"comm":"无","deptno":"RESEARCH","empno":"7369","ename":"SMITH","hiredate":"1980-12-17","job":"SALESMAN","mgr":"FORD","sal":"$5250.0"},{"comm":"$300.0","deptno":"SALES","empno":"7499","ename":"ALLEN","hiredate":"1981-02-20","job":"SALESMAN","mgr":"BLAKE","sal":"$1600.0"},{"comm":"$500.0","deptno":"SALES","empno":"7521","ename":"WARD","hiredate":"1981-02-22","job":"SALESMAN","mgr":"BLAKE","sal":"$1250.0"},{"comm":"无","deptno":"RESEARCH","empno":"7566","ename":"JONES","hiredate":"1981-04-02","job":"MANAGER","mgr":"KING","sal":"$2975.0"},{"comm":"$1400.0","deptno":"SALES","empno":"7654","ename":"MARTIN","hiredate":"1981-09-28","job":"SALESMAN","mgr":"BLAKE","sal":"$1250.0"},{"comm":"无","deptno":"SALES","empno":"7698","ename":"BLAKE","hiredate":"1981-05-01","job":"MANAGER","mgr":"KING","sal":"$2850.01"},{"comm":"无","deptno":"ACCOUNTING","empno":"7782","ename":"CLARK","hiredate":"1981-06-09","job":"MANAGER","mgr":"KING","sal":"$2450.0"},{"comm":"无","deptno":"RESEARCH","empno":"7788","ename":"SCOTT","hiredate":"1987-04-19","job":"ANALYST","mgr":"JONES","sal":"$3000.0"},{"comm":"无","deptno":"ACCOUNTING","empno":"7839","ename":"KING","hiredate":"1981-11-17","job":"PRESIDENT","sal":"$5000.0"},{"comm":"无","deptno":"SALES","empno":"7844","ename":"TURNER","hiredate":"1981-09-08","job":"SALESMAN","mgr":"BLAKE","sal":"$1500.01"}]}
		//3、渲染页面
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("emps", evos);
		try {
			PrintWriter out = response.getWriter();
			System.out.println(jsonObj.toString());
			out.println(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/WEB-INF/view/EmpIndex.jsp";
	}
	
	private String updateEmp(HttpServletRequest request,
			HttpServletResponse response) {
		//1、获取前台字符串数据
		String empno = request.getParameter("empno");
		String ename = request.getParameter("ename");
		String job = request.getParameter("job");
		String mgr = request.getParameter("mgr");
		String hireDate = request.getParameter("hireDate");;
		String sal = request.getParameter("sal");
		String comm = request.getParameter("comm");
		String deptno = request.getParameter("deptno");
		EmpVo eVo = new EmpVo(empno,ename, job, mgr, hireDate, sal, comm, deptno);
		System.out.println(eVo);
		
		//2、使用后台addEmp服务
		EmpService es = new EmpServiceImpl();
		String updateStatus = es.updateEmp(eVo);
		
		//3、渲染页面
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("updateStatus", updateStatus);
		try {
			PrintWriter out = response.getWriter();
			System.out.println(jsonObj.toString());
			out.println(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/WEB-INF/view/EmpIndex.jsp";
	}

	private String deleteEmp(HttpServletRequest request,
			HttpServletResponse response) {
		//1、获取前台字符串数据
		String empno = request.getParameter("empno");
		String ename = request.getParameter("ename");
		String job = request.getParameter("job");
		EmpVo eVo = new EmpVo();
		eVo.setEmpno(empno);
		eVo.setEname(ename);
		eVo.setJob(job);
		System.out.println(eVo);
		
		//2、使用后台addEmp服务
		EmpService es = new EmpServiceImpl();
		String delStatus = es.deleteEmpById(empno);
		
		//3、渲染页面
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("delStatus", delStatus);
		try {
			PrintWriter out = response.getWriter();
			System.out.println(jsonObj.toString());
			out.println(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/WEB-INF/view/EmpIndex.jsp";
	
	}

	private String addEmp(HttpServletRequest request,
			HttpServletResponse response) {
		//1、获取前台字符串数据
		String ename = request.getParameter("ename");
		String job = request.getParameter("job");
		String mgr = request.getParameter("mgr");
		String hireDate = request.getParameter("hireDate");;
		String sal = request.getParameter("sal");
		String comm = request.getParameter("comm");
		String deptno = request.getParameter("deptno");
		EmpVo eVo = new EmpVo(ename, job, mgr, hireDate, sal, comm, deptno);
		System.out.println(eVo);
		
		//2、使用后台addEmp服务
		EmpService es = new EmpServiceImpl();
		String addStatus = es.addEmp(eVo);
		
		//3、渲染页面
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("emps", addStatus);
		try {
			PrintWriter out = response.getWriter();
			System.out.println(jsonObj.toString());
			out.println(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/WEB-INF/view/EmpIndex.jsp";
	}

	private String registerCtrl(HttpServletRequest request,
			HttpServletResponse response) {
		//说白了就是插入一条管理员信息数据
		return null;
	}

	private String loginCtrl(HttpServletRequest request,
			HttpServletResponse response) {
		//说白了就是在服务层查询有每有用户信息，匹配页面传来的数据
		return "/WEB-INF/view/EmpIndex.jsp";
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
