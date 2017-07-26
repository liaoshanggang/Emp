package org.lanqiao.paging.service;

import java.util.ArrayList;
import java.util.List;

import org.lanqiao.paging.dao.EmpDao;
import org.lanqiao.paging.entity.Emp;
import org.lanqiao.paging.vo.EmpVo;

public class EmpService {
	private EmpDao ed;
	private List<Emp> emps;// 缓存

	public EmpService() {
		ed = new EmpDao();
		emps = ed.select();
	}

	public List<EmpVo> query(int pageSize, int currentPage) {
		List<Emp> eList = ed.select_page(pageSize, currentPage);
		List<EmpVo> evoList = new ArrayList<EmpVo>();
		for (Emp emp : eList) {
			EmpVo evo = new EmpVo();
			evo.setEmpno(emp.getEmpno() + "");
			evo.setEname(emp.getEname());
			evo.setJob(emp.getJob());
			//============================================
			// 获取员工领导
			// 把员工领导编号改成领导名字
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

			// 把员工部门编号改成部门名称
			evo.setDeptno(emp.getDeptno() + "");
			evoList.add(evo);
		}
		return evoList;
	}
}
