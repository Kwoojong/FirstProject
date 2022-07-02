package com.study.jsp.command.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.command.Service;
import com.study.jsp.dao.FileDAO;
import com.study.jsp.dto.FileDto;

public class DownloadViewService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bId = request.getParameter("bId");
		FileDAO dao = FileDAO.getInstance();
		String dto= dao.downloadView(bId);
		
		request.setAttribute("downloadview", dto);
		
		
	}
}

