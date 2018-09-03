package blog.service;

import blog.dao.UserDao;
import blog.daoImpl.UserDaoImpl;
import blog.model.User;

public class UserService {

    private UserDao userDao;

    private static UserService instance;

    private UserService() {
        userDao = UserDaoImpl.getInstance();
    }

    public static UserService getInstance() {
        if(instance == null) {
            try {
                instance = new UserService();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
}
