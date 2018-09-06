package blog.daoImpl;

import blog.dao.ArticleDao;
import blog.db.C3P0Connection;
import blog.model.Article;
import blog.utils.DBUtils;
import sun.security.pkcs11.Secmod;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {

    private Connection conn;
    private static ArticleDao instance;

    private ArticleDaoImpl() {
        conn = C3P0Connection.getInstance().getConnection();
    }

    public static ArticleDao getInstance() {
        if(instance == null) {
            try {
                instance = new ArticleDaoImpl();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * 获取文章数
     * @return
     */
    @Override
    public int getCount() {
        int count = 0;
        String sql = "select count(*) as cou from t_article where status = 1";
        Statement stmt;
        try {
            stmt = conn.createStatement();
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
    public List<Article> getArticle() {
        List<Article> articleList = new ArrayList<>();

        String sql = "select a.*,b.name as sort, c.name as author,a.sort_id from t_article a left join t_sort b on a.sort_id = b.id left join t_user c on c.id = a.user_id where status = 1 order by a.create_time desc";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setAuthor(rs.getString("author"));
                article.setSort_name(rs.getString("sort"));
                article.setContent(rs.getString("content"));
                article.setVisit(rs.getInt("visit"));
                article.setCreate_time(rs.getString("create_time"));
                article.setSort_id(rs.getInt("sort_id"));
                articleList.add(article);
            }
            DBUtils.Close(stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    @Override
    public List<Article> getVisitRank() {
        List<Article> articleList = new ArrayList<>();
        String sql = "select * from t_article  where status = 1 order by visit desc limit 10";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setVisit(rs.getInt("visit"));
                articleList.add(article);
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    @Override
    public Article getArticleById(int id) {
        Article article = null;
        String sql = "select * from t_article where id = ? and status = 1";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setUser_id(rs.getInt("user_id"));
                article.setSort_id(rs.getInt("sort_id"));
                article.setStar(rs.getInt("star"));
                article.setComment(rs.getInt("comment"));
                article.setVisit(rs.getInt("visit"));
                article.setCreate_time(rs.getString("create_time"));
                article.setUpdate_time(rs.getString("update_time"));
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public List<Article> getArticleBySortId(int sortId) {
        List<Article> articleList = new ArrayList<>();
        String sql = "select * from t_article where sort_id = ? and status = 1 order by create_time desc";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,sortId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setUser_id(rs.getInt("user_id"));
                article.setSort_id(rs.getInt("sort_id"));
                article.setStar(rs.getInt("star"));
                article.setComment(rs.getInt("comment"));
                article.setVisit(rs.getInt("visit"));
                article.setCreate_time(rs.getString("create_time"));
                article.setUpdate_time(rs.getString("update_time"));
                articleList.add(article);
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    @Override
    public List<Article> getArticleByTagId(int tagId) {
        List<Article> articleList = new ArrayList<>();
        String sql = "select b.* from t_article_tag a left join t_article b on a.article_id = b.id where a.tag_id = ? and b.status = 1 order by b.create_time desc";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,tagId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setUser_id(rs.getInt("user_id"));
                article.setSort_id(rs.getInt("sort_id"));
                article.setStar(rs.getInt("star"));
                article.setComment(rs.getInt("comment"));
                article.setVisit(rs.getInt("visit"));
                article.setCreate_time(rs.getString("create_time"));
                article.setUpdate_time(rs.getString("update_time"));
                articleList.add(article);
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    public Article getLastArticle() {
        Article article = null;
        String sql = "select * from t_article  where status = 1 order by id desc limit 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setUser_id(rs.getInt("user_id"));
                article.setSort_id(rs.getInt("sort_id"));
                article.setStar(rs.getInt("star"));
                article.setComment(rs.getInt("comment"));
                article.setVisit(rs.getInt("visit"));
                article.setCreate_time(rs.getString("create_time"));
                article.setUpdate_time(rs.getString("update_time"));
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public int starArticle(int articleId) {
        String sql = "update t_article set star = star + 1 where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,articleId);
            ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Article article = this.getArticleById(articleId);
        return article.getStar();
    }

    @Override
    public int commentArticle(int articleId) {
        int result = 0;
        String sql = "update t_article set comment = comment + 1 where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,articleId);
            result = ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int desComment(int articleId) {
        String sql = "update t_article set comment = comment - 1 where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,articleId);
            ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Article article = this.getArticleById(articleId);
        return article.getComment();
    }

    @Override
    public void addVisit(int articleId) {
        String sql = "update t_article set visit = visit + 1 where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,articleId);
            ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delArticle(int articleId) {
        int result = 1;
        String sql = "update t_article set status = 0 where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,articleId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result <= 0) return false;
        return true;
    }

    @Override
    public Article updateArticle(int articleId, Article a) {
        Article article = null;
        String sql = "update t_article set title = ?,sort_id = ?,content = ?,update_time = ? where id = ?";
        PreparedStatement ps;
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, a.getTitle());
            ps.setInt(2, a.getSort_id());
            ps.setString(3, a.getContent());
            ps.setString(4, a.getUpdate_time());
            ps.setInt(5, articleId);
            result = ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result != 0)
            article = this.getArticleById(articleId);
        return article;
    }

    @Override
    public void delArticleBySortId(int sortId) {
        String sql = "update t_article set status = 0 where sort_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,sortId);
            ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Article getPreArticle(int id) {
        Article article = null;
        String sql = "select * from t_article where id < ? and status = 1 order by id desc limit 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public Article getNextArticle(int id) {
        Article article = null;
        String sql = "select * from t_article where id > ? and status = 1 order by id asc limit 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public Article addArticle(Article a) {
        Article article = null;
        String sql = "insert into t_article values(null,?,?,?,?,?,?,?,?,?,null)";
        PreparedStatement ps;
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, a.getTitle());
            ps.setInt(2, a.getUser_id());
            ps.setInt(3, a.getSort_id());
            ps.setInt(4, a.getStar());
            ps.setInt(5, a.getComment());
            ps.setInt(6, a.getVisit());
            ps.setString(7, a.getContent());
            ps.setInt(8, a.getStatus());
            ps.setString(9, a.getCreate_time());
            result = ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result != 0)
            article = this.getLastArticle();
        return article;
    }
}
