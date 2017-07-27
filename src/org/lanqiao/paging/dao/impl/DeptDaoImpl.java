package org.lanqiao.paging.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.paging.dao.DeptDao;
import org.lanqiao.paging.entity.Dept;

public class DeptDaoImpl extends AbstractDao<Dept,Integer> implements DeptDao {

	@Override
	public List<Dept> select(int pageSize, int currentPage) {
		return null;
	}

	@Override
	public List<Dept> select(int pageSize, int currentPage, Dept obj) {
		return null;
	}

	@Override
	public List<Dept> select() {
		List<Dept> elist = null;

		// 加载驱动
		conn = getConn();
		try {

			// 获得连接

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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstat, rst);
		}
		return elist;
	}

	@Override
	public Dept select(Integer id) {
		Dept dept = null;
		// 加载驱动
		conn = getConn();
		try {

			// 获得连接

			// 创建语句
			pstat = conn.prepareStatement(DEPT_SQL_SELECT);

			// 绑定变量
			pstat.setInt(1, id);

			// 执行SQL
			rst = pstat.executeQuery();

			// 处理结果
			while (rst.next()) {
				dept = new Dept();
				dept.setDeptno(rst.getInt("DEPTNO"));
				dept.setDname(rst.getString("DNAME"));
				dept.setLoc(rst.getString("LOC"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstat, rst);
		}
		return dept;
	}

	@Override
	public void insert(Dept t) {

	}

	@Override
	public void delete() {

	}

	@Override
	public void delete(Integer id) {

	}

	@Override
	public void update(Dept t) {

	}

	@Override
	public int count() {
		return 0;
	}

}
