<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="cmv icon" href="${pageContext.request.contextPath}/img/cmv.ico" type="image/x-icon" />
<title>图片详情	</title>
</head>
<body style="text-align:center;">
	<div id="show_pic" style="margin:0,auto;width:100%;height:800px;">
	<img style="width:500px;height:500px;margin:0,auto;" alt="${resCom}" src="/pic/${picture.picName}">
	<table>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td><h4><strong>图片描述:</strong></h4></td>
			<td><span>${resCom}</span></td>
		</tr>
		
		<tr>
			<td></td>
			<td><a href="${pageContext.request.contextPath }/userResource/nextRes.action?resId=${resId}&resTag=${resTag}">下一张</a>
				<a href="${pageContext.request.contextPath }/userResource/preRes.action?resId=${resId}&resTag=${resTag}">上一张</a></td>
			<td><a href="${pageContext.request.contextPath }/userResource/indexresource.action?resTag=${resTag}">返回列表</a></td>
		</tr>
		
		</table>
	</div>
	<s:debug></s:debug>
</body>
</html>