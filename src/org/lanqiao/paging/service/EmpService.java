package org.lanqiao.paging.service;

import java.util.ArrayList;
import java.util.List;

import org.lanqiao.paging.dao.DeptDao;
import org.lanqiao.paging.dao.EmpDao;
import org.lanqiao.paging.dao.impl.DeptDaoImpl;
import org.lanqiao.paging.dao.impl.EmpDaoImpl;
import org.lanqiao.paging.entity.Dept;
import org.lanqiao.paging.entity.Emp;
import org.lanqiao.paging.vo.EmpVo;

public class EmpService {
	private EmpDao edao;
	private DeptDao ddao;
	private List<Emp> emps;// 缓存
	private List<Dept> depts;

	public EmpService() {
		edao = new EmpDaoImpl();
		emps = edao.select();
		
		//-----------一下代码是为了获得员工所在部门-2017年7月26日---------------
		ddao = new DeptDaoImpl();
		depts = ddao.select();
	}

	public List<EmpVo> query(int pageSize, int currentPage) {
		List<Emp> eList = edao.select_page(pageSize, currentPage);
		List<EmpVo> evoList = new ArrayList<EmpVo>();
		for (Emp emp : eList) {
			EmpVo evo = new EmpVo();
			evo.setEmpno(emp.getEmpno() + "");
			evo.setEname(emp.getEname());
			evo.setJob(emp.getJob());
			//============================================
			// 获取员工领导
			// 业务1、把员工领导编号改成领导名字
			//方法1
//			Emp temp = ed.select(emp.getMgr());
//			if (temp != null) {
//				evo.setMgr(temp.getEname());
//				System.out.println(temp.getEname());
//			}
			
			//-----------------缓存技术---------------------
			int mgr = emp.getMgr();
			for (Emp emp2 : emps) {
				if(emp2.getEmpno()==mgr){
					evo.setMgr(emp2.getEname());
				}
			}
			
			//============================================
			evo.setHiredate(emp.getHiredate().toString());
			evo.setSal("$" + emp.getSal());
			if (emp.getComm() == 0.0) {
				evo.setComm("无");
			} else {
				evo.setComm("$" + emp.getComm());
			}
			
			//============================================
			// 把员工部门编号改成部门名称
			//法一、获取员工所在的部门名称
			//int deptno = emp.getDeptno();
			//Dept dept = ddao.select(deptno);
			//String dname = dept.getDname();
			//evo.setDeptno(dname + "");
			//evo.setDeptno(emp.getDeptno() + "");
			
			//用缓冲
			int deptno = emp.getDeptno();
			String dname = "";
			for (Dept dept : depts) {
				if(dept.getDeptno()==deptno){
					dname = dept.getDname();
					break;
				}
			}
			evo.setDeptno(dname);
			
			
			evoList.add(evo);
		}
		return evoList;
	}
}
