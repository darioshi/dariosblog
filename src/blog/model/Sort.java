package blog.model;

public class Sort {

    private int id; //主键ID
    private String name; //分类名称
    private String create_time; //创建时间
    private String update_time; //更新时间

    public int getArticle_num() {
        return article_num;
    }

    public void setArticle_num(int article_num) {
        this.article_num = article_num;
    }

    private int article_num; //该分类下文章数

    public Sort() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Sort{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
