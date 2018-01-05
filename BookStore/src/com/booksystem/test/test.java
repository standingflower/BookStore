package com.booksystem.test;


import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.booksystem.mybatis.entities.Book;
import com.booksystem.mybatis.entities.BookUser;
import com.booksystem.mybatis.mapper.BookMapper;
import com.booksystem.mybatis.mapper.BookUserMapper;
import com.booksystem.service.BookService;
import com.booksystem.service.BookUserService;
import com.booksystem.tool.PageInfo;
import com.booksystem.tool.SelectExcel;

public class test {

	private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

	private BookService bookService=null;
	private BookUserService userBookServic=null;
	
	
	@Test
	public void getBookByOrder() {
		bookService = ioc.getBean(BookService.class);
		
		PageInfo<Book> book = bookService.getBookByOrder(1, "book_lendtimes");
		List<Book> list = book.getList();
		for (Book book2 : list) {
			System.out.println(book2);
			
		}
	}
	
	@Test
	public void getBookByPrimaryKey(){
		bookService = ioc.getBean(BookService.class);
		Book book = bookService.getBookByPrimaryKey("10001");
		System.out.println(book);
	}
	
	@Test
	public void selectByBookTypes(){
		bookService = ioc.getBean(BookService.class);
		PageInfo<Book> info = bookService.selectByBookTypes(1, "book_style", "文学");
		List<Book> list = info.getList();
		for (Book book : list) {
			
			System.out.println(book);
		}
	}
	
	@Test
	public void selectBookBySearch(){
		bookService = ioc.getBean(BookService.class);
		PageInfo<Book> info = bookService.selectBookBySearch(1, "book_publih", "书局");
		List<Book> list = info.getList();
		for (Book book : list) {
			System.out.println(book);
		}
	}
	
	
//	@Test
//	public void getRecordByUserId(){
//		userBookServic = ioc.getBean(BookUserService.class);
//		List<BookUser> list = userBookServic.getRecordByUserId("00001");
//		
//		for (BookUser bookUser : list) {
//			System.out.println(bookUser.getBook().toString());
//			System.out.println(bookUser.getUser().toString());
////			System.out.println(bookUser);
//		}
//		
//	}
	
	@Test
	public void lendBook(){
		userBookServic = ioc.getBean(BookUserService.class);
		System.out.println("123");
		int i = userBookServic.lendBook("00001" , "20001");
		System.out.println(i);
		
		
	}
	

	
	@Test
	public void testRenew(){
		userBookServic = ioc.getBean(BookUserService.class);
		System.out.println("321");
		int i = userBookServic.renew(1, "00001", "10010");
		System.out.println(i);
	}
	
	
	
	@Test
	public void getBookUser(){
		userBookServic = ioc.getBean(BookUserService.class);
		BookUserMapper mapper=ioc.getBean(BookUserMapper.class);
		BookUser bookUser=mapper.selectByPrimaryKey("10010", "00001");
		
		System.out.println(bookUser);
	}
	
	
	@Test
	public void downExcel() throws IOException{
		
		SelectExcel bean = ioc.getBean(SelectExcel.class);
		
		bean.createExcel("D:\\桌面\\中文.xls");
		
		System.out.println("end");
		
		
	}
	
	
}
