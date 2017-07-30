package org.lanqiao.paging.service;

import java.util.List;

import org.lanqiao.paging.vo.EmpVo;

public interface EmpService { 
	
	public abstract List<EmpVo> query(int pageSize, int currentPage);

	public abstract List<EmpVo> query(int pageSize, int currentPage, EmpVo evo);

	public abstract int getEmpCount();

	public abstract String addEmp(EmpVo eVo);

	public abstract String deleteEmpById(String idStr);

	public abstract String updateEmp(EmpVo eVo);
}