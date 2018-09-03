package blog.service;

import blog.dao.ArticleDao;
import blog.dao.CommentDao;
import blog.daoImpl.ArticleDaoImpl;
import blog.daoImpl.CommentDaoImpl;
import blog.model.Comment;
import blog.utils.CommentUtils;
import blog.utils.FailException;
import blog.utils.Form2Bean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommentService {
    private CommentDao commentDao;

    private static CommentService instance;

    private CommentService() {
        commentDao = CommentDaoImpl.getInstance();
    }

    public static CommentService getInstance() {
        if(instance == null) {
            try {
                instance = new CommentService();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * 获取文章评论
     * @param articleId
     * @return
     */
    public List<Comment> getCommentByArticleId(int articleId) {
        List<Comment> commentList = commentDao.getCommentByArticleId(articleId);
        if(commentList != null) {
            CommentUtils.cutTime(commentList);
        }
        return commentList;
    }

    public int addComment(HttpServletRequest request) {
        Comment comment = null;
        comment = Form2Bean.commentForm2bean(request);
        if(comment == null) return 0;
        int result = commentDao.addComment(comment);
        if( result != 0 ) {
            ArticleDao articleDao = ArticleDaoImpl.getInstance();
            return articleDao.commentArticle(Integer.parseInt(request.getParameter("article_id")));
        }
        return 0;
    }

    public int delComment(int articleId, int commentId) {
        int res = commentDao.delComment(commentId);
        if(res != 0) {
            ArticleDao articleDao = ArticleDaoImpl.getInstance();
            return articleDao.desComment(articleId);
        }
        return -1;
    }

    public int starComment(int commentId) {
        return commentDao.starComment(commentId);
    }

    public int dissComment(int commentId) {
        return commentDao.disComment(commentId);
    }
}
