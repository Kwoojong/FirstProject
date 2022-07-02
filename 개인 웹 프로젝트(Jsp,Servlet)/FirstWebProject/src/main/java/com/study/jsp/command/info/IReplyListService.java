package com.study.jsp.command.info;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.command.Service;
import com.study.jsp.dao.InfoBoardDao;
import com.study.jsp.dto.FreeBoardDto;

public class IReplyListService implements Service
{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String bGroup = request.getParameter("bId");
		InfoBoardDao dao = InfoBoardDao.getInstance();
		
		
		ArrayList<FreeBoardDto> dtos = dao.replylist(bGroup);
		request.setAttribute("replylist", dtos);
		
	}
}
