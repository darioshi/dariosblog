package blog.utils;

import blog.model.Comment;

import java.util.List;

public class CommentUtils {

    public static List cutTime(List<Comment> commentList) {
        for (Comment comment : commentList) {
            comment.setCreate_time(comment.getCreate_time().substring(0, 16));
        }
        return commentList;
    }
}
