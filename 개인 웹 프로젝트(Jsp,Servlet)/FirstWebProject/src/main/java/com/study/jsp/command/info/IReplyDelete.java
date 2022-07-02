package com.study.jsp.command.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.command.Service;
import com.study.jsp.dao.InfoBoardDao;

public class IReplyDelete implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String bId = request.getParameter("bId");
		InfoBoardDao dao = InfoBoardDao.getInstance();
		
		dao.replyDelete(bId);
		
	}
}

