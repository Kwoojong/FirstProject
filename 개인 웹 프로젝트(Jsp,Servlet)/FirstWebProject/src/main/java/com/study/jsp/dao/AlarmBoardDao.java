package com.study.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.dto.FreeBoardDto;
import com.study.jsp.dto.anotherDto;
import com.study.jsp.dto.fPageInfo;

public class AlarmBoardDao
{
	private static AlarmBoardDao instance = new AlarmBoardDao();
	DataSource dataSource;
	
	int listCount = 7; //한 페이지당 보여줄 게시물의 갯수
	int pageCount = 3; //하단에 보여줄 페이지 리스트의 갯수.
	
	private AlarmBoardDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static AlarmBoardDao getInstance() {
		return instance;
	}
	
	public void write(String bName, String bTitle, String bContent, String id) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into project_alarm_board " +
						   " (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bLike,id) " +
						   " values " +
						   " (project_alarm_board_seq.nextval, ?, ?, ?, 0, project_alarm_board_seq.currval, 0, 0 ,0, ? )";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, id);
			int rn = pstmt.executeUpdate();
			
			query = "insert into project_alarm_board_reply " +
					   " (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bLike,id) " +
					   " values " +
					   " (project_alarm_board_seq.currval, ?, ?, ?, 0, project_alarm_board_seq.currval, 0, 0 ,0, ? )";
			
			pstmt.close();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, id);
			rn = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con !=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<FreeBoardDto> list(int curPage,String search, String searchSomething) {
		ArrayList<FreeBoardDto> dtos = new ArrayList<FreeBoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart= (curPage-1)*listCount+1;
		int nEnd = (curPage-1)*listCount+listCount;
		
		try {
			
			con = dataSource.getConnection();
			
			if(search== null) {
				String query = "select * "
						+ "  from ( "
						+ "   select rownum num, A.* "
						+ "     from ( "
						+ "        select * "
						+ "         from project_alarm_board where bindent='0' "
						+ "         order by bgroup desc, bstep asc ) A "
						+ "    where rownum <= ? ) B "
						+ "where B.num >= ? ";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				resultSet = pstmt.executeQuery();
				
				while (resultSet.next()) {
					int bId = resultSet.getInt("bId");
					String bName = resultSet.getString("bName");
					String bTitle = resultSet.getString("bTitle");
					String bContent = resultSet.getString("bContent");
					Timestamp bDate = resultSet.getTimestamp("bDate");
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
					int bLike = resultSet.getInt("blike");
					String id = resultSet.getString("id");
					
					FreeBoardDto dto = new FreeBoardDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent,bLike,id);
					dtos.add(dto);
				}
			}else if(search.equals("sTitle")) {
				String query = "select * "
						+ "  from ( "
						+ "   select rownum num, A.* "
						+ "     from ( "
						+ "        select * "
						+ "         from project_alarm_board where bindent='0' and btitle like ? "
						+ "         order by bgroup desc, bstep asc ) A "
						+ "    where rownum <= ? ) B "
						+ "where B.num >= ? ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%"+searchSomething+"%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();
				
				while (resultSet.next()) {
					int bId = resultSet.getInt("bId");
					String bName = resultSet.getString("bName");
					String bTitle = resultSet.getString("bTitle");
					String bContent = resultSet.getString("bContent");
					Timestamp bDate = resultSet.getTimestamp("bDate");
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
					int bLike = resultSet.getInt("blike");
					String id = resultSet.getString("id");
					
					FreeBoardDto dto = new FreeBoardDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent,bLike,id);
					dtos.add(dto);
				}
			}else if(search.equals("sContent")) {
				String query = "select * "
						+ "  from ( "
						+ "   select rownum num, A.* "
						+ "     from ( "
						+ "        select * "
						+ "         from project_alarm_board where bindent='0' and bContent like ? "
						+ "         order by bgroup desc, bstep asc ) A "
						+ "    where rownum <= ? ) B "
						+ "where B.num >= ? ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%"+searchSomething+"%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();
				
				while (resultSet.next()) {
					int bId = resultSet.getInt("bId");
					String bName = resultSet.getString("bName");
					String bTitle = resultSet.getString("bTitle");
					String bContent = resultSet.getString("bContent");
					Timestamp bDate = resultSet.getTimestamp("bDate");
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
					int bLike = resultSet.getInt("blike");
					String id = resultSet.getString("id");
					
					FreeBoardDto dto = new FreeBoardDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent,bLike,id);
					dtos.add(dto);
				}
			}else if(search.equals("sName")) {
				String query = "select * "
						+ "  from ( "
						+ "   select rownum num, A.* "
						+ "     from ( "
						+ "        select * "
						+ "         from project_alarm_board where bindent='0' and bname like ? "
						+ "         order by bgroup desc, bstep asc ) A "
						+ "    where rownum <= ? ) B "
						+ "where B.num >= ? ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%"+searchSomething+"%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();
				
				while (resultSet.next()) {
					int bId = resultSet.getInt("bId");
					String bName = resultSet.getString("bName");
					String bTitle = resultSet.getString("bTitle");
					String bContent = resultSet.getString("bContent");
					Timestamp bDate = resultSet.getTimestamp("bDate");
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
					int bLike = resultSet.getInt("blike");
					String id = resultSet.getString("id");
					
					FreeBoardDto dto = new FreeBoardDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent,bLike,id);
					dtos.add(dto);
				}
			}else if(search.equals("sTitlecontent")) {
				String query = "select * "
						+ "  from ( "
						+ "   select rownum num, A.* "
						+ "     from ( "
						+ "        select * "
						+ "         from project_alarm_board where bindent='0' and (bcontent like ? or btitle like ?) "
						+ "         order by bgroup desc, bstep asc ) A "
						+ "    where rownum <= ? ) B "
						+ "where B.num >= ? ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%"+searchSomething+"%");
				pstmt.setString(2, "%"+searchSomething+"%");
				pstmt.setInt(3, nEnd);
				pstmt.setInt(4, nStart);
				resultSet = pstmt.executeQuery();
				
				while (resultSet.next()) {
					int bId = resultSet.getInt("bId");
					String bName = resultSet.getString("bName");
					String bTitle = resultSet.getString("bTitle");
					String bContent = resultSet.getString("bContent");
					Timestamp bDate = resultSet.getTimestamp("bDate");
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
					int bLike = resultSet.getInt("blike");
					String id = resultSet.getString("id");
					
					FreeBoardDto dto = new FreeBoardDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent,bLike,id);
					dtos.add(dto);
				}
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
	
	public fPageInfo articlePage(int curPage,String search, String searchSomething) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		//총 게시물의 갯수
		int totalCount = 0;
		try {
			
			con = dataSource.getConnection();
			if(search== null) {
				String query = "select count(*) as total from project_alarm_board where bindent='0'";
				pstmt = con.prepareStatement(query);
				resultSet = pstmt.executeQuery();
				
				if(resultSet.next()) {
					totalCount = resultSet.getInt("total");
				}
			}else if(search.equals("sTitle")) {
				String query = "select count(*) as total from project_alarm_board where bindent='0' and btitle like ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%"+searchSomething+"%");
				resultSet = pstmt.executeQuery();
				
				if(resultSet.next()) {
					totalCount = resultSet.getInt("total");
				}
			}else if(search.equals("sContent")) {
				String query = "select count(*) as total from project_alarm_board where bindent='0' and bcontent like ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%"+searchSomething+"%");
				resultSet = pstmt.executeQuery();
				
				if(resultSet.next()) {
					totalCount = resultSet.getInt("total");
				}
			}else if(search.equals("sName")) {
				String query = "select count(*) as total from project_alarm_board where bindent='0' and bname like ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%"+searchSomething+"%");
				resultSet = pstmt.executeQuery();
				
				if(resultSet.next()) {
					totalCount = resultSet.getInt("total");
				}
			}else if(search.equals("sTitlecontent")) {
				String query = "select count(*) as total from project_alarm_board where bindent='0' and (bcontent like ? or btitle like ?) ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%"+searchSomething+"%");
				pstmt.setString(2, "%"+searchSomething+"%");
				resultSet = pstmt.executeQuery();
				
				if(resultSet.next()) {
					totalCount = resultSet.getInt("total");
				}
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
		
		//총 페이지 수 
		int totalPage = totalCount / listCount;
		if (totalCount % listCount > 0)
		    totalPage++;

		//현재 페이지
		// curPage = 현재 보고 있는 페이지
		int myCurPage = curPage;
		if (myCurPage > totalPage)
			myCurPage = totalPage;
		if (myCurPage < 1)
			myCurPage = 1;
		
		//시작 페이지
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		// +1 은 첫 페이지가 0이나 10이 아니라 1이나 11로 하기 위함임
		
		//끝페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage)
			endPage = totalPage;
		// -1 은  첫 페이지가 0이나 10이 아니라 1이나 11로 하기 위함임

		fPageInfo pinfo = new fPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public FreeBoardDto contentView(String strID) {
		upHit(strID);
		
		FreeBoardDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from project_alarm_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int bLike = resultSet.getInt("bLike");
				String id = resultSet.getString("id");
				
				dto = new FreeBoardDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent,bLike,id);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public void delete(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from project_alarm_board where bGroup=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			
			int rn = pstmt.executeUpdate();
			pstmt.close();
			query = "delete from project_alarm_board_reply where bGroup=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			
			rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2){ 
				e2.printStackTrace();
				
			}
		}
	}
	
	public void modify(String bId, String bName, String bTitle, String bContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update project_alarm_board set bName = ?, bTitle=?, bContent=? where bId=?";
			
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bId));
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	private void upHit (String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update project_alarm_board set bHit= bHit + 1 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2){ 
				e2.printStackTrace();
				
			}
		}
	}
	
	private void downHit (String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update project_alarm_board set bHit= bHit -1 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2){ 
				e2.printStackTrace();
				
			}
		}
	}
	
	public FreeBoardDto reply_view(String str) {
		FreeBoardDto dto =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			String query = "select * from project_alarm_board where bId=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int bLike = resultSet.getInt("bLike");
				String id = resultSet.getString("id");
				
				dto = new FreeBoardDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent,bLike,id);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2){ 
				e2.printStackTrace();
				
			}
		}
		
		return dto;
	}
	
	public void reply(String bId,String bName, String bTitle, String bContent,
						String bGroup, String bStep, String bIndent, String id)
	{
		replyShape(bGroup, bStep);
		downHit(bId);
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into project_alarm_board_reply " +
						   " (bId, bName, bTitle, bContent, bGroup, bStep, bIndent, id) " +
						   " values (project_alarm_board_reply_seq.nextval,?,?,?,?,?,?,?) ";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep)+1);
			pstmt.setInt(6, Integer.parseInt(bIndent)+1);
			pstmt.setString(7, id);
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2){ 
				e2.printStackTrace();
				
			}
		}
	}
	
	private void replyShape(String strGroup, String strStep) 
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update project_alarm_board_reply set bStep = bStep+1 where bGroup = ? and bStep > ?";
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(1, Integer.parseInt(strStep));
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2){ 
				e2.printStackTrace();
				
			}
		}
	}
	
	public ArrayList<FreeBoardDto> replylist(String rlbGroup) {
		ArrayList<FreeBoardDto> dtos = new ArrayList<FreeBoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from project_alarm_board_reply where bindent ^= 0 and bgroup = ? order by bdate asc";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, rlbGroup);
			
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int bLike = resultSet.getInt("blike");
				String id = resultSet.getString("id");
				FreeBoardDto dto = new FreeBoardDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent,bLike,id);
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
	
	public void upLike (String bId) {
		downHit(bId);
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update project_alarm_board set bLike = bLike + 1 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2){ 
				e2.printStackTrace();
				
			}
		}
	}
	
	
	public ArrayList<anotherDto> replycount(int curPage, String search, String searchSomething) {
		ArrayList<anotherDto> dto = new ArrayList<anotherDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet1 = null;
		
		int nStart= (curPage-1)*listCount+1;
		int nEnd = (curPage-1)*listCount+listCount;
		
		try {
			con = dataSource.getConnection();
			
			if(search== null) {
				System.out.println("null 일때 dao 에서 search : " + search );
				String query = "select * from (  select rownum num, A.*  from "
						+ " (  select bgroup, count(*)-1 from project_alarm_board_reply group by bgroup order by bgroup desc ) "
						+ " A  where rownum <= ? ) B where B.num >= ?";
				
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				resultSet = pstmt.executeQuery();
				
				String query1 = "select *  from (  select rownum num, A.*  from "
						+ "(  select sysdate-bdate from project_alarm_board where bindent='0' order by bgroup desc, bstep asc ) A "
						+ " where rownum <= ? ) B where B.num >= ?";
				
				pstmt1 = con.prepareStatement(query1);
				pstmt1.setInt(1, nEnd);
				pstmt1.setInt(2, nStart);
				resultSet1 = pstmt1.executeQuery();
				
				while (resultSet.next()) {
					int replycount = resultSet.getInt(3);
					if(resultSet1.next()) {
						double latestpost = resultSet1.getDouble(2);
						System.out.println("latestpost" + latestpost);
						anotherDto rc = new anotherDto(replycount, latestpost);
						dto.add(rc);
					}
					
				}
			}else if(search.equals("sTitle")) {
				System.out.println("sTitle 일때 dao 에서 search : " + search );
				String query = "select * from (  select rownum num, A.*  from "
						+ " (  select bgroup, count(*)-1 from project_alarm_board_reply where btitle like ? group by bgroup order by bgroup desc ) "
						+ " A  where rownum <= ? ) B where B.num >= ?";
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%"+searchSomething+"%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();
				
				String query1 = "select *  from (  select rownum num, A.*  from "
						+ "(  select sysdate-bdate from project_alarm_board where bindent='0' and btitle like ? order by bgroup desc, bstep asc ) A "
						+ " where rownum <= ? ) B where B.num >= ?";
				
				pstmt1 = con.prepareStatement(query1);
				pstmt1.setString(1, "%"+searchSomething+"%");
				pstmt1.setInt(2, nEnd);
				pstmt1.setInt(3, nStart);
				resultSet1 = pstmt1.executeQuery();
				
				while (resultSet.next()) {
					int replycount = resultSet.getInt(3);
					if(resultSet1.next()) {
						double latestpost = resultSet1.getDouble(2);
						System.out.println("latestpost" + latestpost);
						anotherDto rc = new anotherDto(replycount, latestpost);
						dto.add(rc);
					}
					
				}
			}else if(search.equals("sContent")) {
				
				System.out.println("sContent 일때 dao 에서 search : " + search );
				
				String query = "select * from (  select rownum num, A.*  from "
						+ " (  select bgroup, count(*)-1 from project_alarm_board_reply where bhit like ? group by bgroup order by bgroup desc ) "
						+ " A  where rownum <= ? ) B where B.num >= ?";
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%"+searchSomething+"%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();
				
				String query1 = "select *  from (  select rownum num, A.*  from "
						+ "(  select sysdate-bdate from project_alarm_board where bindent='0' and bcontent like ? order by bgroup desc, bstep asc ) A "
						+ " where rownum <= ? ) B where B.num >= ?";
				
				pstmt1 = con.prepareStatement(query1);
				pstmt1.setString(1, "%"+searchSomething+"%");
				pstmt1.setInt(2, nEnd);
				pstmt1.setInt(3, nStart);
				resultSet1 = pstmt1.executeQuery();
				
				while (resultSet.next()) {
					int replycount = resultSet.getInt(3);
					if(resultSet1.next()) {
						double latestpost = resultSet1.getDouble(2);
						System.out.println("latestpost" + latestpost);
						anotherDto rc = new anotherDto(replycount, latestpost);
						dto.add(rc);
					}
					
				}
			}else if(search.equals("sName")) {
				
				System.out.println("sName 일때 dao 에서 search : " + search );
				String query = "select * from (  select rownum num, A.*  from "
						+ " (  select bgroup, count(*)-1 from project_alarm_board_reply where bname like ? group by bgroup order by bgroup desc ) "
						+ " A  where rownum <= ? ) B where B.num >= ?";
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%"+searchSomething+"%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();
				
				String query1 = "select *  from (  select rownum num, A.*  from "
						+ "(  select sysdate-bdate from project_alarm_board where bindent='0' and bname like ? order by bgroup desc, bstep asc ) A "
						+ " where rownum <= ? ) B where B.num >= ?";
				
				pstmt1 = con.prepareStatement(query1);
				pstmt1.setString(1, "%"+searchSomething+"%");
				pstmt1.setInt(2, nEnd);
				pstmt1.setInt(3, nStart);
				resultSet1 = pstmt1.executeQuery();
				
				while (resultSet.next()) {
					int replycount = resultSet.getInt(3);
					if(resultSet1.next()) {
						double latestpost = resultSet1.getDouble(2);
						System.out.println("latestpost" + latestpost);
						anotherDto rc = new anotherDto(replycount, latestpost);
						dto.add(rc);
					}
					
				}
			}else if(search.equals("sTitlecontent")) {
				System.out.println("sTitlecontent 일때 dao 에서 search : " + search );
				String query = "select * from (  select rownum num, A.*  from "
						+ " (  select bgroup, count(*)-1 from project_alarm_board_reply where bhit like ? group by bgroup order by bgroup desc ) "
						+ " A  where rownum <= ? ) B where B.num >= ?";
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%"+searchSomething+"%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();
				
				String query1 = "select *  from (  select rownum num, A.*  from "
						+ "(  select sysdate-bdate from project_alarm_board where bindent='0' and (bcontent like ? or btitle like ?) order by bgroup desc, bstep asc ) A "
						+ " where rownum <= ? ) B where B.num >= ?";
				
				pstmt1 = con.prepareStatement(query1);
				pstmt1.setString(1, "%"+searchSomething+"%");
				pstmt1.setString(2, "%"+searchSomething+"%");
				pstmt1.setInt(3, nEnd);
				pstmt1.setInt(4, nStart);
				resultSet1 = pstmt1.executeQuery();
				
				while (resultSet.next()) {
					int replycount = resultSet.getInt(3);
					if(resultSet1.next()) {
						double latestpost = resultSet1.getDouble(2);
						System.out.println("latestpost" + latestpost);
						anotherDto rc = new anotherDto(replycount, latestpost);
						dto.add(rc);
					}
					
				}
			}
			
		}catch(Exception e) {
			
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2){ 
				e2.printStackTrace();
				
			}
		}return dto;
	}
	
	public void replyDelete(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			
			
			String query = "delete from project_alarm_board_reply where bId=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2){ 
				e2.printStackTrace();
				
			}
		}
	}
	
}
