package org.lanqiao.paging.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lanqiao.paging.util.DBUtil;


public class AbstractDao {
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
