package blog.model;

public class Comment {
    private int id; //主键ID
    private int article_id; //文章ID
    private String nickname; //昵称
    private String email; //邮箱
    private String content; //内容
    private int star; //支持数
    private int diss; //反对数
    private String create_time; //创建时间
    private String update_time; //创建时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getDiss() {
        return diss;
    }

    public void setDiss(int diss) {
        this.diss = diss;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", article_id=" + article_id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", star=" + star +
                ", diss=" + diss +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
