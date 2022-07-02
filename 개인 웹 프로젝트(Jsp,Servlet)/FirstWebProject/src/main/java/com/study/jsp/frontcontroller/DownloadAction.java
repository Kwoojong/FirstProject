package com.study.jsp.frontcontroller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DownloadAction")
public class DownloadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("겟입니다");
		String fileName = request.getParameter("file");
		System.out.println("파일네임 : " +fileName);
		
		String directory = this.getServletContext().getRealPath("/fileFolder/");
		File file = new File(directory + "/" + fileName);
		
		System.out.println("파일경로 : "+ file);
		
		String mimeType = getServletContext().getMimeType(file.toString());
		if(mimeType == null) {
			response.setContentType("application/octet-stream");
		}
		
		String downloadName =null;
		if(request.getHeader("user-agent").indexOf("MSIE") == -1) {
			
			downloadName = new String(fileName.getBytes("UTF-8"), "8859_1");
			
		}else {
			downloadName = new String(fileName.getBytes("EUC-KR"), "8859_1");
		}
		
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ downloadName + "\";");
		
		FileInputStream fileInputStream = new FileInputStream(file);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		byte b[] = new byte[1024];
		int data = 0;
		
		while((data = (fileInputStream.read(b, 0 , b.length))) != -1) {
			servletOutputStream.write(b, 0 , data);
		}
		
		servletOutputStream.flush();
		servletOutputStream.close();
		fileInputStream.close();
	}

	

}
