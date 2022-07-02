package com.study.jsp.command;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.FreeBoardDao;
import com.study.jsp.dto.FreeBoardDto;

public class FReplyListService implements Service
{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String bGroup = request.getParameter("bId");
		FreeBoardDao dao = FreeBoardDao.getInstance();
		
		
		ArrayList<FreeBoardDto> dtos = dao.replylist(bGroup);
		request.setAttribute("replylist", dtos);
		
	}
}
