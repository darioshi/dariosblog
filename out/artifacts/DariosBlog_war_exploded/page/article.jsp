<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${article.title} | Dario's Blog</title>
    <!-- Bootstrap core CSS -->
    <link
            href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->

    <!-- 引入本页面的特殊样式 -->
    <link type="text/css" rel="stylesheet" href="./css/article.css" />
    <link type="text/css" rel="stylesheet" href="./css/comment.css" />

    <script src="./js/article.js"></script>

</head>
<body>
<div class="head_line"></div>
<div class="container" id="main">
    <div id="header"></div>
    <div class="row c_center">
        <div class="col-md-3" id="left_content">

            <div id="title">
                <h2 style="margin-top: 20px;margin-bottom: 10px;"><a href="/Blog/LoginServlet">Dario's Blog</a></h2>
                <h5 class="text-muted">Get busy living or get busy dying</h5>
            </div>

            <div class="c_center" id="person_info">
                <img src="/Blog/img/header.jpg" height="130" width="130"
                     alt="找不到头像" class="img-circle">
                <h4 class="text-muted" style="margin-top: 10px;margin-bottom: 10px;">Dario</h4>
                <h5 class="text-muted" style="margin-top: 10px;margin-bottom: 10px;">菜鸟</h5>
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
                    <tr class="active">
                        <td><a href="/Blog/index.jsp	"><span class="glyphicon glyphicon-home"></span>
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
                    <%-- <tr>
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
        <div class="col-md-1" id="center_content">
        </div>

        <div  class="col-md-8 " id="right_Content">
            <div id="article">
                <div id="a_head ">
                    <c:if test="${comment_info!=null}">
                        ${comment_info}
                    </c:if>
                    <h3>${article.title}</h3>
                    <br />
                    <div class="f_div">
                        <h5>
                            <span>${article.create_time}</span>&nbsp;&nbsp; <a href="/Blog/SortServlet?get=${sort.id}">${sort.name}</a>&nbsp;&nbsp;
                            ${author.name}
                        </h5>
                    </div>
                    <div class="r_div">
                        <h5>
                            <span class="glyphicon glyphicon-eye-open">&nbsp;${article.visit}&nbsp;</span>
                            <span class="glyphicon glyphicon-heart" id="love">&nbsp;${article.star}&nbsp;</span>
                            <span	class="glyphicon glyphicon-comment">&nbsp;${article.comment}&nbsp; </span>
                        </h5>
                    </div>
                </div>
                <div class="line" style="margin-top: 30px;"></div>
                <div id="a_content">
                    <!-- 引入 show.jsp 显示文章内容 ${article.content}-->
                    <jsp:include page="/page/show.jsp"/>
                </div>
                <div class="line"></div>
                <div id="tag" class="f_div">
                    <c:forEach var="t"  varStatus="status" items="${tags_list}" >
                        <c:choose>
                            <c:when test="${status.count%2==1}">
							<span class="label label-info"><a href="/Blog/TagsServlet?get=${t.id}">
							&nbsp;${t.name}&nbsp;</a></span>
                            </c:when>
                            <c:otherwise>
								<span class="label label-success"><a href="/Blog/TagsServlet?get=${t.id}">
								&nbsp;${t.name}&nbsp;</a></span>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
                <br>
                <div style="margin-top: 20px;">
                    <div class="f_div">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                        <c:choose>
                            <c:when test="${pre_article!=null}">
                                <a href="/Blog/ArticleServlet?id=${pre_article.id}">&nbsp;上一篇:${pre_article.title}</a>
                            </c:when>
                            <c:otherwise>
                                &nbsp;没有更早的文章了
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="r_div">
                        <c:choose>
                            <c:when test="${next_article!=null}">
                                <a href="/Blog/ArticleServlet?id=${next_article.id}">下一篇:&nbsp;${next_article.title}</a>
                            </c:when>
                            <c:otherwise>
                                &nbsp;没有更多的文章了
                            </c:otherwise>
                        </c:choose>
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </div>
                    <div>
                        <span class="btn btn-default" style="color:#d9534f;"  onclick="love_article(${article.id})" >点赞</span>
                    </div>
                    <br/>
                </div>
                <div class="line"></div>

                <!-- 评论 -->
                <div class="comment">
                    <div class="r_div">
                        <a href="#comment"><span class="glyphicon glyphicon-pencil">&nbsp;写评论....</span></a>
                    </div>
                    <!-- 加载文章评论 -->
                    <c:if test="${comment_list!=null}">
                        <c:forEach var="comm" varStatus="status" items="${comment_list}">
                            <div class="row" >
                                <div class="f_div">
                                    <img src="/Blog/img/comment.jpg" height="50" width="50"  class="img-circle"/>
                                    &nbsp;&nbsp;
                                    <span style="color: #428bca"> ${comm.nickname}</span>
                                    <span>&nbsp;&nbsp;${comm.create_time}</span>
                                </div>
                                <div  id="c_content" class="c_left">
                                    <pre style="padding: 10px; text-align: left">${comm.content }</pre>
                                </div>
                                <div class="r_div" style="margin-bottom: 10px;">
                                    <a><span class="glyphicon glyphicon-thumbs-up"  onclick="star(this,${comm.id})">${comm.star}</span></a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a><span class="glyphicon glyphicon-thumbs-down" onclick="diss(this,${comm.id})">${comm.diss}</span></a>
                                    &nbsp;&nbsp;&nbsp;
                                    <!-- admin here -->
                                    <c:if test="${sessionScope.user!=null}">
                                        <span class="btn btn-default" style="color:red;" onclick="deletecm(this,${comm.id},${article.id})">删除</span>
                                        &nbsp;
                                    </c:if>
                                </div>
                                <div class="line"></div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                <!-- 这里可以扩展子评论 -->
                <!-- 写评论 -->
                <div id="comment" style="margin-left: 30px;">
                    <form action="/Blog/NewCommentServlet?article_id=${article.id}" method="post">
                        <div style="margin-bottom: 20px;">
                            <textarea class="form-control" style="resize:none; width:80%; height:100px;" name="w_content" placeholder="评论......"></textarea>
                        </div>
                        <div style="margin-bottom: 20px;">
                            <input  style="width:30%" class="form-control" type="text" name="w_nickname" placeholder="nickname"/>
                        </div>
                        <div style="margin-bottom: 20px;">
                            <input  style="width:30%" class="form-control" type="email" name="w_email" placeholder="Email"/>
                        </div>
                        <div>
                            <input  style="width:20%; background-color: #337ab7;color: white" class="form-control" type="submit"  value="评论"/>
                        </div>
                    </form>
                </div>
            </div>
            <!--  -->
            <div class="line"></div>
        </div>
    </div>
</div>
<!-- footer -->
</body>
</html>