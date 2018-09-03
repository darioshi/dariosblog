package blog.dao;

import blog.model.User;

public interface UserDao {

    /**
     * 登录
     * @param username
     * @param pssword
     * @return
     */
    User login(String username, String pssword);

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    boolean register(String username, String password);

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    User getUserById(int id);
}
