package blog.dao;

import blog.model.Comment;

import java.util.List;

public interface CommentDao {

    /**
     * 获取文章评论
     * @return
     */
    List<Comment> getCommentByArticleId(int articleId);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    int addComment(Comment comment);

    /**
     * 删除评论
     */

    int delComment(int commentId);

    /**
     * 点赞评论
     * @param commentId
     * @return
     */
    int starComment(int commentId);

    /**
     * diss评论
     * @param commentId
     * @return
     */
    int disComment(int commentId);

    /**
     * 获取评论
     * @param commentId
     * @return
     */
    Comment getCommentById(int commentId);
}
