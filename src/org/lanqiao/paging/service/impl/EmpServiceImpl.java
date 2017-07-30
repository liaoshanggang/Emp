package org.lanqiao.paging.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.paging.dao.DeptDao;
import org.lanqiao.paging.dao.EmpDao;
import org.lanqiao.paging.dao.impl.DeptDaoImpl;
import org.lanqiao.paging.dao.impl.EmpDaoImpl;
import org.lanqiao.paging.entity.Dept;
import org.lanqiao.paging.entity.Emp;
import org.lanqiao.paging.service.EmpService;
import org.lanqiao.paging.vo.EmpVo;

public class EmpServiceImpl implements EmpService {
	private EmpDao edao;
	private DeptDao ddao;
	private List<Emp> emps;// 缓存
	private List<Dept> depts;

	public EmpServiceImpl() {
		edao = new EmpDaoImpl();
		emps = edao.select();

		// -----------一下代码是为了获得员工所在部门-2017年7月26日---------------
		ddao = new DeptDaoImpl();
		depts = ddao.select();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lanqiao.paging.service.EmpService#query(int, int)
	 */
	@Override
	public List<EmpVo> query(int pageSize, int currentPage) {
		List<Emp> eList = edao.select(pageSize, currentPage);
		List<EmpVo> evoList = new ArrayList<EmpVo>();
		for (Emp emp : eList) {
			EmpVo evo = new EmpVo();
			evo.setEmpno(emp.getEmpno() + "");
			evo.setEname(emp.getEname());
			evo.setJob(emp.getJob());
			// ============================================
			// 获取员工领导
			// 业务1、把员工领导编号改成领导名字
			// 方法1
			// Emp temp = ed.select(emp.getMgr());
			// if (temp != null) {
			// evo.setMgr(temp.getEname());
			// System.out.println(temp.getEname());
			// }

			// -----------------缓存技术---------------------
			int mgr = emp.getMgr();
			for (Emp emp2 : emps) {
				if (emp2.getEmpno() == mgr) {
					evo.setMgr(emp2.getEname());
				}
			}

			// ============================================
			evo.setHiredate(emp.getHiredate().toString());
			evo.setSal("$" + emp.getSal());
			if (emp.getComm() == 0.0) {
				evo.setComm("无");
			} else {
				evo.setComm("$" + emp.getComm());
			}

			// ============================================
			// 把员工部门编号改成部门名称
			// 法一、获取员工所在的部门名称
			// int deptno = emp.getDeptno();
			// Dept dept = ddao.select(deptno);
			// String dname = dept.getDname();
			// evo.setDeptno(dname + "");
			// evo.setDeptno(emp.getDeptno() + "");

			// 用缓冲
			int deptno = emp.getDeptno();
			String dname = "";
			for (Dept dept : depts) {
				if (dept.getDeptno() == deptno) {
					dname = dept.getDname();
					break;
				}
			}
			evo.setDeptno(dname);

			evoList.add(evo);
		}
		return evoList;
	}

	@Override
	public List<EmpVo> query(int pageSize, int currentPage, EmpVo vo) {
		List<EmpVo> evolist = new ArrayList<EmpVo>();

		// 调用dao
		// List<Emp> elist=edao.select(pageSize, currentPage);

		Emp entity = new Emp();
		entity.setDeptno(Integer.parseInt(vo.getDeptno()));
		double beginSal = Integer.parseInt(vo.getBeginSal());
		double endSal = Integer.parseInt(vo.getEndSal());
		List<Emp> elist = edao.select(pageSize, currentPage, entity);

		// 将实体集合转换成vo集合
		for (Emp ele : elist) {
			EmpVo evo = new EmpVo();

			evo.setEmpno(ele.getEmpno() + "");
			evo.setEname(ele.getEname());
			evo.setJob(ele.getJob());
			// 如何获取员工领导？
			// 一、通过在循环中调用dao，得到指定员工的领导
			// 1、得到员工领导的编号
			// 2、调用dao的select方法

			/*
			 * Emp temp=edao.select(ele.getMgr());
			 * evo.setMgr(temp.getEname()+"");
			 */
			// 修改结束

			// --------------------缓存技术----------------------
			// 直接在员工的集合中获得
			// 1、得到该员工的领导编号，
			int mgr = ele.getMgr();
			// 2、在员工的集合中找到员工编号=mgr
			for (Emp ele2 : emps) {// 在缓存中查找员工领导
				if (ele2.getEmpno() == mgr) {
					evo.setMgr(ele2.getEname());
				}
			}

			// -----------------------------------------------

			evo.setHiredate(ele.getHiredate().toString());
			evo.setSal("$" + ele.getSal());
			if (ele.getComm() == 0.0) {
				evo.setComm("无");
			} else {
				evo.setComm("$" + ele.getComm());
			}

			// 如何获取员工所在的部门名称？

			// 方法1，步骤如下：
			// a、得到员工所在的部门编号
			// int deptno = ele.getDeptno();
			// b、通过DeptDAO得到部门实体
			// Dept temp = ddao.select(deptno);
			// c、通过部门实体获得部门名称
			// String dname = temp.getDname();
			// d、修改evo中的部门编号为部门名称
			// evo.setDeptno(dname);
			int deptno = ele.getDeptno();
			// 作业1，用缓存技术优化方法1的代码
			for (Dept dept : depts) {
				if (dept.getDeptno() == deptno) {
					evo.setDeptno(dept.getDname());
				}
			}

			// 根据条件筛选指定的员工信息，并添加到集合中去
			if (ele.getSal() > beginSal && ele.getSal() < endSal) {
				evolist.add(evo);
			}
		}

		return evolist;
	}

	@Override
	public int getEmpCount() {
		return emps.size();
	}

	@Override
	public String addEmp(EmpVo eVo) {
		Emp t = new Emp();
		t.setEname(eVo.getEname());
		t.setJob(eVo.getJob());
		t.setMgr(Integer.parseInt(eVo.getMgr()));
		java.sql.Date sqlDate = strToSqlDate(eVo.getHiredate());
		t.setHiredate(sqlDate);
		t.setComm(Double.parseDouble(eVo.getComm()));
		t.setSal(Double.parseDouble(eVo.getSal()));
		t.setDeptno(Integer.parseInt(eVo.getDeptno()));

		int flag = edao.insert(t);
		if (flag == 1) {
			return "success";
		}
		return "fail";
	}

	@Override
	public String deleteEmpById(String idStr) {
		int idInt = stringToInt(idStr);
		int flag = edao.delete(idInt);
		if (flag == 1) {
			return "success";
		}
		return "fail";
	}

	@Override
	public String updateEmp(EmpVo eVo) {
		Emp t = new Emp();
		t.setEmpno(Integer.parseInt(eVo.getDeptno()));
		t.setEname(eVo.getEname());
		t.setJob(eVo.getJob());
		t.setMgr(Integer.parseInt(eVo.getMgr()));
		java.sql.Date sqlDate = strToSqlDate(eVo.getHiredate());
		t.setHiredate(sqlDate);
		t.setComm(Double.parseDouble(eVo.getComm()));
		t.setSal(Double.parseDouble(eVo.getSal()));
		t.setDeptno(Integer.parseInt(eVo.getDeptno()));

		int flag = edao.update(t);
		if (flag == 1) {
			return "success";
		}
		return "fail";
	}

	public java.sql.Date strToSqlDate(String dateStringToParse) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		java.sql.Date sqlDate = null;
		try {
			java.util.Date date = bartDateFormat.parse(dateStringToParse);
			sqlDate = new java.sql.Date(date.getTime());
			System.out.println(sqlDate.getTime());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return sqlDate;
	}

	private int stringToInt(String str) {

		int result = -1;
		try {
			result = Integer.valueOf(str);
		} catch (NumberFormatException e) {
			// Logger.error(str+": 转型异常"+ e.getMessage());
			System.out.println(e.getMessage());
		}

		return result;
	}
}
