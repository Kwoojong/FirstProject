package com.study.jsp.command;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.MemberDao;
import com.study.jsp.dto.MemberDto;



public class JoinService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		
		MemberDto dto = new MemberDto();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.seteMail(eMail);
		dto.setAddress(address);
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		
		MemberDao dao = MemberDao.getInstance();
		
		String jsonData;
		if(dao.confirmId(dto.getId()) == MemberDao.MEMBER_EXISTENT){
			
			jsonData = "{\"code\":\"fail\",\"desc\":\"아이디가 이미 존재합니다.\"}";
		}else{
			int ri = dao.insertMember(id,pw,name,eMail,address);
			if(ri == MemberDao.MEMBER_JOIN_SUCCESS) {
				HttpSession session = request.getSession();
				session.setAttribute("id", dto.getId());
				jsonData = "{"
						+ "\"code\":\"success\","
						+ "\"desc\":\"회원가입되었습니다.\""
						+ "}";
			}else {
				jsonData = "{"
						+ "\"code\":\"fail\","
						+ "\"desc\":\"에러가 발생하여 회원가입에 실패했습니다.\""
						+ "}";
			}
		}	
		
		request.setAttribute("data", jsonData);
	}
}
