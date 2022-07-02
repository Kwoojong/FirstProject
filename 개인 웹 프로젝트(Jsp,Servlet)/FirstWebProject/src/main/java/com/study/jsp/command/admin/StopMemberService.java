package com.study.jsp.command.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.command.Service;
import com.study.jsp.dao.MemberDao;

public class StopMemberService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		String stopDate = request.getParameter("stoplogindate");
		String id = request.getParameter("id");
		MemberDao dao = MemberDao.getInstance();
		
		int ri =dao.stopMember(stopDate, id);
		
		System.out.println(ri);
		if(dao.stopMember(stopDate, id)==1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.print(stopDate + "일 정지시켰습니다.");
			writer.close();
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.print(stopDate + "정지실패 day(일)을 입력해주세요.");
			writer.close();
		}
		
		
	}
}