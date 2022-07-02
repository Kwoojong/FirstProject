package com.study.jsp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.MemberDao;

public class WithdrawService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String pw = request.getParameter("pw");
		
		MemberDao dao = MemberDao.getInstance();
		
		int ri = dao.deleteMember(id,pw);
		
		String jsonData=null;
		if(ri==1) {
			jsonData = "{"
					+ "\"code\":\"success\","
					+ "\"desc\":\"회원 탈퇴되었습니다.\""
					+ "}";
		}else {
			jsonData = "{"
					+ "\"code\":\"fail\","
					+ "\"desc\":\"비밀번호가 일치하지않습니다.\""
					+ "}";
		}
		request.setAttribute("data", jsonData);
	}
}
