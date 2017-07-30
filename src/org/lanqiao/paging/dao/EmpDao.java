package org.lanqiao.paging.dao;

import org.lanqiao.paging.entity.Emp;

public interface EmpDao extends BaseDao<Emp, Integer>{
	//根据分页参数获取当前页的数据
	String EMP_SQL_PAGE = "SELECT * FROM (SELECT e.*,ROWNUM r FROM (SELECT * FROM t_emp ORDER BY hiredate DESC) e)  WHERE r>? AND r<=?";
	//根据对象唯一标识符id查询
	String EMP_SQL_SELECT_BY_ID = "SELECT * FROM T_EMP WHERE EMPNO = ?";
	//查询所有的对象
	String EMP_SQL_SELECT_ALL = "SELECT * FROM T_EMP";
	//新增一个对象
	//String EMP_SQL_INSERT ="INSERT INTO t_emp VALUES(T_EMP_SEQ.NEXTVAL,?,?,?,to_date(?,'YYYY/MM/DD'),?,?)";
	String EMP_SQL_INSERT ="INSERT INTO t_emp VALUES(T_EMP_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
	//删除一个对象
	String EMP_SQL_DELETE ="DELETE FROM t_emp WHERE empno=? AND ename=? AND job=? AND mgr=? AND hiredate=? AND sal=? AND comm=? AND deptno=?";
	//根据id删除一个对象
	String EMP_SQL_DELETE_BY_ID ="DELETE FROM t_emp WHERE empno=?";
	//更新对象
	String EMP_SQL_UPDATE ="UPDATE t_emp SET ename=?,job=?,mgr=?,hiredate=?,sal=?,comm=?,deptno=? WHERE empno=?";
	
	/**
	 * 获得指定条件的记录数
	 */
	public int count(Emp emp);
}
