<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
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
	
	.userde{
		clear:both;
		margin:0px auto;
		margin-top:40px;
		padding-left:50px;
		width: 840px;
	}
	
	.tab{
		width:800px;
		margin: 20px auto;
	}

</style>

<script type="text/javascript" src="/BookStore/file/js/jquery-1.3.1.js" ></script>
<script type="text/javascript">
	$(function(){
		
		$(".renew").click(function(){
			var fun=$(this);
			var s=fun.parent().prev().html();
			s=$.trim(s);
			if(s>0){
				alert("请找管理员续借");
				return false;
			}else{
				//发送Ajax请求
				$.ajax({
					type:"post",
					url:$.trim($(this).attr("href")),
					data:null,
					dataType:"json",
					success:function(data){
						if(data.resjson==1){
							//续借成功，修改显示的续借次数
							s=Number(s)+1;
							fun.parent().prev().html(s);
							alert("续借成功！");
						}
						if(data.resjson==-1){
							alert("当前用户不可续借！");
						}
						if(data.resjson==0){
							alert("续借失败,请到柜台续借！");
						}
					}
				});
				return false;
			}
		});
		$(".jg").click(function(){
			alert("请到柜台解挂");
			return false;
		});
		
	})

</script>
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
	</div>
	<div class="userde">
		用户名：<s:property value="#session.user.userName"/><br>
		借阅号：<s:property value="#session.user.userId"/><br>
		借阅状态：	
		<s:if test="#session.user.iflend==0">
			可借
			(<a href="user_lose">挂失</a>)
		</s:if>
		<s:else>
			不可借
			(<a href="" class="jg">解挂</a>)
			
		</s:else>
		<s:if test="#session.user.userRole==0">
			<br><a href="user_toManage">管理图书</a>
		</s:if>
		
	</div>
	
	<br>
	<div style="text-align: center;font-size: 20px;color: blue;">当前借阅详情</div>	
	
	<table class="tab">
		<tr style="background-color: pink;">
			<td width="5%">序号</td>
			<td width="10%">书名</td>
			<td width="10%">借书时间</td>
			<td width="10%">还书时间</td>
			<td width="5%">是否归还</td>
			<td width="5%">需借次数</td>
			<td width="5%">续借</td>
		</tr>
		<s:iterator var="bu" value="#request.info.list" status="st">
			<tr>
				<td>
					<s:property value="#st.getCount()"/>
				</td>
				<td>
					<a href="book_findBookByPrimaryKey?bookId=
						${bu.book.bookId }">
						${bu.book.bookName }
					</a>
				</td>
				<td>
					<s:date name="#bu.lendtime" format="yyyy-MM-dd" nice="false"/>
				</td>
				<td>
					<s:date name="#bu.returntime" format="yyyy-MM-dd" nice="false"/>
				</td>
				<td>
					${bu.ifreturn==0?"是":"否" }
				</td>
				<td>
					${bu.renewtimes }
				</td>
				<td>
					<s:if test="#bu.ifreturn==1">
						<a href="bookuser_renew?user.userId=<s:property value='#session.user.userId'/>&bookId=${bu.book.bookId }" class="renew">
							续借
						</a>
					</s:if>
					<s:else>
						续借
					</s:else>
					<!-- 续借之前通过js判读是否可借 -->
				</td>
		</s:iterator>
	</table>
	<div style="margin: 0px auto;
	margin-top: 45px;
	text-align: center;">
		<a href="
			bookuser_selectRecordByUserId?pageNoStr=${requestScope.info.prePage }
		">上一页</a>
		<span style="color: red"> ${requestScope.info.pageNum }</span>
		/
		${requestScope.info.pages }
		<a href="
			bookuser_selectRecordByUserId?pageNoStr=${requestScope.info.nextPage }
		">下一页</a>
	</div>
	
	<div style="margin-top:150px;text-align:center;">
		图书管理系统©版本1.0
	</div>
	
	
	
</body>
</html>