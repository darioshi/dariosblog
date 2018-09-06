<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New Article | Dario's Blog</title>


    <!-- Bootstrap core CSS -->
    <link	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link type="text/css" rel="stylesheet" href="./css/add.css" />

    <link rel="stylesheet" href="./editormd/css/style.css" />
    <link rel="stylesheet" href="./editormd/css/editormd.css" />

    <script src="./editormd/js/zepto.min.js"></script>
    <script src="./editormd/js/editormd.js"></script>

    <script src="./js/add.js"></script>
    <%--<script type="text/javascript" src="./editormd/js/jquery.min.js"></script>--%>
</head>
<body>
<div class="head_line"></div>
<div class="container" id="main">
    <div class="col-md-3" id="left_content">

        <div id="title">
            <h2 style="margin-top: 20px;margin-bottom: 10px;"><a href="/Blog">Dario's Blog</a></h2>
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
                <a href="#">${article_number}<br/>日志</a>
            </div>
            <div class="inline ">
                <a href="/Blog"><span> ${sort_number} </span><br/>分类</a>
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
    <div  class="col-md-9 " id="right_Content">
        <form action="/Blog/NewArticleServlet" method="post">
            <table class="edit_table">
                <tr>
                    <td><b>标题</b></td>
                    <td><input type="text"  name="title" required></td>
                </tr>
                <tr>
                    <td><b>分类</b></td>
                    <td><input type="text"  id="sort" name="sort" style="width: 100px;" required>
                        <span style="float: left; margin-left: 10px;">
                            <c:forEach var="s"  items="${all_sort}">
                                <a onclick="sort_click(this)" href="javascript:void(0);">${s.name}</a>&nbsp;
                            </c:forEach>
                        </span>
                    </td>
                </tr>
                <tr>
                    <td><b>标签</b></td>
                    <td>
                        <input type="text" id="tags"  name="tags" style="width: 200px;" required>
                        <span style="float: left; margin-left: 10px;">
                            <c:forEach var="t"  varStatus="status" items="${all_tag}" >
                                <c:choose>
                                    <c:when test="${status.count%2==1}">
							            <span class="label label-info"><a onclick="tags_click(this)" href="javascript:void(0);">${t.name}</a>&nbsp;</span>
                                    </c:when>
                                    <c:otherwise>
								        <span class="label label-success"><a onclick="tags_click(this)" href="javascript:void(0);">${t.name}</a>&nbsp;</span>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </span>
                    </td>
                </tr>
            </table>
            <div class="foot_line"></div>
            <!-- content -->
            <div class="editormd" id="mdView">
                <textarea name="content" required></textarea>
            </div>
            <br/>
            <input  style="width:20%; background-color: #337ab7;color: white" class="form-control"  type="submit"   value="提交" />
            <br/>
        </form>
        <%--<div class="foot_line"></div>--%>
        <!-- container -->
    </div>
    <!-- container -->
</div>

<script type="text/javascript">
    var editor;
    var jQuery = Zepto;
    $(function() {
        editor = editormd("mdView", {
            width  : "100%",
            height : 700,
            path   : './editormd/lib/',
            codeFold : true,
            searchReplace : true,
            saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
            htmlDecode : "style,script,iframe|on*", // 开启 HTML 标签解析，为了安全性，默认不开启
            emoji : true,
            taskList : true,
            tocm: true,
            tex : true,
            flowChart : true,
            sequenceDiagram : true,
            imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "/Blog/UploadPic",
            //后台只需要返回一个 JSON 数据
            onload : function() {
                // console.log("onload =>", this, this.id, this.settings);
            }
        });
        editor.setToolbarAutoFixed(false);//工具栏自动固定定位的开启与禁用
    });
</script>

</body>
</html>