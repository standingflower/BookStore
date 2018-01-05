<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
	<s:property value="book.bookName"/>
</title>
<style type="text/css">
	body {
		margin: 0px;
		background-color:#b3e5fc;
	}
	
	.header {
		margin: 0px auto;
		margin-top: 25px;
		width: 840px;
		height: 110px;
		padding-left: 45px;
	}
	
	.main {
		margin: 0px auto;
		width: 840px;
	}
	
	.book_logo {
		float: left;
		font-family: 华文楷体;
		font-size: 45px;
		padding-left: 105px;
		padding-top: 25px;
		line-height: 100px;
		color: #2979ff;
	}
	
	.login {
		margin-top: 75px;
		margin-right: 35px;
		float: right;
		font-size: 20px;
	}
	
	.navi {
		clear: both;
		margin: 0px auto;
		width: 840px;
		border: 1px solid #b3e5fc;
	}
	
	dt{
		float: left;
		width:33%;
		text-align:center;
		background-color:#4999ff; 
	}
	
	dt a{
		display: block;
		text-decoration: none;
		font-size: 20px;
		
	}
	dt a:hover{
		background-color:#b3e5fc;
	}
	
	
	.sele {
		clear: both;
		padding-left: 22px;
		margin-top: 40px;
		margin-bottom: 10px;
	}
	
	.foot {
		width: 55%;
		margin: 0px auto;
		margin-top: 45px;
		text-align: center;
		color: #757575;
	}
	
	
	.detail{
		clear:both;
		margin: 10px auto;
		width: 840px;
		height: 360px;
	
	}

</style>
</head>
<body>
	<div class="header">
		<div style="float: left;">
			<img src="file/img/webimg/book_logo.png" width="150px" height="120px">
		</div>
		<div class="book_logo">图书管理系统</div>
		<div class="login">
			<s:if test="#session.user==null">
				<a href="login.jsp">登录</a>
			</s:if>
			<s:else>
				欢迎<span style="color:red;"><s:property value="#session.user.userName"/></span>的登录
			</s:else>
		</div>
	</div>
	
	<div class="main">
		<div class="navi">
			<dl>
				<dt><a href="book_findbook" style="display: block;">热门推荐</a></dt>
				<dt><a href="book_search" >文献查找</a></dt>
				<dt><a href="bookuser_selectRecordByUserId">我的图书</a></dt>
			</dl>
		</div>
		<div style="text-align: center;margin-top: 50px"><h3>书籍详细信息</h3></div>
		<div class="detail">
			<table>
				<tr>
					<td width="20%">书名：</td>
					<td width="40%">
						<s:property value="book.bookName"/>
					</td>
					<td width="10%"></td>
					<td rowspan="9" width="30%">
						<img alt="" style="width: 250px;height: 350px;" src="
							<s:property value='book.bookPicture'/>
						">
					</td>
				</tr>
				<tr>
					<td>作者：</td>
					<td>
						<s:property value="book.bookAuthor"/>
					</td>
				</tr>
				<tr>
					<td>出版社：</td>
					<td>
						<s:property value="book.bookPublih"/>
					</td>
				</tr>
				<tr>
					<td>ISBN：</td>
					<td>
						<s:property value="book.bookIsbn"/>
					</td>
				</tr>
				<tr>
					<td>内容简介：</td>
					<td>
						<s:property value="book.bookBrief"/>
					</td>
				</tr>
				<tr>
					<td>书籍库存：</td>
					<td>
						<s:property value="book.bookStore"/>
					</td>
				</tr>
				<tr>
					<td>可借数：</td>
					<td>
						<s:property value="book.bookLend"/>
					</td>
				</tr>
				<tr>
					<td>类型：</td>
					<td>
						<s:property value="book.bookStyle"/>
					</td>
				</tr>
				<tr>
					<td>借阅次数：</td>
					<td>
						<s:property value="book.bookLendtimes"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	
	<div style="margin-top:150px;text-align:center;">
		图书管理系统©版本1.0
	</div>
	
	
	
</body>
</html>