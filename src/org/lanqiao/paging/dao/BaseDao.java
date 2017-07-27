package org.lanqiao.paging.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T, PK extends Serializable> {
	/**
	 * 根据分页参数获取当前页的数据
	 * 
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<T> select(int pageSize, int currentPage);
	
	/**
	 * 带条件的分页查询
	 * @param pageSize
	 * @param currentPage
	 * @param obj
	 * @return
	 */
	public List<T> select(int pageSize, int currentPage,T obj);

	/**
	 * 查询所有的对象
	 * 
	 * @return
	 */
	public List<T> select();

	/**
	 * 根据对象唯一标识符id查询
	 * 
	 * @param deptno
	 * @return
	 */
	public T select(PK id);

	/**
	 * 新增一个对象
	 * @param t
	 */
	public void insert(T t);

	/**
	 * 删除一个对象
	 */
	public void delete();

	/**
	 * 根据id删除一个对象
	 * @param id
	 */
	public void delete(PK id);

	/**
	 * 更新对象
	 * @param t
	 */
	public void update(T t);

	/**
	 * 
	 * @return
	 */
	public int count();
}
