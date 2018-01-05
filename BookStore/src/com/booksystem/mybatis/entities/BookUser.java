package com.booksystem.mybatis.entities;

import java.util.Date;

public class BookUser {
    private Book book;
    
    private String bookId;

    private User user;
    
    private String userId;

    private Date lendtime;

    private Date returntime;

    private int ifreturn;

    private int renewtimes;

    
    public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getLendtime() {
        return lendtime;
    }

    public void setLendtime(Date lendtime) {
        this.lendtime = lendtime;
    }

    public Date getReturntime() {
        return returntime;
    }

    public void setReturntime(Date returntime) {
        this.returntime = returntime;
    }

    

    public int getIfreturn() {
		return ifreturn;
	}

	public void setIfreturn(int ifreturn) {
		this.ifreturn = ifreturn;
	}

	public int getRenewtimes() {
        return renewtimes;
    }

    public void setRenewtimes(int renewtimes) {
        this.renewtimes = renewtimes;
    }

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "BookUser [book=" + book + ", user=" + user + ", lendtime=" + lendtime + ", returntime=" + returntime
				+ ", ifreturn=" + ifreturn + ", renewtimes=" + renewtimes + "]";
	}
	
	

	public BookUser(Book book, User user, Date lendtime, Date returntime, int ifreturn, int renewtimes) {
		super();
		this.book = book;
		this.user = user;
		this.lendtime = lendtime;
		this.returntime = returntime;
		this.ifreturn = ifreturn;
		this.renewtimes = renewtimes;
	}

	public BookUser() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}