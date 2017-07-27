package org.lanqiao.paging.dao;

import org.lanqiao.paging.entity.Emp;

public interface EmpDao extends BaseDao<Emp, Integer>{
	String EMP_SQL_PAGE = "SELECT * FROM (SELECT e.*,ROWNUM r FROM t_emp e)  WHERE r>? AND r<=?";
	String EMP_SQL_SELECT = "SELECT * FROM T_EMP WHERE EMPNO = ?";
	String EMP_SQL_ALL = "SELECT * FROM T_EMP";
	/**
	 * 获得指定条件的记录数
	 */
}
