package org.lanqiao.paging.dao;

import java.util.List;

import org.lanqiao.paging.entity.Dept;

public interface DeptDao {
	String DEPT_SQL_SELECT = "SELECT * FROM T_DEPT WHERE DEPTNO = ?";
	String DEPT_SQL_ALL = "SELECT * FROM T_DEPT";

	/**
	 * 获得所有部门信息
	 * 
	 * @return
	 */
	public List<Dept> select();

	/**
	 * 获得指定员工部门的信息
	 * 
	 * @param deptno
	 * @return
	 */
	public Dept select(int deptno);
}
