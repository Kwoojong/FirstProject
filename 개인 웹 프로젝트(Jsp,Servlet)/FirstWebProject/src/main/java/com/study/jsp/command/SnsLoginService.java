package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SnsLoginService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		System.out.println(id);
		System.out.println(name);
		
		HttpSession session = request.getSession();
    	session.setAttribute("id", id);
    	session.setAttribute("name", name);
    	session.setAttribute("ValidMem", "yes");
    	String jsonData = null;
    	jsonData = "{"
				+ "\"code\":\"success\","
				+ "\"desc\":\"로그인 성공\""
				+ "}";
    	
    	request.setAttribute("data", jsonData);
	}

}
