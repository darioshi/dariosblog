package blog.model;

public class ArticleTag {
    private int id; // 主键ID
    private int article_id; //文章ID
    private int tag_id; //标签ID
    private String create_time; //创建时间
    private String update_time; //更新时间

    private String tag_name; //标签名
    private int article_num; //该标签下文章数

    public int getArticle_num() {
        return article_num;
    }

    public void setArticle_num(int article_num) {
        this.article_num = article_num;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

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

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
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
        return "ArticleTag{" +
                "id=" + id +
                ", article_id=" + article_id +
                ", tag_id=" + tag_id +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", tag_name='" + tag_name + '\'' +
                ", article_num=" + article_num +
                '}';
    }
}
