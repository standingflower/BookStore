package com.booksystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booksystem.mybatis.entities.Book;
import com.booksystem.mybatis.mapper.BookMapper;
import com.booksystem.tool.PageInfo;
import com.github.pagehelper.PageHelper;

@Service
public class BookService {
	
	public static final int pageSize=5;
	
	private Book book=null;
	
	private List<Book> listBook;
	
	@Autowired
	private BookMapper bookMapper;
	
	//分页显示，根据借阅降序后的结果
	@Transactional(readOnly=true)
	public PageInfo<Book> getBookByOrder(int pageNo,String order){
		PageHelper.startPage(pageNo, pageSize);
		List<Book> list = bookMapper.selectAllByOrder(order);
		PageInfo<Book> info = new PageInfo<>(list);
		return info;
	}
	
	
	//获取所有图书
	public List<Book> selectAllBook(){
		
		listBook=bookMapper.selectAllByOrder("book_lendtimes");
		
		return listBook;
	}
	
	
	//通过书籍id获取指定的书籍
	@Transactional(readOnly=true)
	public Book getBookByPrimaryKey(String bookId){
		return bookMapper.selectByPrimaryKey(bookId);
	}
	
	
	//根据书籍的类型，降序显示结果，分页显示
	@Transactional(readOnly=true)
	public PageInfo<Book> selectByBookTypes(int pageNo,String type,String value){
		PageHelper.startPage(pageNo, pageSize);
		List<Book> list = bookMapper.selectByBookTypes(type, value);
		PageInfo<Book> info = new PageInfo<>(list);
		return info;
	}
	
	//模糊查询
	@Transactional(readOnly=true)
	public PageInfo<Book> selectBookBySearch(int pageNo,String type,String value){
		PageHelper.startPage(pageNo, pageSize);
		List<Book> list = bookMapper.selectBookBySearch(type, value);
		PageInfo<Book> info = new PageInfo<>(list);
		return info;
	}
	
	//保存
	public int CBook(Book book,String picturePath){
		int i ;
		Book temp = bookMapper.selectByPrimaryKey(book.getBookId());
		//书籍id以存在
		if(temp!=null){
			return 1;
		}else{
			book.setBookPicture(picturePath);
			i = bookMapper.insert(book);
			return i;
		}
	}
	
	//查找书籍
	public Book RBook(String bookId){
		book = bookMapper.selectByPrimaryKey(bookId);
		return book;
	}
	
	//删除书籍
	public int DBook(String bookId){
		int i = bookMapper.deleteByPrimaryKey(bookId);
		return i;
	}
	
	//更新书籍
	public int Ubook(Book book){
		int i = bookMapper.updateByPrimaryKey(book);
		return i;
	}
	
	
	public BookService(){
		System.out.println("bookService构造器");
	}
	
}
