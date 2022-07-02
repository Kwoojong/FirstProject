package com.study.jsp.command.alarm;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.Service;
import com.study.jsp.dao.AlarmBoardDao;
import com.study.jsp.dto.FreeBoardDto;
import com.study.jsp.dto.anotherDto;
import com.study.jsp.dto.fPageInfo;

public class AListService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		int nPage= 1;
		try {
			
			String sPage = request.getParameter("page");
			
			nPage = Integer.parseInt(sPage);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		String search = null;
		String searchSomething = null;
		
		search = request.getParameter("search");
		searchSomething = request.getParameter("searchSomething");
		
		System.out.println("검색종류 : " + search);
		System.out.println("검색어 : " + searchSomething);
		System.out.println("페이지 : " + nPage);
		
		AlarmBoardDao dao = AlarmBoardDao.getInstance();
		fPageInfo pinfo = dao.articlePage(nPage,search,searchSomething);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		
		
		ArrayList<FreeBoardDto> dtos = dao.list(nPage,search,searchSomething);
		ArrayList<anotherDto> dto = dao.replycount(nPage,search,searchSomething);
		
		request.setAttribute("list", dtos);
		request.setAttribute("replycount", dto);
	}
}
