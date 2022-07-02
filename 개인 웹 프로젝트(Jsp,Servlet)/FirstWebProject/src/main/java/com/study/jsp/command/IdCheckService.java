package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.MemberDao;
import com.study.jsp.dto.MemberDto;

public class IdCheckService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		String id = request.getParameter("id");
		
		MemberDto dto = new MemberDto();
		dto.setId(id);
		
		MemberDao dao = MemberDao.getInstance();
		
		String jsonData;
		
		if(dao.confirmId(dto.getId()) == MemberDao.MEMBER_EXISTENT)
		{
			jsonData = "{"
					+ "\"code\":\"success\","
					+ "\"desc\":\"아이디가 이미 존재합니다.\""
					+ "}";
		}else 
		{
			jsonData = "{"
					+ "\"code\":\"success\","
					+ "\"desc\":\"사용 가능한 아이디입니다.\""
					+ "}";
		}
		
		request.setAttribute("data", jsonData);
	}
}
