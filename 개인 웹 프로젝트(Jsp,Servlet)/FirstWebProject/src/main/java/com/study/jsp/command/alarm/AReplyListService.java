package com.study.jsp.command.alarm;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.command.Service;
import com.study.jsp.dao.AlarmBoardDao;
import com.study.jsp.dto.FreeBoardDto;

public class AReplyListService implements Service
{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String bGroup = request.getParameter("bId");
		AlarmBoardDao dao = AlarmBoardDao.getInstance();
		
		
		ArrayList<FreeBoardDto> dtos = dao.replylist(bGroup);
		request.setAttribute("replylist", dtos);
		
	}
}
