package org.lanqiao.paging.dao;

import org.lanqiao.paging.entity.Dept;

public interface DeptDao extends BaseDao<Dept, Integer> {
	String DEPT_SQL_SELECT_BY_ID = "SELECT * FROM T_DEPT WHERE DEPTNO = ?";
	String DEPT_SQL_SELECT_ALL = "SELECT * FROM T_DEPT";
	//新增一个对象
	String DEPT_SQL_INSERT ="";
	//删除一个对象
	String DEPT_SQL_DELETE ="";
	//根据id删除一个对象
	String DEPT_SQL_DELETE_BY_ID ="";
	//更新对象
	String DEPT_SQL_UPDATE ="";
}
