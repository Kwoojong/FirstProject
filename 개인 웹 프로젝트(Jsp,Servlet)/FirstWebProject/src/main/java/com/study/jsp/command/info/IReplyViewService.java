package com.study.jsp.command.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.command.Service;
import com.study.jsp.dao.InfoBoardDao;
import com.study.jsp.dto.FreeBoardDto;

public class IReplyViewService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bId = request.getParameter("bId");
		InfoBoardDao dao = InfoBoardDao.getInstance();
		FreeBoardDto dto = dao.reply_view(bId);
		
		request.setAttribute("fReplyView", dto);
	}
}
