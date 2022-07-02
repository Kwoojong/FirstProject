package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.FreeBoardDao;
import com.study.jsp.dto.FreeBoardDto;

public class FReplyViewService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bId = request.getParameter("bId");
		FreeBoardDao dao = FreeBoardDao.getInstance();
		FreeBoardDto dto = dao.reply_view(bId);
		
		request.setAttribute("fReplyView", dto);
	}
}
