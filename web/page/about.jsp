<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关于 | Dario's Blog</title>

<!-- Bootstrap core CSS -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<!-- 引入页面公共样式 -->
<link type="text/css" rel="stylesheet" href="/Blog/css/public.css" />
</head>
<body>
	<div class="head_line"></div>
	<div class="container" id="main">
		<div id="header"></div>
		<div class="row c_center">
            <div class="col-md-3" id="left_content">

                <div id="title">
                    <h2><a href="/Blog">Dario's Blog</a></h2>
                    <h5 class="text-muted">Get busy living or get busy dying</h5>
                </div>

                <div class="c_center" id="person_info">
                    <img src="/Blog/img/header.jpg" height="130" width="130"
                         alt="找不到头像" class="img-circle">
                    <h4 class="text-muted">Dario</h4>
                    <h5 class="text-muted">菜鸟</h5>
                </div>

                <div class="c_center">
                    <div class="inline ">
                        <a href="/Blog">${article_number}<br/>日志</a>
                    </div>
                    <div class="inline ">
                        <a href="/Blog/SortServlet?get=all"><span> ${sort_number} </span><br/>分类</a>
                    </div>
                    <div class="inline " >
                        <a href="/Blog/TagsServlet?get=all"><span>${tags_number}</span><br/>标签</a>
                    </div>
                </div>


                <div id="list">
                    <table class="table table-hover c_center">
                        <tr>
                            <td><a href="/Blog/index.jsp"><span class="glyphicon glyphicon-home"></span>
                                &nbsp;&nbsp;首页</a></td>
                        </tr>
                        <tr>
                            <td><a href="/Blog/SortServlet?get=all"><span class="glyphicon glyphicon-list"></span>
                                &nbsp;&nbsp;分类</a></td>
                        </tr>
                        <tr>
                            <td><a href="/Blog/TagsServlet?get=all"><span class="glyphicon glyphicon-tags"></span>
                                &nbsp;&nbsp;标签</a></td>
                        </tr>
                        <%--<tr>
                            <td><a href="/Blog/AxisServlet"><span class="glyphicon glyphicon-book"></span>
                                &nbsp;&nbsp;时间</a></td>
                        </tr>--%>
                        <tr  class="active">
                            <td><a href="/Blog/AboutServlet"><span class="glyphicon glyphicon-user"></span>
                                &nbsp;&nbsp;关于</a></td>
                        </tr>
                    </table>
                </div>
                <!-- list -->
                <br/>

                <div class="sort">
                    <div class="list-group">
                        <span class="list-group-item active">分类</span>
                        <!-- 这里初始化分类 -->
                        <c:forEach var="entity"  items="${sort_list}">
                            <a href="/Blog/SortServlet?get=${entity.id}" class="list-group-item">${entity.name}&nbsp;(${entity.article_num})</a>
                        </c:forEach>
                        <!-- 初始化结束 -->
                    </div>
                </div>
                <!-- sort -->

                <div class="visit">
                    <div class="list-group">
                        <span class="list-group-item active">阅读排行</span>
                        <!-- 这里初始化阅读排行 -->
                        <c:forEach var="a"  items="${visit_rank}">
                            <a href="/Blog/ArticleServlet?id=${a.id}" class="list-group-item">${a.title}&nbsp;<span class="c_right">(${a.visit})</span></a>
                        </c:forEach>
                        <!-- 初始化结束 -->
                    </div>
                </div>
                <!-- visit-->


                <div id="tag">
                    <div class="list-group">
                        <span class="list-group-item active">标签</span>
                        <br/>

                        <!-- 这里初始化标签 -->
                        <c:forEach var="t"  varStatus="status" items="${tag_list}" >
                            <c:choose>
                                <c:when test="${status.count%2==1}">
							<span class="label label-info" style="line-height: 2;"><a href="/Blog/TagsServlet?get=${t.tag_id}">
							&nbsp;${t.tag_name}&nbsp;(${t.article_num})</a></span>
                                </c:when>
                                <c:otherwise>
								<span class="label label-success" style="line-height: 2;"><a href="/Blog/TagsServlet?get=${t.tag_id}">
								&nbsp;${t.tag_name}&nbsp;(${t.article_num})</a></span>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <!-- 初始化标签完成 -->
                    </div>
                </div>
                <!-- tag -->

                <!-- admin here -->
                <c:if test="${sessionScope.user!=null}">
                    <a href="/Blog/AddServlet">
                        <span class="glyphicon glyphicon-plus">&nbsp;&nbsp;写新文章&nbsp;&nbsp;</span>
                    </a>
                    <a href="/Blog/AdminServlet">
                        <span class="glyphicon glyphicon glyphicon-user">&nbsp;&nbsp;管理更多&nbsp;&nbsp;</span>
                    </a>
                </c:if>

            </div>
            <div class="col-md-1" id="center_content"></div>
            <div class="col-md-8 " id="right_content" style="margin-top: 100px;">
				<h2>About me</h2>
				<div style="text-align: center">
					<h4>
						Email : darioshi@163.com<br/>
                        GitHub: https://github.com/darioshi<br/>
                        QQ: 328774768<br/>
					</h4>
				</div>
			</div>
		</div>
	</div>
	
	<div class="foot_line"></div>
</body>
</html>