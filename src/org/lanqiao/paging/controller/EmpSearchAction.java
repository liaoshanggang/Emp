package org.lanqiao.paging.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.paging.service.EmpService;
import org.lanqiao.paging.vo.EmpVo;
@SuppressWarnings("all")
public class EmpSearchAction extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("--------get----------");
		
		//=================模拟从后台获取数据库=================
//		List<EmpVo> listVO = new ArrayList<EmpVo>();
//		for(int i=0;i<14;i++){	
//			EmpVo e1 = new EmpVo("张三丰"+i,"道士"+i,"张翠山"+i,"1400年12月"+(i+1)+"日","8888."+i,"无","10","20");
//			listVO.add(e1);
//		}
		
		//=================从服务层获取数据库=================
		//http://localhost:8080/paging/search.do?pageSize=10&currentPage=1
		EmpService es = new EmpService();
		String strPageSize = req.getParameter("pageSize");
		String strCurrentPage = req.getParameter("currentPage");
		int pageSize = 4;
		int currentPage = 1;
		if(strCurrentPage!=null){
			pageSize = Integer.parseInt(strPageSize);
		}
		if(strCurrentPage!=null){			
			currentPage = Integer.parseInt(strCurrentPage);
		}
		List<EmpVo> listVO = es.query(pageSize, currentPage);
		/*for (EmpVo empVo : listVO) {
			System.out.println(empVo);
		}*/
		//准备数据
		req.setAttribute("listVO", listVO);
		
		//请求转发
		req.getRequestDispatcher("queryEmp.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("--------post----------");
		this.doGet(req, resp);
	}
	
}
