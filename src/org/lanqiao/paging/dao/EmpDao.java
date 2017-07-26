package org.lanqiao.paging.dao;

import java.util.List;

import org.lanqiao.paging.entity.Emp;

public interface EmpDao {
	String EMP_SQL_PAGE = "SELECT * FROM (SELECT e.*,ROWNUM r FROM t_emp e)  WHERE r>? AND r<=?";
	String EMP_SQL_SELECT = "SELECT * FROM T_EMP WHERE EMPNO = ?";
	String EMP_SQL_ALL = "SELECT * FROM T_EMP";

	public Emp select(int deptno, int empno);

	/**
	 * 获得所有员工信息
	 * 
	 * @return
	 */
	public List<Emp> select();

	/**
	 * 获得指定员工领导的信息
	 * 
	 * @param empno
	 * @return
	 */
	public Emp select(int empno);

	/**
	 * 根据当前页，和指定当前页显示多少条记录获得一页员工信息
	 * 
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<Emp> select_page(int pageSize, int currentPage);

}
