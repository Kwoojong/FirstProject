package com.study.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.dto.FileDto;
import com.study.jsp.dto.FreeBoardDto;



public class FileDAO
{
	private static FileDAO instance = new FileDAO();
	DataSource dataSource;
	
	
	public FileDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static FileDAO getInstance() {
		return instance;
	}
	
	
	
	public int upload(String fileName, String fileRealName) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;	
			System.out.println(fileName);
			System.out.println(fileRealName);
		try {
			con = dataSource.getConnection();
			
			
			
			
			
			String query = "insert into database_file values(?,?, project_info_board_seq.nextval )";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, fileName);
			pstmt.setString(2, fileRealName);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}
	
	public String downloadView(String bId) {
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;	
		String fileName = null;
		int nbId = Integer.parseInt(bId);
		try {
			con = dataSource.getConnection();
			String query = "select * from database_file where bId=? ";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nbId-1);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				fileName = resultSet.getString("fileName");
				String fileRealName = resultSet.getString("fileRealName");
				
			}
					
		}catch(Exception e){
			e.printStackTrace();
		}
		return fileName;
		
		
	}
		
}
