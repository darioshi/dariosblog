package blog.model;

public class Tag {

    private int id; //主键ID
    private String name; //标签名
    private String create_time; //创建时间
    private String update_time; //更新时间

    public Tag() {
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
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
