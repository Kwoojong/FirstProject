package com.study.jsp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.FreeBoardDao;

public class FLikeService	implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String bId = request.getParameter("bId");
		FreeBoardDao dao = FreeBoardDao.getInstance();
		
		dao.upLike(bId);
	}
}
