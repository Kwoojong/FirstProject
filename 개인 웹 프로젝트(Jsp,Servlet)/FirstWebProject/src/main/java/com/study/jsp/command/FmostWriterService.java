package com.study.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.FreeBoardDao;
import com.study.jsp.dto.anotherDto2;

public class FmostWriterService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		String date = request.getParameter("date");
		FreeBoardDao dao = FreeBoardDao.getInstance();
		anotherDto2 dto = dao.mostWrite(date);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		
		writer.print("id : " +  dto.getId() +"<br>" );
		writer.print("게시글 수 : " + dto.getWnCount()  );
		writer.close();
	}
}