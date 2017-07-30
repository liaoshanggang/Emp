package org.lanqiao.paging.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
				emp.setJob(rst.getString("JOB"));
				emp.setMgr(rst.getInt("MGR"));
				emp.setHiredate(rst.getDate("HIREDATE"));
				emp.setSal(rst.getDouble("SAL"));
				emp.setComm(rst.getDouble("COMM"));
				emp.setDeptno(rst.getInt("DEPTNO"));
				System.out.println(emp);
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
			pstat = conn.prepareStatement(EMP_SQL_SELECT_ALL);

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
			pstat = conn.prepareStatement(EMP_SQL_SELECT_BY_ID);

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
	public int insert(Emp t) {
		
		// 加载驱动
		conn = getConn();
		int count = -1;
		try {

			// 获得连接

			// 创建语句
			pstat = conn.prepareStatement(EMP_SQL_INSERT);

			// 绑定变量
			pstat.setString(1, t.getEname());
			pstat.setString(2, t.getJob());
			pstat.setInt(3, t.getMgr());
			pstat.setDate(4, t.getHiredate());
			pstat.setDouble(5, t.getComm());
			pstat.setDouble(6, t.getSal());
			pstat.setDouble(7, t.getDeptno());

			// 执行SQL
			count = pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstat, rst);
		}
		return count;
	}

	@Override
	public int delete() {
		return 0;}

	@Override
	public int delete(Integer id) {
		
		// 加载驱动
		conn = getConn();
		int count = -1;
		try {

			// 获得连接

			// 创建语句
			pstat = conn.prepareStatement(EMP_SQL_DELETE_BY_ID);

			// 绑定变量
			pstat.setInt(1, id);

			// 执行SQL
			count = pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstat, rst);
		}
		return count;
	
	}

	@Override
	public int update(Emp t) {
		
		// 加载驱动
		conn = getConn();
		int count = -1;
		try {

			// 获得连接

			// 创建语句
			pstat = conn.prepareStatement(EMP_SQL_UPDATE);

			// 绑定变量
			pstat.setString(1, t.getEname());
			pstat.setString(2, t.getJob());
			pstat.setInt(3, t.getMgr());
			pstat.setDate(4, t.getHiredate());
			pstat.setDouble(5, t.getComm());
			pstat.setDouble(6, t.getSal());
			pstat.setDouble(7, t.getDeptno());
			pstat.setInt(8, t.getEmpno());

			// 执行SQL
			count = pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstat, rst);
		}
		return count;
	}

	@Override
	public int count() {
		return 0;
	}

	@Override
	public int count(Emp emp) {
		return 0;
	}
	
	public static void main(String[] args) {
		EmpDaoImpl edi = new EmpDaoImpl();
//		/7499	ALLEN	SALESMAN	7698	1981/2/20	1600.00	300.00	30

		/*java.sql.Date sqlDate = strToSqlDate("2017/2/3");
		Emp emp = new Emp("ALLEN", "SALESMAN", 7698, sqlDate, 1600.00, 300.00, 30);
		edi.insert(emp);*/
		//edi.delete(8006);
		java.sql.Date sqlDate = strToSqlDate("2017/2/5");
		Emp emp = new Emp(8007,"ALLEN", "SALESMAN", 8007, sqlDate, 1700.00, 400.00, 50);
		edi.update(emp);
	}
	private static java.sql.Date strToSqlDate(String dateStringToParse) {
		SimpleDateFormat bartDateFormat =  
		        new SimpleDateFormat("yyyy/MM/dd");
		java.sql.Date sqlDate = null;
		       try {  
		        java.util.Date date = bartDateFormat.parse(dateStringToParse);  
		        sqlDate = new java.sql.Date(date.getTime());
		        System.out.println(sqlDate.getTime());  
		       }  
		       catch (Exception ex) {  
		        System.out.println(ex.getMessage());  
		       }
		       return sqlDate;
	}
}
