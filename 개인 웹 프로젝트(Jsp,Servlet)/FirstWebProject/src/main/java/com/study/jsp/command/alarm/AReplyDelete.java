package com.study.jsp.command.alarm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.command.Service;
import com.study.jsp.dao.AlarmBoardDao;

public class AReplyDelete implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String bId = request.getParameter("bId");
		AlarmBoardDao dao = AlarmBoardDao.getInstance();
		
		dao.replyDelete(bId);
		
	}
}

