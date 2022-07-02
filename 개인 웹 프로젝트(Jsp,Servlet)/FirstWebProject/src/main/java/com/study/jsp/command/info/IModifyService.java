package com.study.jsp.command.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.Service;
import com.study.jsp.dao.InfoBoardDao;

public class IModifyService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String bId = request.getParameter("bId");
		String bName = (String)session.getAttribute("name");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		InfoBoardDao dao = InfoBoardDao.getInstance();
		dao.modify(bId,bName,bTitle,bContent);
	}
}
