package com.study.jsp.dto;

public class anotherDto
{
	private int replycount;
	private double latestpost;
	private String id;
	private int wnCount;
	
	public anotherDto(){}
	
	
	public anotherDto(int replycount, double latestpost)
	{
		super();
		this.replycount = replycount;
		this.latestpost = latestpost;
	}
	
	

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public int getWnCount()
	{
		return wnCount;
	}

	public void setWnCount(int wnCount)
	{
		this.wnCount = wnCount;
	}

	public int getReplycount()
	{
		return replycount;
	}

	public void setReplycount(int replycount)
	{
		this.replycount = replycount;
	}

	public double getLatestpost()
	{
		return latestpost;
	}

	public void setLatestpost(double latestpost)
	{
		this.latestpost = latestpost;
	}
	
	
	
}
