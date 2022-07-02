package com.study.jsp.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.command.Service;
import com.study.jsp.dao.MemberDao;

public class BanMemberService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		MemberDao dao = MemberDao.getInstance();
		
		
		String jsonData = null;
		if(dao.banMember(id)==1) {
			dao.banMember(id);
			jsonData = "{"
					+ "\"code\":\"success\","
					+ "\"desc\":\""+ id + "님은 강퇴되었습니다.\""
					+ "}";
			System.out.println("킥킥킥킥");
		}else {
			jsonData = "{"
					+ "\"code\":\"fail\","
					+ "\"desc\":\" 강퇴 실패....\""
					+ "}";
		}
		request.setAttribute("data", jsonData);
	}
}	