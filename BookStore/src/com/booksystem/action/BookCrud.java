package com.booksystem.action;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.booksystem.mybatis.entities.Book;
import com.booksystem.service.BookService;
import com.opensymphony.xwork2.ActionSupport;


@Scope("prototype")
@Controller
public class BookCrud extends ActionSupport {

	private Book book = null;
	private File picture;
	private String pictureFileName;
	private String pictureContentType;
	
	
	private Book tmp;
	//返回结果，操作是否成功
	private String res;

	@Autowired
	private BookService bookService;
	

	//添加图书
	public String Cbook() throws IOException {
		System.out.println("Cbook");
		//System.out.println(picture.length());
		//System.out.println(pictureFileName);
		//System.out.println(pictureContentType);
		//先判断id是否被占用
		tmp = bookService.getBookByPrimaryKey(book.getBookId());
		System.out.println(tmp);
		if(tmp==null){
			UUID uuid=UUID.randomUUID();
			//将封面保存，再保存图片路径到数据库
			String path=ServletActionContext.getServletContext().getRealPath("/file");
			//保存到硬盘的路径
			String realPath=path+"/img/bookimg/"+uuid+"-"+pictureFileName;;
			//保存到数据库路径
			String databasePath="file/img/bookimg/"+uuid+"-"+pictureFileName;
			int i = bookService.CBook(book,databasePath);
			System.out.println(book);
			System.out.println(picture.length());
			System.out.println(pictureFileName);
			System.out.println(pictureContentType);
			if(i>0){
				//保存书籍成功，保存图片
				FileUtil.copyFile(picture, new File(realPath));
				res="添加成功！";
			}else{
				res="添加失败！";
			}
		}else{
			res="id以存在！";
		}
		return "crud";
	}
	
	//查询图书
	public String Rbook() {
		System.out.println("Rbook");
		tmp=bookService.getBookByPrimaryKey(book.getBookId());
		if(tmp==null){
			res="未找到指定书籍";
			return "crud";
		}else{
			book = bookService.RBook(book.getBookId());
			return "crud";
		}
	}

	//更新图书
	public String Ubook() {
		System.out.println("Ubook");
		//判断是有指定的书
		tmp=bookService.getBookByPrimaryKey(book.getBookId());
		
		if(tmp==null){
			res="未找到指定书籍";
			return "crud";
		}else{
			int i = bookService.Ubook(book);
			if(i>0){
				res="更新成功";
				return "crud";
			}else{
				res="更新失败";
				return "crud";
			}
		}
	}

	//删除图书
	public String Dbook() {
		System.out.println("Dbook");
		tmp=bookService.getBookByPrimaryKey(book.getBookId());
		if(tmp==null){
			res="此书籍不存在";
			return "crud";
		}else{
			int i = bookService.DBook(book.getBookId());
			if(i>0){
				res="删除成功";
				return "crud";
			}else{
				res="删除失败！";
				return "crud";
			}
		}
		
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
	
	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}
	
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}

}
