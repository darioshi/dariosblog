package blog.utils;

import blog.model.*;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

public class Form2Bean {

	private static boolean vilidate(Comment c) {
		boolean result = true;

		if (c.getArticle_id() == 0 || StringUtils.isEmpty(c.getContent())) {
			result = false;
		}
		return result;
	}

	public static Article articleForm2Bean(HttpServletRequest request, int sort_id) throws FailException {

		Map value = new HashMap();

		String title = request.getParameter("title");
		String content = request.getParameter("content");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int user_id = 1;
        if(user != null) {
            user_id = user.getId();
        }
        // 初始化时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

		value.put("title", title);
		value.put("user_id", user_id);
		value.put("sort_id", sort_id);
		value.put("status", 1);
		value.put("content", content);
		value.put("star", 0);
		value.put("comment", 0);
		value.put("create_time", df.format(date));
        value.put("visit", 0);

		Article bean = new Article();

		try {
			BeanUtils.populate(bean, value);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return bean;
	}

	public static Sort sortForm2bean(HttpServletRequest request) {
	    Map map = new HashMap();
	    Sort sort = null;
	    String sort_name = request.getParameter("sort").trim();;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String create_time = df.format(date);
        map.put("name",sort_name);
        map.put("create_time",create_time);
        sort = new Sort();
        try {
            BeanUtils.populate(sort,map);
        }catch (Exception e) {
            e.printStackTrace();
        }
	    return sort;
    }

    public static Comment commentForm2bean(HttpServletRequest request) {
	    Map map = new HashMap();
	    Comment comment = null;
	    int articleId = Integer.parseInt(request.getParameter("article_id"));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String create_time = df.format(date);
        map.put("article_id", articleId);
        map.put("create_time", create_time);
        map.put("content", request.getParameter("w_content"));
        map.put("email", request.getParameter("w_email"));
        map.put("nickname", request.getParameter("w_nickname"));
        comment = new Comment();
        try {
            BeanUtils.populate(comment,map);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return comment;
    }

    public static List<Tag> tagForm2bean(HttpServletRequest request) {
        List<Tag> tagList = new ArrayList<>();
        String tags_str = request.getParameter("sort").trim();
        String[] tags = tags_str.split(" ");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String create_time = df.format(date);
        for (String tag : tags) {
            Map map = new HashMap();
            map.put("name",tag);
            map.put("create_time",create_time);
            Tag t = new Tag();
            try {
                BeanUtils.populate(t,map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tagList.add(t);
        }
        return tagList;
    }
}
