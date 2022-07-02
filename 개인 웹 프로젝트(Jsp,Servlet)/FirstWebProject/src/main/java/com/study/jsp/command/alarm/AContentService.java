package com.study.jsp.command.alarm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.command.Service;
import com.study.jsp.dao.AlarmBoardDao;
import com.study.jsp.dto.FreeBoardDto;

public class AContentService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bId = request.getParameter("bId");
		AlarmBoardDao dao = AlarmBoardDao.getInstance();
		FreeBoardDto dto = dao.contentView(bId);
		
		
		request.setAttribute("fContentView", dto);
	}
}
