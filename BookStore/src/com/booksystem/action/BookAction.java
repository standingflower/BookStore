package com.booksystem.action;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.booksystem.mybatis.entities.Book;
import com.booksystem.service.BookService;
import com.booksystem.tool.PageInfo;
import com.booksystem.tool.SelectExcel;
import com.opensymphony.xwork2.ActionSupport;

/*
Scope指定Bean的生命周期：
	singleton：IOC容器中仅存在一个Bean实例，Bean以单例的形式存在(默认)
	prototype：每次调用getBean的时候，都会重新创建一个新的Bean
	request：每次HTTP请求都会创建一个新的Bean
	session：同一个HTTP共享一个Bean
*/
@Scope("prototype")
@Controller
public class BookAction extends ActionSupport{
	//分页号
	private String pageNoStr=null;
	private int pageNo=1;
	//根据书籍哪一个属性进行排序
	private String order=null;
	//书籍类型
	private String type="book_name";
	//书籍类型对应的值
	private String value=null;
	
	private String bookId=null;
	
	@Autowired
	private BookService bookService;
	//分页显示的集合
	PageInfo<Book> pageInfo=null;
	private Book book=null;
	
	
	@Autowired
	private SelectExcel selectExcel;
	// 被下载文件的流
	private InputStream inputStream;
	// 被下载文件的类型MIME值
	private String contentType;
	// 被下载文件的大小
	private Long contentLength;
	// contentDisposition响应头的信息
	private String contentDisposition;

	//根据指定条件查询书籍，并且响应指定页面
	public String findbook(){
		System.out.println("sfindbook");
		//默认使用借阅次数排序
		order="book_lendtimes";
		pageInfo = bookService.getBookByOrder(pageNo,order);
		System.out.println("efindbook");
		return "showbook";
	}
	
	
	//下载所有图书到Excel中
	public String downLoadToExcele() throws IOException{
		
		
		String path = ServletActionContext.getServletContext().getRealPath("/file/");
		String fileName="所有图书.xls";
		String realPath=path+fileName;
		
		selectExcel.createExcel(realPath);
		
		inputStream = new FileInputStream(realPath);
		//获取文件类型
		contentType = ServletActionContext.getServletContext().getMimeType(realPath);
		//防止中文乱码
		fileName = new String(fileName.getBytes("gbk"),"iso8859-1");
		contentDisposition = "attachment;filename="+fileName;
		return SUCCESS;
	}
	
	
	
	
	public String findbookByAjax(){
		System.out.println("sfindbookByAjax");
		//默认使用借阅次数排序
		order="book_lendtimes";
		pageInfo = bookService.getBookByOrder(pageNo,order);
		System.out.println(pageInfo);
		System.out.println("efindbookByAjax");
		return "bookajax";
	}
	
	public String search(){
		System.out.println("ssearch");
		order="book_lendtimes";
		pageInfo = bookService.getBookByOrder(pageNo,order);
		System.out.println("esearch");
		return "searchBook";
	}
	
	//根据书籍id号获取指定书籍
	public String findBookByPrimaryKey(){
		System.out.println("sfindBookByPrimaryKey");
		book = bookService.getBookByPrimaryKey(bookId);
		System.out.println("efindBookByPrimaryKey");
		return "bookdetails";
	}
	
	//根据书籍类型，降序显示，分页显示
	public String selectByBookTypes(){
		System.out.println("sselectByBookTypes");
		pageInfo = bookService.selectByBookTypes(pageNo, type, value);
		System.out.println("eselectByBookTypes");
		return "showbook";
	}
	
	//模糊查询
	public String selectBookBySearch(){
		System.out.println("sselectBookBySearch");
		pageInfo = bookService.selectBookBySearch(pageNo, type, value);
		System.out.println("eselectBookBySearch");
		return "showbook";
	}
	
	
	
	
	
	
	
	
	public String getPageNoStr() {
		return pageNoStr;
	}
	public void setPageNoStr(String pageNoStr) {
		System.out.println("123");
		this.pageNoStr = pageNoStr;
		System.out.println("321");
		try {
			pageNo = Integer.parseInt(pageNoStr);
			System.out.println("pageNo1"+pageNo);
		} catch (NumberFormatException e) {
			pageNo=1;
			System.out.println("page2"+pageNo);
		}
	}
	
	public PageInfo<Book> getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo<Book> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}


	public InputStream getInputStream() {
		return inputStream;
	}


	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public Long getContentLength() {
		return contentLength;
	}


	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}


	public String getContentDisposition() {
		return contentDisposition;
	}


	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	
	
	
	
	
}
