<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Result | Dario's Blog</title>
    <!-- Bootstrap core CSS -->
    <link
            href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

    <!-- 引入页面公共样式 -->
    <link type="text/css" rel="stylesheet" href="/Blog/css/public.css" />

</head>
<body>
<div class="head_line"></div>
<div class="container" id="main">
    <div class="row c_center" style="margin:10% auto;">
        <c:choose>
            <c:when test="${article==null}">
                <h4>
                    创建或更新文章失败了......<a href="/Blog/AddServlet">返回</a>
                </h4>
            </c:when>
            <c:otherwise>
                <h4>${msg} 查看文章......&nbsp;&nbsp;<a href="/Blog/ArticleServlet?id=${article.id}">${article.title}</a></h4>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="foot_line"></div>
</div>
<!-- footer -->
</body>
</html>