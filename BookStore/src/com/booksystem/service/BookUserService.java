package com.booksystem.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booksystem.mybatis.entities.Book;
import com.booksystem.mybatis.entities.BookUser;
import com.booksystem.mybatis.mapper.BookMapper;
import com.booksystem.mybatis.mapper.BookUserMapper;
import com.booksystem.tool.PageInfo;
import com.github.pagehelper.PageHelper;

@Service
public class BookUserService {
	
	@Autowired
	private BookUserMapper bookUserMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	
	private BookUser bookUser;
	
	private Book book;
	
	//根据学生id获取所有借阅记录
	public PageInfo<BookUser> getRecordByUserId(String userId,int pageNo){
		PageHelper.startPage(pageNo, 5);
		List<BookUser> list = bookUserMapper.getRecord(userId);
		PageInfo<BookUser> info = new PageInfo<>(list);
		return info;
	}
	
	
	//借书(更新图书数目+添加借书时间)
	@Transactional
	public int lendBook(String userId,String bookId){
		//减少库存,增加借阅次数
		Book tmpBook = bookMapper.selectByPrimaryKey(bookId);
		tmpBook.setBookLend(tmpBook.getBookLend()-1);
		tmpBook.setBookLendtimes(tmpBook.getBookLendtimes());
		//向表中插入数据
		bookUser = new BookUser();
		bookUser.setBookId(bookId);
		bookUser.setUserId(userId);
		bookUser.setIfreturn(1);
		bookUser.setLendtime(new Date());
		if(bookMapper.updateByPrimaryKey(tmpBook)>0&&
				bookUserMapper.insert(bookUser)>0){
			return 0;//成功
		}else{
			return -1;//失败
		}
	}
	
	//还书(添加还书时间+是否归还+更新图书数目)
	@Transactional
	public int returnBook(String userId,String bookId){
		BookUser bookUser = bookUserMapper.selectByPrimaryKey(bookId, userId);
		System.out.println(bookUser);
		bookUser.setReturntime(new Date());
		bookUser.setIfreturn(0);
		bookUser.setBookId(bookId);
		bookUser.setUserId(userId);
		int updateBookUser = bookUserMapper.updateByPrimaryKey(bookUser);
		//修改书籍数目
		book = bookMapper.selectByPrimaryKey(bookId);
		//可借数加1
		book.setBookLend(book.getBookLend()+1);
		//更新
		int updateBook = bookMapper.updateByPrimaryKey(book);
		if(updateBookUser>0&&updateBook>0){
			//成功
			return 1;
		}
		return 0;
	}
	
	//续借(更新续借次数)
	public int renew(int role,String userId,String bookId){
		bookUser=bookUserMapper.selectByPrimaryKey(bookId, userId);
		System.out.println(bookUser);
		System.out.println("userrole:"+role);
		bookUser.setBookId(bookId);
		bookUser.setUserId(userId);
		if(bookUser==null){
			return -1;
		}
		//如果是学生操作
		if(role==1){
			//没有续借过
			if(bookUser.getRenewtimes()==0){
				System.out.println("123456");
				bookUser.setRenewtimes(bookUser.getRenewtimes()+1);
				System.out.println(bookUser);
				int i = bookUserMapper.updateByPrimaryKey(bookUser);
				System.out.println(i);
				return i;
			}else{
				//已经需借过
				return 0;
			}
		}
		//管理员操作
		if(role==0){
			bookUser.setRenewtimes(bookUser.getRenewtimes()+1);
			return bookUserMapper.updateByPrimaryKey(bookUser);
		}
		return 0;
	}
	
	
	
	
	
}
