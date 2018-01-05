package com.booksystem.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.booksystem.mybatis.entities.Book;
import com.booksystem.mybatis.entities.BookUser;
import com.booksystem.mybatis.entities.User;
import com.booksystem.mybatis.entities.UserAndRecord;
import com.booksystem.service.BookService;
import com.booksystem.service.BookUserService;
import com.booksystem.service.UserService;
import com.booksystem.tool.PageInfo;
import com.opensymphony.xwork2.ActionSupport;


@Scope("prototype")
@Controller
public class BookUserAction extends ActionSupport implements SessionAware,RequestAware{
	
	private static final long serialVersionUID = -8572439312372621196L;
	@Autowired
	private BookUserService bookUserService;
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	
	
	private Map<String,Object> session;
	
	private Map<String,Object> request;
	
	private User user;
	
	private String bookId;
	
	private int pageNo=1;
	
	private String pageNoStr;
	
	//操作结果
	private String result;
	
	//续借返回结果
	private int resjson;
	
	private UserAndRecord userAndRecord;
	
	
	//管理员通过输入学生id查询借阅记录，并且获取用户基本信息
	public String selectRecordByUserIdByAjax(){
		System.out.println("sselectRecordByUserIdByAjax");
		//获取学生
		user = userService.getUser(user.getUserId());
		if(user==null){
			resjson=-1;
		}else{
			System.out.println(user);
			//获取借阅记录
			PageInfo<BookUser> info = bookUserService.getRecordByUserId(user.getUserId(), pageNo);
			//返回需要的数据
			userAndRecord = new UserAndRecord();
			userAndRecord.setUserId(user.getUserId());
			userAndRecord.setUserName(user.getUserName());
			userAndRecord.setIflend(user.getIflend());
			userAndRecord.setBuList(info.getList());
			userAndRecord.setPageNum(info.getPageNum());
			userAndRecord.setPages(info.getPages());
			userAndRecord.setSize(info.getSize());
			userAndRecord.setNextPage(info.getNextPage());
			userAndRecord.setPrePage(info.getPrePage());
			System.out.println("eselectRecordByUserIdByAjax");
		}
		return "resjson";
	}
	
	//根据学生id获取记录
	public String selectRecordByUserId(){
		System.out.println("sselectRecordByUserId");
		System.out.println("12:"+"getRecordByUserId");
		user = (User) session.get("user");
		System.out.println("qwee:"+user);
		PageInfo<BookUser> info = bookUserService.getRecordByUserId(user.getUserId(),pageNo);
		request.put("info", info);
		System.out.println("eselectRecordByUserId");
		return "userdetails";
	}
	
	//续借操作(通过json操作),学生操作
	public String renew(){
		System.out.println("srenew");
		System.out.println("qwer");
		System.out.println(user);
		System.out.println(bookId);
		//判断用户是否可借
		User tmpUser = userService.getUser(user.getUserId());
		if(tmpUser==null){
			resjson=-2;//未找到指定用户
		}else{
			if(tmpUser.getIflend()==0){
				//获取对象角色
				User tmp=(User)session.get("user");
				int res = bookUserService.renew(tmp.getUserRole(), user.getUserId(), bookId);
				System.out.println(res);
				//续借失败
				if(res==0){
					//当前用户已续借(用户自己续借)
					resjson=0;
				}
				if(res>0){
					//续借成功
					resjson=1;
				}
				if(res<0){
					resjson=-1;//续借失败
				}
				
			}else{
				resjson=2;//当前用户不能续借
			}
		}
		
		
		System.out.println("erenew");
		return "resultJson";
	}
	
//	//续借，管理员操作
//	public String renewByAdmin(){
//		System.out.println();
//		//从session中获取user
//		User tmp=(User) session.get("user");
//		int res = bookUserService.renew(tmp.getUserRole(), user.getUserId(), bookId);
//		if(res>0){
//			result=getText("renew.success");//续借失败
//		}else{
//			result=getText("renew.error");
//		}
//		return "manage";
//	}
	
	
	//借书
	public String lendBook(){
		System.out.println("slendBook");
		System.out.println(user);
		System.out.println(bookId);
		//书籍Id，用户id，结束时间，是否可借
		//根据用户id获取USer，判断是否可借
		User tmpUser = userService.getUser(user.getUserId());
		System.out.println(tmpUser);
		if(tmpUser==null){
			//不存在
			resjson=2;
		}else{
			if(tmpUser.getIflend()==0){
				//可借
				//根据书籍id获取图书，判断是否可借
				Book tmpBook = bookService.getBookByPrimaryKey(bookId);
				if(tmpBook.getBookStore()>tmpBook.getBookLend()){
					//库存大于借出数(可借)
					int i = bookUserService.lendBook(user.getUserId(), bookId);
					if(i==0){
						resjson=1;//借阅成功
					}else{
						resjson=-2;//借阅失败
					}
				}else{
					//所有书籍都被借出
					resjson=0;
				}
			}else{
				//不可借
				resjson=-1;
			}
		}
		
		
		
		
		System.out.println("elendBook");
		return "resultJson";
	}
	
	
	
	
	//还书
	public String returnBook(){
		System.out.println("sreturnBook");
		User tmpUser = userService.getUser(user.getUserId());
		
		if(tmpUser==null){
			resjson=1;
		}else{
			int res=bookUserService.returnBook(user.getUserId(), bookId);
			if(res>0){
				resjson=0;//还书成功
			}
			if(res==0){
				resjson=-1;//还书失败
			}
		}
		
		
		System.out.println("ereturnBook");
		
		return "resultJson";
	}
	
	
	
	
	
	

	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
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


	public String getBookId() {
		return bookId;
	}


	public void setBookId(String bookId) {
		this.bookId = bookId;
	}


	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request=arg0;
	}

	
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		System.out.println("setuser");
		this.user = user;
	}




	public UserAndRecord getUserAndRecord() {
		return userAndRecord;
	}
	public void setUserAndRecord(UserAndRecord userAndRecord) {
		this.userAndRecord = userAndRecord;
	}

	public int getResjson() {
		return resjson;
	}

	public void setResjson(int resjson) {
		this.resjson = resjson;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
	

}
