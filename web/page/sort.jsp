<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>分类 | Dario's Blog</title>


	<!-- Bootstrap core CSS -->
	<link
			href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
			rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="signin.css" rel="stylesheet">


	<link type="text/css" rel="stylesheet" href="/Blog/css/public.css" />
	<link type="text/css" rel="stylesheet" href="/Blog/css/sort.css" />
</head>
<body>
<div class="head_line"></div>

<div class="container" id="main">
	<div id="header"></div>
	<div class="row c_center">
		<div class="col-md-3" id="left_content">

			<div id="title">
				<h2><a href="/Blog/LoginServlet">Dario's Blog</a></h2>
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
					<a href="/Blog/ArticleServlet?get=all">${article_number}<br/>日志</a>
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
					<tr class="active">
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
					<tr>
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
							<span class="label label-info"><a href="/Blog/TagsServlet?get=${t.tag_id}">
							&nbsp;${t.tag_name}&nbsp;(${t.article_num})</a></span>
							</c:when>
							<c:otherwise>
								<span class="label label-success"><a href="/Blog/TagsServlet?get=${t.tag_id}">
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
		<div class="col-md-8 " id="right_Content">
			<br /> <br />
			<div class="list-group">
				<a href="#" class="list-group-item active">分类</a>
				<!-- 这里初始化列表 -->
				<c:forEach var="map" items="${sort_article_map}">
					<div class="sort_name">
						<!-- 分类名字 -->
						<span class="glyphicon glyphicon-triangle-bottom"></span>&nbsp;&nbsp;${map.key.name}
					</div>
					<div style="margin-left: 30px;">
						<!-- 分类信息 -->
						<ul class="list-group">
							<c:forEach var="list" items="${map.value}">
								<li class="list-group-item">
									<div>
										<div style="float: left; margin-right: 50px">
											<a href="/Blog/ArticleServlet?id=${list.id}">${list.title}</a>
										</div>
										<div class="c_right">
											<img src="/Blog/img/time.png">
												${list.create_time}&nbsp;&nbsp;
											<span class="visit"><img src="/Blog/img/visit.png">&nbsp;${list.visit}</span>
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
				<!-- 初始化列表完成 -->
			</div>
		</div>
	</div>

	<div class="foot_line"></div>
</div>
<!-- container -->

</body>
</html>