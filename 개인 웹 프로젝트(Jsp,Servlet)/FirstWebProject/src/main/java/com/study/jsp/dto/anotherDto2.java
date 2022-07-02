package com.study.jsp.dto;

public class anotherDto2
{
	private String id;
	private int wnCount;
	
	public anotherDto2(){}
	public anotherDto2(String id, int wnCount)
	{
		super();
		this.id = id;
		this.wnCount = wnCount;
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
	
}
