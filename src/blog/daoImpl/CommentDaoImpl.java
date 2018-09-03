package blog.daoImpl;

import blog.dao.CommentDao;
import blog.db.C3P0Connection;
import blog.model.Comment;
import blog.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private Connection conn;

    private static CommentDaoImpl instance;

    private CommentDaoImpl() {
        conn = C3P0Connection.getInstance().getConnection();
    }

    public static CommentDaoImpl getInstance() {
        if(instance == null) {
            try {
                instance = new CommentDaoImpl();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


    @Override
    public List<Comment> getCommentByArticleId(int articleId) {
        List<Comment> commentList = new ArrayList<>();
        String sql = "select * from t_comment where article_id = ? and status = 1";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,articleId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setContent(rs.getString("content"));
                comment.setCreate_time(rs.getString("create_time"));
                comment.setNickname(rs.getString("nickname"));
                comment.setEmail(rs.getString("email"));
                comment.setDiss(rs.getInt("diss"));
                comment.setStar(rs.getInt("star"));
                commentList.add(comment);
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }

    @Override
    public int addComment(Comment comment) {
        int result = 0;
        String sql = "insert into t_comment values (null,?,?,?,?,0,0,?,null,1)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,comment.getArticle_id());
            ps.setString(2,comment.getNickname());
            ps.setString(3,comment.getEmail());
            ps.setString(4,comment.getContent());
            ps.setString(5,comment.getCreate_time());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delComment(int commentId) {
        int result = 0;
        String sql = "update t_comment set status = 0 where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,commentId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int starComment(int commentId) {
        String sql = "update t_comment set star = star + 1 where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,commentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Comment comment = this.getCommentById(commentId);
        return comment.getStar();
    }

    @Override
    public int disComment(int commentId) {
        String sql = "update t_comment set diss = diss + 1 where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,commentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Comment comment = this.getCommentById(commentId);
        return comment.getDiss();
    }

    @Override
    public Comment getCommentById(int commentId) {
        Comment comment = null;
        String sql ="select * from t_comment where id = ? and status = 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,commentId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                comment = new Comment();
                comment.setStar(rs.getInt("star"));
                comment.setDiss(rs.getInt("diss"));
                comment.setEmail(rs.getString("email"));
                comment.setNickname(rs.getString("nickname"));
                comment.setCreate_time(rs.getString("create_time"));
                comment.setContent(rs.getString("content"));
                comment.setId(rs.getInt("id"));
                comment.setArticle_id(rs.getInt("article_id"));
                comment.setUpdate_time(rs.getString("update_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }
}
