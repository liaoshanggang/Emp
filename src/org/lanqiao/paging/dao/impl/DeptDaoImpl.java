package org.lanqiao.paging.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.paging.dao.DeptDao;
import org.lanqiao.paging.entity.Dept;
import org.lanqiao.paging.entity.Emp;

public class DeptDaoImpl implements DeptDao {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER = "scott";
	public static final String PASSWORD = "tiger";

	public Emp select(int deptno, int empno) {
		return null;
	}

	/**
	 * 获得所有部门信息
	 * 
	 * @return
	 */
	public List<Dept> select() {
		List<Dept> elist = null;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rst = null;
		// 加载驱动
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			// 获得连接
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// 创建语句
			pstat = conn.prepareStatement(DEPT_SQL_ALL);

			// 绑定变量

			// 执行SQL
			rst = pstat.executeQuery();

			elist = new ArrayList<Dept>();
			// 处理结果
			while (rst.next()) {
				Dept dept = new Dept();
				dept.setDeptno(rst.getInt("DEPTNO"));
				dept.setDname(rst.getString("DNAME"));
				dept.setLoc(rst.getString("LOC"));
				elist.add(dept);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	 * 获得指定员工部门的信息
	 * 
	 * @param deptno
	 * @return
	 */
	public Dept select(int deptno) {
		Dept dept = null;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rst = null;
		// 加载驱动
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			// 获得连接
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// 创建语句
			pstat = conn.prepareStatement(DEPT_SQL_SELECT);

			// 绑定变量
			pstat.setInt(1, deptno);

			// 执行SQL
			rst = pstat.executeQuery();

			// 处理结果
			while (rst.next()) {
				dept = new Dept();
				dept.setDeptno(rst.getInt("DEPTNO"));
				dept.setDname(rst.getString("DNAME"));
				dept.setLoc(rst.getString("LOC"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		return dept;
	}
}
