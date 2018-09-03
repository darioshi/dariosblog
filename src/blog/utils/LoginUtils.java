package blog.utils;

import blog.dao.UserDao;
import blog.daoImpl.UserDaoImpl;
import blog.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtils {
    public static boolean login(HttpServletRequest request) {

        //获取前端传来的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //判断用户名和密码是否为空
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) return false;

        //获取UserDao对象进行登录
        UserDao dao = UserDaoImpl.getInstance();
        User user = dao.login(username, password);
        if(user == null) return false;

        //写入session
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        return true;
    }
}
