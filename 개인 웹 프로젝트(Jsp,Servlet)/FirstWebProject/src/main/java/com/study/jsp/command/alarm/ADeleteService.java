package com.study.jsp.command.alarm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.Service;
import com.study.jsp.dao.AlarmBoardDao;
import com.study.jsp.dto.FreeBoardDto;

public class ADeleteService implements Service
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		
		
		String bId = request.getParameter("bId");
		AlarmBoardDao dao = AlarmBoardDao.getInstance();
		FreeBoardDto dto = dao.contentView(bId);
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id==null) {
			response.setContentType("text/html;charset=euc-kr"); 
            PrintWriter out = response.getWriter(); 
            out.println("<script>"); 
            out.println("alert('로그인 후 시도해주세요.');"); 
            out.println("history.go(-1);");
            out.println("</script>"); 
            out.close(); 
            return;
		}else if(id.equals("admin")) {
			dao.delete(bId);
		}else if(id.equals(dto.getId())) {
			dao.delete(bId);
		}else {
			response.setContentType("text/html;charset=euc-kr"); 
            PrintWriter out = response.getWriter(); 
            out.println("<script>"); 
            out.println("alert('작성자만 삭제 할 수 있습니다..');"); 
            out.println("history.go(-1);");
            out.println("</script>"); 
            out.close(); 
            return;
		}
	}
}

