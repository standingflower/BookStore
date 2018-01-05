<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书首页</title>
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
	

</style>

</head>
<body>

	<div class="header">
		<div style="float: left;">
			<img src="file/img/webimg/book_logo.png" width="150px" height="120px">
		</div>
		<div class="book_logo">图书管理系统</div>
		<div class="login">	
			<!-- 
				test不能使用EL表达式
			 -->
			<s:if test="#session.user==null">
				<a href="login.jsp">登录</a>
			</s:if>
			<s:else>
				欢迎<span style="color:red;">
					<!-- 
						${sessionScope.user.userName }都可以
					-->
					<s:property value="#session.user.userName"/>
				</span>的登录
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
		<div class="sele">
			a： <a href="book_selectByBookTypes?type=book_style&&value=文学">文学</a>
			&nbsp;&nbsp;b： <a
				href="book_selectByBookTypes?type=book_style&&value=军事">军事</a>
			&nbsp;&nbsp;c： <a
				href="book_selectByBookTypes?type=book_style&&value=科技">科技</a> <br>d：
			<a href="book_selectByBookTypes?type=book_style&&value=医学">医学</a>
			&nbsp;&nbsp;e： <a
				href="book_selectByBookTypes?type=book_style&&value=法律">法律</a>
		</div>
		<div>
			<table width="800px" align="center" style="list-style: none">
				<tr style="background-color: pink; list-style: none;">
					<td width="2%" style="padding-top: 20px"></td>
					<td width="6%">书名</td>
					<td width="10%">作者</td>
					<td width="10%">出版社</td>
					<td width="14%">ISBN</td>
					<td width="10%">借阅次数</td>
				</tr>
				<s:if test="pageInfo.list.size>0">
					<s:iterator var="book" value="pageInfo.list" status="st">
						<tr>
							<th><s:property value="#st.getCount()" /></th>
							<td><a href="book_findBookByPrimaryKey?bookId=
								<s:property value="bookId"/>
							">
								<!-- 
									${bookName }都可以
									Structs2标签中的EL标签表达式会自动到值栈中去寻找对应属性名的属性值
								 -->
								<s:property value="bookName" />
							</a></td>
							<td><s:property value="bookAuthor" /></td>
							<td><s:property value="bookPublih" /></td>
							<td><s:property value="bookIsbn" /></td>
							<th><s:property value="bookLendtimes" /></th>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					<tr>
						<th colspan="6">结果为空！</th>
					</tr>					
				</s:else>
				
			</table>
		</div>

		</div>
		<div class="foot">
			 <a href="<s:property value="com.opensymphony.xwork2.ActionContext.name"/>
				?pageNoStr=${pageInfo.prePage }
				<s:if test="type!=null">
					&type=${type}&value=${value }
				</s:if>
			">上一页</a>

			<span style="color: red"> ${pageInfo.pageNum }
			</span> /
			${pageInfo.pages }
			<a href="<s:property value="com.opensymphony.xwork2.ActionContext.name"/>?pageNoStr=
				${pageInfo.nextPage }
				<s:if test="type!=null">
					&type=${type}&value=${value }
				</s:if>
			">下一页</a>
		</div>
		
		
		<div style="margin-top:150px;text-align:center;">
			图书管理系统©版本1.0
		</div>
	
</body>
</html>