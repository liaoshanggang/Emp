package org.lanqiao.paging.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.paging.dao.EmpDao;
import org.lanqiao.paging.entity.Emp;

public class EmpDaoImpl extends AbstractDao<Emp, Integer> implements EmpDao {

	@Override
	public List<Emp> select(int pageSize, int currentPage) {
		List<Emp> elist = null;
		// 加载驱动
		//conn = DBUtil.getConn();
		conn = getConn();
		try {

			// 获得连接

			// 创建语句
			pstat = conn.prepareStatement(EMP_SQL_PAGE);

			// 绑定变量
			pstat.setInt(1, (currentPage - 1) * pageSize);
			pstat.setInt(2, currentPage * pageSize);

			// 执行SQL
			rst = pstat.executeQuery();

			elist = new ArrayList<Emp>();
			// 处理结果
			while (rst.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rst.getInt("EMPNO"));
				emp.setEname(rst.getString("ENAME"));
				emp.setJob(rst.getString("job"));
				emp.setMgr(rst.getInt("mgr"));
				emp.setHiredate(rst.getDate("hiredate"));
				emp.setSal(rst.getDouble("sal"));
				emp.setComm(rst.getDouble("comm"));
				emp.setDeptno(rst.getInt("deptno"));
				elist.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstat, rst);
		}
		return elist;
	}

	@Override
	public List<Emp> select(int pageSize, int currentPage, Emp obj) {
		return null;
	}

	@Override
	public List<Emp> select() {
		List<Emp> elist = null;
		//conn = DBUtil.getConn();
		conn = getConn();
		try {
			// 获得连接

			// 创建语句
			pstat = conn.prepareStatement(EMP_SQL_ALL);

			// 绑定变量

			// 执行SQL
			rst = pstat.executeQuery();

			elist = new ArrayList<Emp>();
			// 处理结果
			while (rst.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rst.getInt("EMPNO"));
				emp.setEname(rst.getString("ENAME"));
				emp.setJob(rst.getString("job"));
				emp.setMgr(rst.getInt("mgr"));
				emp.setHiredate(rst.getDate("hiredate"));
				emp.setSal(rst.getDouble("sal"));
				emp.setComm(rst.getDouble("comm"));
				emp.setDeptno(rst.getInt("deptno"));
				elist.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstat, rst);
		}
		return elist;
	}

	@Override
	public Emp select(Integer id) {
		Emp emp = null;
		// 加载驱动
		conn = getConn();
		try {

			// 获得连接

			// 创建语句
			pstat = conn.prepareStatement(EMP_SQL_SELECT);

			// 绑定变量
			pstat.setInt(1, id);

			// 执行SQL
			rst = pstat.executeQuery();

			// 处理结果
			while (rst.next()) {
				emp = new Emp();
				emp.setEmpno(rst.getInt("EMPNO"));
				emp.setEname(rst.getString("ENAME"));
				emp.setJob(rst.getString("job"));
				emp.setMgr(rst.getInt("mgr"));
				emp.setHiredate(rst.getDate("hiredate"));
				emp.setSal(rst.getDouble("sal"));
				emp.setComm(rst.getDouble("comm"));
				emp.setDeptno(rst.getInt("deptno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstat, rst);
		}
		return emp;
	}

	@Override
	public void insert(Emp t) {

	}

	@Override
	public void delete() {

	}

	@Override
	public void delete(Integer id) {

	}

	@Override
	public void update(Emp t) {

	}

	@Override
	public int count() {
		return 0;
	}

	@Override
	public int count(Emp emp) {
		// TODO Auto-generated method stub
		return 0;
	}
}
