package com.study.jsp.command.admin;

import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.command.Service;
import com.study.jsp.dao.MemberDao;

public class blackCheckService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		MemberDao dao = MemberDao.getInstance();
		
		
		
		String jsonData = null;
		if(dao.blackCheck(id).before(new Timestamp(System.currentTimeMillis()))) {
			jsonData = "{"
					+ "\"code\":\"success\","
					+ "\"desc\":\"블랙멤버 아님\""
					+ "}";
			System.out.println("블랙아님");
		}else{
			Timestamp ri =dao.blackCheck(id);
			jsonData = "{"
					+ "\"code\":\"fail\","
					+ "\"desc\":\""+ ri +" 까지 로그인 정지입니다.\""
					+ "}";
			System.out.println(ri +" 까지 블랙");
		}
		request.setAttribute("data", jsonData);
	}
}
