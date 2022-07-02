package com.study.jsp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.MemberDao;
import com.study.jsp.dto.MemberDto;

public class ModifyService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
	    dto.setId(id);
	    dto.setPw(pw);
	    dto.setName(name);
		dto.setAddress(address);
		dto.seteMail(eMail);
		
		int ri = dao.updateMember(dto);
		
		String jsonData=null;
		if(ri == 1){
			jsonData = "{"
					+ "\"code\":\"success\","
					+ "\"desc\":\"정보가 수정되었습니다..\""
					+ "}";
	    }else{
	    	jsonData = "{"
					+ "\"code\":\"fail\","
					+ "\"desc\":\"정보 수정에 실패했습니다..\""
					+ "}";
	    }
		
		request.setAttribute("data", jsonData);
	}
}
