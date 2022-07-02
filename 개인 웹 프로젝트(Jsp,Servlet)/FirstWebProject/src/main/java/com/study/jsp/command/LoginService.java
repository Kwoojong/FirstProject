package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.MemberDao;
import com.study.jsp.dto.MemberDto;

public class LoginService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String jsonData = null;
		MemberDao dao = MemberDao.getInstance();
		
	    int checkNum = dao.userCheck(id, pw);
	    if(checkNum == -1){
	    	jsonData = "{"
					+ "\"code\":\"fail\","
					+ "\"desc\":\"아이디가 존재하지않습니다.\""
					+ "}";
	    }else if(checkNum == 0){
	    	jsonData = "{"
					+ "\"code\":\"fail\","
					+ "\"desc\":\"비밀번호가 틀립니다.\""
					+ "}";
	    }else if(checkNum == 1){
	    	MemberDto dto = dao.getMember(id);
	    	
	    	HttpSession session = request.getSession();
	    	String name = dto.getName();
	    	session.setAttribute("id", id);
	    	session.setAttribute("name", name);
	    	session.setAttribute("ValidMem", "yes");
	    	
	    	jsonData = "{"
					+ "\"code\":\"success\","
					+ "\"desc\":\"로그인 성공\""
					+ "}";
	    }
	    request.setAttribute("data", jsonData);
	}
}
