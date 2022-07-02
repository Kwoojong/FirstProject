package com.study.jsp.dto;

import java.sql.Timestamp;

public class FreeBoardDto
{
	private int bId; //게시글번호
	private String bName; // 글쓴이
	private String bTitle; // 글제목
	private String bContent; //내용
	private Timestamp bDate; //글 쓴 시간
	private int bHit; // 조회수
	private int bGroup; 
	private int bStep;
	private int bIndent; 
	private int bLike; //좋아요
	private String id; //글쓴사람 id
	
	
	public FreeBoardDto() {
		
	}
	
	public FreeBoardDto(int bId, String bName, String bTitle, String bContent, Timestamp bDate, int bHit, int bGroup,
			int bStep, int bIndent, int bLike, String id)
	{
		super();
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
		this.bLike = bLike;
		this.id = id;
		
	}
	
	public int getbId()
	{
		return bId;
	}
	public void setbId(int bId)
	{
		this.bId = bId;
	}
	public String getbName()
	{
		return bName;
	}
	public void setbName(String bName)
	{
		this.bName = bName;
	}
	public String getbTitle()
	{
		return bTitle;
	}
	public void setbTitle(String bTitle)
	{
		this.bTitle = bTitle;
	}
	public String getbContent()
	{
		return bContent;
	}
	public void setbContent(String bContent)
	{
		this.bContent = bContent;
	}
	public Timestamp getbDate()
	{
		return bDate;
	}
	public void setbDate(Timestamp bDate)
	{
		this.bDate = bDate;
	}
	public int getbHit()
	{
		return bHit;
	}
	public void setbHit(int bHit)
	{
		this.bHit = bHit;
	}
	public int getbGroup()
	{
		return bGroup;
	}
	public void setbGroup(int bGroup)
	{
		this.bGroup = bGroup;
	}
	public int getbStep()
	{
		return bStep;
	}
	public void setbStep(int bStep)
	{
		this.bStep = bStep;
	}
	public int getbIndent()
	{
		return bIndent;
	}
	public void setbIndent(int bIndent)
	{
		this.bIndent = bIndent;
	}
	public int getbLike()
	{
		return bLike;
	}
	public void setbLike(int bLike)
	{
		this.bLike = bLike;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}


	
	
}
