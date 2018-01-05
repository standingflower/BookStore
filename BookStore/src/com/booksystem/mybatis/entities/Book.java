package com.booksystem.mybatis.entities;

public class Book {
    private String bookId;

    private String bookName;

    private String bookAuthor;

    private String bookPublih;

    private String bookIsbn;

    private String bookBrief;

    private Double bookPrice;

    private int bookStore;

    private int bookLend;

    private int bookLose;

    private String bookPicture;

    private String bookStyle;

    private int bookLendtimes;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
    }

    public String getBookPublih() {
        return bookPublih;
    }

    public void setBookPublih(String bookPublih) {
        this.bookPublih = bookPublih == null ? null : bookPublih.trim();
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn == null ? null : bookIsbn.trim();
    }

    public String getBookBrief() {
        return bookBrief;
    }

    public void setBookBrief(String bookBrief) {
        this.bookBrief = bookBrief == null ? null : bookBrief.trim();
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookStore() {
        return bookStore;
    }

    public void setBookStore(int bookStore) {
        this.bookStore = bookStore;
    }

    public int getBookLend() {
        return bookLend;
    }

    public void setBookLend(int bookLend) {
        this.bookLend = bookLend;
    }

    public int getBookLose() {
        return bookLose;
    }

    public void setBookLose(int bookLose) {
        this.bookLose = bookLose;
    }

    public String getBookPicture() {
        return bookPicture;
    }

    public void setBookPicture(String bookPicture) {
        this.bookPicture = bookPicture == null ? null : bookPicture.trim();
    }

    public String getBookStyle() {
        return bookStyle;
    }

    public void setBookStyle(String bookStyle) {
        this.bookStyle = bookStyle == null ? null : bookStyle.trim();
    }

    public int getBookLendtimes() {
        return bookLendtimes;
    }

    public void setBookLendtimes(int bookLendtimes) {
        this.bookLendtimes = bookLendtimes;
    }

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookPublih="
				+ bookPublih + ", bookIsbn=" + bookIsbn + ", bookBrief=" + bookBrief + ", bookPrice=" + bookPrice
				+ ", bookStore=" + bookStore + ", bookLend=" + bookLend + ", bookLose=" + bookLose + ", bookPicture="
				+ bookPicture + ", bookStyle=" + bookStyle + ", bookLendtimes=" + bookLendtimes + "]";
	}

	public Book(String bookId, String bookName, String bookAuthor, String bookPublih, String bookIsbn, String bookBrief,
			Double bookPrice, int bookStore, int bookLend, int bookLose, String bookPicture,
			String bookStyle, int bookLendtimes) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookPublih = bookPublih;
		this.bookIsbn = bookIsbn;
		this.bookBrief = bookBrief;
		this.bookPrice = bookPrice;
		this.bookStore = bookStore;
		this.bookLend = bookLend;
		this.bookLose = bookLose;
		this.bookPicture = bookPicture;
		this.bookStyle = bookStyle;
		this.bookLendtimes = bookLendtimes;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}