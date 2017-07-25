package org.lanqiao.paging.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.paging.entity.Emp;

public class EmpDao {
	private static final String EMP_SQL_PAGE="SELECT * FROM (SELECT e.*,ROWNUM r FROM t_emp e)  WHERE r>? AND r<=?";
	public static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String user = "scott";
	public static final String password = "tiger";
	public List<Emp> page(int pageSize,int currentPage){
		List<Emp> elist = null;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rst = null;
		//加载驱动
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			//获得连接
			conn = DriverManager.getConnection(url,user,password);

			//创建语句
			pstat = conn.prepareStatement(EMP_SQL_PAGE);

			//绑定变量
			pstat.setInt(1, (currentPage-1)*pageSize);
			pstat.setInt(2, currentPage*pageSize);

			//执行SQL
			rst = pstat.executeQuery();
			
			elist = new ArrayList<Emp>();
			//处理结果
			while(rst.next()){
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(conn!=null){
					conn.close();				
				}
				if(pstat!=null){
					pstat.close();
				}
				if (rst!=null) {
					rst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return elist;
	}
	public static void main(String[] args) {
		EmpDao empDao= new EmpDao();
		List<Emp> elist = empDao.page(5, 2);
		for (Emp emp : elist) {
			System.out.println(emp);
		}
	}
}
