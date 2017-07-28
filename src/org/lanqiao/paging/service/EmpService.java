package org.lanqiao.paging.service;

import java.util.List;

import org.lanqiao.paging.vo.EmpVo;

public interface EmpService {

	public abstract List<EmpVo> query(int pageSize, int currentPage);

	public abstract List<EmpVo> query(int pageSize, int currentPage, EmpVo evo);

}