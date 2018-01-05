package com.booksystem.mybatis.entities;

import java.util.List;

public class UserAndRecord {
	
	//用户名
	private String userName;
	//借阅号
	private String userId;
	//借阅状态
	private int iflend;
	//借阅记录
	private List<BookUser> buList;
	//当前页
	private int pageNum;
	//总页数
	private int pages;
	//当前页记录数
	private int size;
	//下一页
	private int nextPage;
	//上一页
	private int prePage;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getIflend() {
		return iflend;
	}
	public void setIflend(int iflend) {
		this.iflend = iflend;
	}
	public List<BookUser> getBuList() {
		return buList;
	}
	public void setBuList(List<BookUser> buList) {
		this.buList = buList;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	@Override
	public String toString() {
		return "UserAndRecord [userName=" + userName + ", userId=" + userId + ", iflend=" + iflend + ", buList="
				+ buList + ", pageNum=" + pageNum + ", pages=" + pages + ", size=" + size + ", nextPage=" + nextPage
				+ ", prePage=" + prePage + "]";
	}
	public UserAndRecord() {
		super();
	}
	public UserAndRecord(String userName, String userId, int iflend, List<BookUser> buList, int pageNum, int pages,
			int size, int nextPage, int prePage) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.iflend = iflend;
		this.buList = buList;
		this.pageNum = pageNum;
		this.pages = pages;
		this.size = size;
		this.nextPage = nextPage;
		this.prePage = prePage;
	}
	
	
	
	
	
}
