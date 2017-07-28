package org.lanqiao.paging.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	@Override
	public List<T> select(int pageSize, int currentPage) {
		return null;
	}
	@Override
	public List<T> select(int pageSize, int currentPage, T obj) {
		return null;
	}
	@Override
	public List<T> select() {
		return null;
	}
	@Override
	public T select(PK id) {
		return null;
	}
	@Override
	public void insert(T t) {
		
	}
	@Override
	public void delete() {
		
	}
	@Override
	public void delete(PK id) {
		
	}
	@Override
	public void update(T t) {
		
	}
	@Override
	public int count() {
		return 0;
	}
}
