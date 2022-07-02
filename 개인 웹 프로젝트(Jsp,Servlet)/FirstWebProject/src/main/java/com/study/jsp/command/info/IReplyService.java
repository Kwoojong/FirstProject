package com.study.jsp.command.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.Service;
import com.study.jsp.dao.InfoBoardDao;

public class IReplyService implements Service
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
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		String id = (String)session.getAttribute("id");	
		
		InfoBoardDao dao = InfoBoardDao.getInstance();
		dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent, id);
	}
}
