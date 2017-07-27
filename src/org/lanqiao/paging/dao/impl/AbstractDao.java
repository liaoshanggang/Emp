package org.lanqiao.paging.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lanqiao.paging.dao.BaseDao;
import org.lanqiao.paging.util.DBUtil;


public abstract class AbstractDao<T, PK extends Serializable> implements BaseDao<T, PK>{
	protected Connection conn = null;
	protected PreparedStatement pstat = null;
	protected ResultSet rst = null;
	
	protected Connection getConn() {
		return DBUtil.getConn();
	}
	protected void close(Connection conn,PreparedStatement pstat,ResultSet rst){
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
}
