package com.study.jsp.command.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.study.jsp.command.Service;
import com.study.jsp.dao.MemberDao;
import com.study.jsp.dto.MemberDto;



public class SelectAllMemberService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		MemberDao dao = MemberDao.getInstance();
		
		ArrayList<MemberDto> dtos = dao.AllMemberList();
		
		String data=null;
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		for(int i =0 ; i<dtos.size() ; i++ ) {
			if( i == (dtos.size()-1)) {
				data = "{\"id\":\""+dtos.get(i).getId()+"\",";
				data += "\"pw\":\"" + dtos.get(i).getPw()+"\",";
				data += "\"name\":\"" +dtos.get(i).getName()+"\",";
				data += "\"rDate\":\""+ dtos.get(i).getrDate()+"\",";
				data += "\"eMail\":\""+ dtos.get(i).geteMail()+"\",";
				data += "\"address\":\"" + dtos.get(i).getAddress()+"\"}]";
				System.out.println(data);
				writer.print(data);
			}else {
				if(i == 0)
				{
					data = "[";
					data += "{\"id\":\""+dtos.get(i).getId()+"\",";
					data += "\"pw\":\"" + dtos.get(i).getPw()+"\",";
					data += "\"name\":\"" +dtos.get(i).getName()+"\",";
					data += "\"rDate\":\""+ dtos.get(i).getrDate()+"\",";
					data += "\"eMail\":\""+ dtos.get(i).geteMail()+"\",";
					data += "\"address\":\"" + dtos.get(i).getAddress()+"\"},";
				}else {
					data = "{\"id\":\""+dtos.get(i).getId()+"\",";
					data += "\"pw\":\"" + dtos.get(i).getPw()+"\",";
					data += "\"name\":\"" +dtos.get(i).getName()+"\",";
					data += "\"rDate\":\""+ dtos.get(i).getrDate()+"\",";
					data += "\"eMail\":\""+ dtos.get(i).geteMail()+"\",";
					data += "\"address\":\"" + dtos.get(i).getAddress()+"\"},";
				}
				System.out.println(data);
				writer.print(data);
			}
		}
		writer.close();
	}
}
