# Dario's Blog
基于jsp/servlet的个人博客网站
github地址：https://github.com/darioshi/dariosblog

## 简介

学习java web基础练手项目，用servlet和JSP实现个人博客网站，实现的功能有文章发布，评论，点赞，分类，标签等等功能。

* 主要涉及到的知识点有jsp,servlet,mysql,bootstrap,html/css/js,ajax,json

* 数据库连接池使用了[c3p0](http://www.mchange.com/projects/c3p0/) 你可以在src/c3p0-config.xml配置连接池和数据库信息

* Markdown编辑器使用了[editor.md](https://github.com/pandao/editor.md)

### 快速运行

1. 配置 src/c3p0-config.xml 修改你的数据库信息，确认能建立连接。

2. 运行建表sql建立表 src/dariosblog.sql,可以在此任意插入一些数据以便检查。

3. 搭建服务器环境,如 eclipse或IDEA、tomcat 导入整个demo.

4. localhost:xxx/Blog/ 访问


### 数据库

你可以在src/目录下找到详细的sql文件。

* t_article - 文章表
* t_comment - 评论表
* t_tag - 文章的标签表
* t_article_tag - 文章标签关系表
* t_sort - 分类表
* t_user - 管理员表
* t_visitor - 访问记录表
