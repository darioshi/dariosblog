package blog.model;

public class Article {
    private int id; //文章ID
    private String title; //标题
    private int user_id; //作者ID
    private int sort_id; //分类ID
    private int star; //点赞数
    private int comment; //评论数
    private int visit; //访问数
    private int status; //状态
    private String content; //内容
    private String create_time; //创建时间
    private String update_time; //创建时间

    private String sort_name; //分类名
    private String Author; //作者

    public Article() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment_id) {
        this.comment = comment_id;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getSort_name() {
        return sort_name;
    }

    public void setSort_name(String sort_name) {
        this.sort_name = sort_name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", user_id=" + user_id +
                ", sort_id=" + sort_id +
                ", star=" + star +
                ", comment=" + comment +
                ", visit=" + visit +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", sort_name='" + sort_name + '\'' +
                '}';
    }
}
