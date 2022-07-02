package com.study.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.dto.MemberDto;

public class MemberDao
{
	
	private static MemberDao instance = new MemberDao();
	DataSource dataSource;
	
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS= 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	private MemberDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	public int insertMember(String id, String pw, String name, String eMail,String address){
		int ri=0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into projectmember values (?,?,?,?, sysdate ,?,sysdate)";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, eMail);
			pstmt.setString(5, address);
			pstmt.executeUpdate();
			ri = MemberDao.MEMBER_JOIN_SUCCESS;
		}catch(Exception e) {	
			e.printStackTrace();
		}finally
		{
			try {
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int confirmId(String id) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from projectmember where id= ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()) {
				ri = MemberDao.MEMBER_EXISTENT;
			}else {
				ri = MemberDao.MEMBER_NONEXISTENT;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int userCheck(String id, String pw) {
		int ri=0;
		String dbPw;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pw from projectmember where id = ?";
		
		try {
			con=dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dbPw = set.getString("pw");
				if(dbPw.equals(pw)) {
					System.out.println("login ok");
					ri = MemberDao.MEMBER_LOGIN_SUCCESS; //로그인 ok
				}else {
					System.out.println("login fail");
					ri = MemberDao.MEMBER_LOGIN_PW_NO_GOOD; //비밀번호 x
					
				}
			}else {
				System.out.println("login fail");
				ri = MemberDao.MEMBER_LOGIN_IS_NOT; //아이디x
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public MemberDto getMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from projectmember where id = ?";
		MemberDto dto = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dto = new MemberDto();
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.seteMail(set.getString("eMail"));
				dto.setrDate(set.getTimestamp("rDate"));
				dto.setAddress(set.getString("address"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				set.close();
				pstmt.close();
				con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public int updateMember(MemberDto dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update projectmember set pw=?, eMail=?, address=? where id=?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.geteMail());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getId());
			ri = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int deleteMember(String id, String pw) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete projectmember where id=? and pw=?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ri = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public ArrayList<MemberDto> AllMemberList(){
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			String query = "select * from projectmember";
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				String id = resultSet.getString(1);
				String pw = resultSet.getString(2);
				String name = resultSet.getString(3);
				String eMail = resultSet.getString(4);
				Timestamp rDate = resultSet.getTimestamp(5);
				String address = resultSet.getString(6);
				
				MemberDto dto = new MemberDto(id,pw,name,eMail,rDate,address);
				dtos.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
			
		return dtos;	
	}
	
	public int stopMember(String stopDate, String id) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update projectmember set bdate= sysdate + ? where id= ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, stopDate);
			pstmt.setString(2, id);
			
			 
			return pstmt.executeUpdate();
 		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return -1;
	}
	
	public Timestamp blackCheck(String id) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		System.out.println(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.DATE, -1);
		time.setTime(cal.getTime().getTime());
		System.out.println(time);
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String query = "select bdate from projectmember where id= ? ";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			resultSet = pstmt.executeQuery();
			if(resultSet.next()) {
				
				return resultSet.getTimestamp(1);	
			}
 		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				pstmt.close();
				con.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		} return time ;
	}
	
	public int banMember(String id) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete projectmember where id = ? ";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ri= pstmt.executeUpdate();
			System.out.println(ri);
			return ri;
 		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return -1;
	}
}
