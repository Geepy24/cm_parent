<%@page import="com.cm.domain.User"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<jsp:useBean id="now" class="java.util.Date" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome to CMVolleyball</title>
<link rel="cmv icon" href="${pageContext.request.contextPath}/img/cmv.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
	<script type="text/javascript">
	//页面加载就往新闻窗口填充文章
		function index_article(){
			
			$.ajax({
				type : "GET" ,
				url : "${pageContext.request.contextPath}/Article/indexArticle.action" ,
				data : {
					"content" : "artTitle"
					
				}, 
				dataType : "JSON" ,
				success : function(data){
					//返回的json数据是{"artId" : "artTitle+pubTime"}，要截取
					//返回的是字符串，要变为json对象
				//	alert(data) ;
					var backdata = JSON.parse(data) ;
					//读取json对象中数据的条目数
					for(attr in backdata){
					//	alert(attr+"-"+backdata[attr]) ;
					//显示到新闻栏目中
						arr = backdata[attr].split(".",2) ;
						var title = arr[0] ;
						var publish_time = arr[1] ;
						//alert(publish_time);
						$("#article_table").prepend(
					"<tr><td id='left_td'><a href='${pageContext.request.contextPath}/Article/showArticle.action?artId="+attr+"' target='_blank' >"+title+"</a></td>"
							+"<td id='right_td'>"+publish_time+"</td></tr>" 
					
					) ;
					}
					
					
				}
			}) ;
			
		}
	</script>
	
	
	<script type="text/javascript">
		$(document).ready(index_article()) ;
	</script>


</head>
<body>

	<div id="head">
		<div id="upper">
			<div id="upper_a">
				<span class="l"><a href="">收藏本站</a></span>
				<span class="l">当前日期&nbsp;:&nbsp;<fmt:formatDate  value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" /></span>
				
				<c:set var="userName" value="${session.loginInfo.userName}"/>
				<c:if test="${null == userName}">
					<span class="r"><a href="${pageContext.request.contextPath}/User/login.action">登录</a></span>
					<span class="r"><a href="${pageContext.request.contextPath}/Login/register.action">注册</a></span>
				</c:if>
				
				<c:if test="${null != userName}"  >
					<span class="r"><a href="${pageContext.request.contextPath}/Logout/logout.action">注销</a></span>
					<span class="r">${userName}</span>
				</c:if>
				
			</div>
		</div>
		<div id="contant">
			<img src="${pageContext.request.contextPath}/img/logo1.png"  />
		</div>
		<div id="nav1">
			<div id="nav">
				<span><a href="#">网站首页</a></span>
				<span><a href="${pageContext.request.contextPath }/brief.jsp">球队简介</a></span>
				<span><a href="${pageContext.request.contextPath }/userResource/indexpictures.action" >精彩图集</a></span>
				<span><a href="${pageContext.request.contextPath }/userResource/indexmovies.action">视频</a></span>
				<span><a href="${pageContext.request.contextPath}/Persional/comein.action?userId=${loginInfo.userId}" >个人页面</a></span>
			</div>
		</div>
	</div> 

	
	<div id="center">
		<div id="slide">
			<img src="${pageContext.request.contextPath}/img/bg01_03.jpg"/>
		</div>
		<div id="main">
			<fieldset id="message" >
				<legend align="left"><img src="${pageContext.request.contextPath}/img/volleyball.png">&nbsp;&nbsp;<STRONG><font color="darkblue" size=3>球队新闻</font></STRONG>&nbsp;&nbsp;</legend>
				<!-- 	<a href="javascript:void(0);" onclick="index_article()">点击获取</a>
				 -->	
					
					<table id="article_table">
					
					</table>
			</fieldset>
			<fieldset id="source">
				<legend align="left"><img src="${pageContext.request.contextPath}/img/volleyball.png">&nbsp;&nbsp;<STRONG><font color="darkblue" size=3>实用工具列表</font></STRONG>&nbsp;&nbsp;</legend>
				<table>
					<tr>
						<td>暂无</td>
					</tr>					
				</table>	
			</fieldset>
		</div>
	</div>
	 
	 <div id="buttom">
	 	<div id="end">
	 		<ul>
	 			<li><a href="${pageContext.request.contextPath}/privateSource/management.action">管理员页面</a></li>
	 		</ul>
	 		
	 		<ul>
	 			<li><a href="">联系我们</a></li>
	 			<li><a href="">意见反馈</a></li>
	 		</ul>
	 		<ul>
	 			<li>感谢您的访问!</li>
	 			<li>版本号：v2.0</li>
	 		</ul>
		 	</div>
	 </div>

 <s:debug></s:debug>
</body>
	 
	 
	

</html>