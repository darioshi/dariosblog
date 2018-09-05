package blog.daoImpl;

import blog.dao.TagDao;
import blog.db.C3P0Connection;
import blog.model.ArticleTag;
import blog.model.Tag;
import blog.utils.DBUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TagDaoImpl implements TagDao {

    private Connection conn;

    private static TagDao instance;

    private TagDaoImpl() {
        conn = C3P0Connection.getInstance().getConnection();
    }

    public static TagDao getInstance() {
        if(instance == null) {
            try {
                instance = new TagDaoImpl();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public int getCount() {
        int count = 0;
        String sql = "select count(*) as cou from t_tag";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                count = Integer.parseInt(rs.getString("cou"));
            }
            DBUtils.Close(stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List getTagAndCont() {
        List<ArticleTag> tagList = new ArrayList<>();
        String sql = "select a.id, a.name, count(b.id) num from t_tag a left join t_article_tag b on a.id = b.tag_id group by a.id";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setTag_id(rs.getInt("id"));
                articleTag.setTag_name(rs.getString("name"));
                articleTag.setArticle_num(rs.getInt("num"));
                tagList.add(articleTag);
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tagList;
    }

    @Override
    public List<Tag> getTagsByArticleId(int articleId) {
        List<Tag> tagList = new ArrayList<>();
        String sql = "select tag_id, name from t_article_tag a left join t_tag b on a.tag_id = b.id where article_id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,articleId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getInt("tag_id"));
                tag.setName(rs.getString("name"));
                tagList.add(tag);
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tagList;
    }

    @Override
    public List<Tag> getAllTag() {
        List<Tag> tagList = new ArrayList<>();
        String sql = "select * from t_tag order by create_time desc";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getInt("id"));
                tag.setName(rs.getString("name"));
                tagList.add(tag);
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tagList;
    }

    @Override
    public Tag getTagById(int tagId) {
        Tag tag = null;
        String sql = "select * from t_tag where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,tagId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                tag = new Tag();
                tag.setId(rs.getInt("id"));
                tag.setName(rs.getString("name"));
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tag;
    }

    @Override
    public Tag addTag(String name) {
        Tag tag = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new Date();
        String create_time = df.format(date);
        String sql = "insert into t_tag values(null,?,?,null)";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,create_time);
            result = ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result != 0) tag = this.getLastTag();
        return tag;
    }

    @Override
    public Tag getLastTag() {
        Tag tag = null;
        String sql = "select * from t_tag order by id desc limit 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                tag = new Tag();
                tag.setName(rs.getString("name"));
                tag.setId(rs.getInt("id"));
                tag.setCreate_time(rs.getString("create_time"));
                tag.setUpdate_time(rs.getString("update_time"));
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tag;
    }

    @Override
    public Tag getTagByName(String name) {
        Tag tag = null;
        String sql = "select * from t_tag where name = ? order by create_time desc limit 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                tag = new Tag();
                tag.setId(rs.getInt("id"));
                tag.setName(rs.getString("name"));
                tag.setCreate_time(rs.getString("create_time"));
                tag.setUpdate_time(rs.getString("update_time"));
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tag;
    }

    @Override
    public int addArticleTag(int articleId, int tagId) {
        ArticleTag articleTag = new ArticleTag();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new Date();
        String create_time = df.format(date);
        String sql = "insert into t_article_tag values(null,?,?,?,null)";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,articleId);
            ps.setInt(2,tagId);
            ps.setString(3,create_time);
            result = ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArticleTag getArticleTag(int articleId, int tagId) {
        ArticleTag articleTag = null;
        String sql = "select * from t_article_tag where article_id ? and tag_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,articleId);
            ps.setInt(2,tagId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                articleTag = new ArticleTag();
                articleTag.setId(rs.getInt("id"));
                articleTag.setArticle_id(rs.getInt("article_id"));
                articleTag.setTag_id(rs.getInt("tag_id"));
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleTag;
    }

    @Override
    public void deleteArticleTag(int articleId) {
        String sql = "delete from t_article_tag where article_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,articleId);
            ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
