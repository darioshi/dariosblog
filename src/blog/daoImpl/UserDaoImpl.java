package blog.daoImpl;

import blog.dao.UserDao;
import blog.db.C3P0Connection;
import blog.model.User;
import blog.utils.DBUtils;
import blog.utils.MD5Util;
import org.apache.commons.beanutils.BeanUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private Connection conn;

    private UserDaoImpl() {
        conn = C3P0Connection.getInstance().getConnection();
    }

    //单例模式
    private static UserDao instance;

    public static UserDao getInstance() {
        if(instance == null) {
            try {
                instance = new UserDaoImpl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public User login(String username, String password) {
        User user = null;
        String sql = "select * from t_user where name = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sql = "select * from t_user where name = ? and password = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,username);
                ps.setString(2, MD5Util.encode(password));
                rs = ps.executeQuery();
                if(rs.next()) {
                    Map<String,String> map = new HashMap<String,String>();
                    user = new User();
                    map.put("name", rs.getString("name"));
                    map.put("password", rs.getString("password"));
                    map.put("id", rs.getString("id"));
                    try {
                        BeanUtils.populate(user,map);
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                }
            }
            DBUtils.Close(ps, rs);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean register(String username, String password) {
        return false;
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        String sql = "select * from t_user where id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
