package org.lanqiao.paging.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.paging.dao.EmpDao;
import org.lanqiao.paging.entity.Emp;
import org.lanqiao.paging.util.DBUtil;

public class EmpDaoImpl implements EmpDao {
	/*public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER = "scott";
	public static final String PASSWORD = "tiger";*/

	public Emp select(int deptno, int empno) {
		return null;
	}

	/**
	 * 获得所有员工信息
	 * 
	 * @return
	 */
	public List<Emp> select() {
		List<Emp> elist = null;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rst = null;
		conn = DBUtil.getConn();
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
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstat != null) {
					pstat.close();
				}
				if (rst != null) {
					rst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return elist;
	}

	/**
	 * 获得指定员工领导的信息
	 * 
	 * @param empno
	 * @return
	 */
	public Emp select(int empno) {
		Emp emp = null;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rst = null;
		// 加载驱动
		conn = DBUtil.getConn();
		try {

			// 获得连接

			// 创建语句
			pstat = conn.prepareStatement(EMP_SQL_SELECT);

			// 绑定变量
			pstat.setInt(1, empno);

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
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstat != null) {
					pstat.close();
				}
				if (rst != null) {
					rst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return emp;
	}

	/**
	 * 根据当前页，和指定当前页显示多少条记录获得一页员工信息
	 * 
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<Emp> select_page(int pageSize, int currentPage) {
		List<Emp> elist = null;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rst = null;
		// 加载驱动
		conn = DBUtil.getConn();
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
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstat != null) {
					pstat.close();
				}
				if (rst != null) {
					rst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return elist;
	}

	public static void main(String[] args) {
		EmpDaoImpl empDao = new EmpDaoImpl();
		// List<Emp> elist = empDao.select_page(5, 2);
		// for (Emp emp : elist) {
		// System.out.println(emp);
		// }

		// Emp emp = new Emp();
		// emp = empDao.select(7698);
		// System.out.println(emp);

		// List<Emp> elist = empDao.select();
		// for (Emp emp : elist) {
		// System.out.println(emp);
		// }
	}
}
