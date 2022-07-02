package com.study.jsp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.FreeBoardDao;

public class FWriteService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");	
		String bName = (String)session.getAttribute("name");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		FreeBoardDao dao = FreeBoardDao.getInstance();
		dao.write(bName,bTitle,bContent,id);
		String jsonData=null;
		jsonData = "{"
				+ "\"code\":\"success\","
				+ "\"desc\":\"글 작성 되었습니다..\""
				+ "}";
		
		request.setAttribute("data", jsonData);
	}
}
