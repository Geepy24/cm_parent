<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>



<meta charset="UTF-8">



<title>${article.artTitle }</title>



<link rel="stylesheet" href="${pageContext.request.contextPath }/articleUI/css/style.css" media="screen"
	type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath }/articleUI/js/jquery-1.4.4.min.js"></script>

<!-- <script type="text/javascript" src="js/show.js"></script> -->
</head>



<body>



	<header class="yapiskan">${article.artTitle }</header>

	<div id="lipsum">
		
			${article.artContent }
		
		<p>
			文章作者：${article.user.userName }
		</p>
		<p>
			发表时间：${article.pubTime}
		</p>
	</div>


	



	<script src="${pageContext.request.contextPath }/articleUI/js/index.js"></script>



</body>


</html>