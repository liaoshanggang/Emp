package org.lanqiao.paging.dao;

import org.lanqiao.paging.entity.Dept;

public interface DeptDao extends BaseDao<Dept, Integer> {
	String DEPT_SQL_SELECT = "SELECT * FROM T_DEPT WHERE DEPTNO = ?";
	String DEPT_SQL_ALL = "SELECT * FROM T_DEPT";

}
