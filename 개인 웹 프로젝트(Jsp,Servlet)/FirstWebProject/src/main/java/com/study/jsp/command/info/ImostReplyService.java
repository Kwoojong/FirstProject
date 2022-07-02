package com.study.jsp.command.info;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.command.Service;
import com.study.jsp.dao.InfoBoardDao;
import com.study.jsp.dto.anotherDto2;

public class ImostReplyService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		String date = request.getParameter("date");
		InfoBoardDao dao = InfoBoardDao.getInstance();
		anotherDto2 dto = dao.mostReply(date);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		writer.print("id : " +  dto.getId() +"<br>" );
		writer.print("댓글 수 : " + dto.getWnCount() );
		writer.close();
	}
}