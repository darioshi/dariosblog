package blog.daoImpl;

import blog.dao.SortDao;
import blog.db.C3P0Connection;
import blog.model.Sort;
import blog.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SortDaoImpl implements SortDao {

    private Connection conn;

    private static SortDao instance;

    private SortDaoImpl() {
        conn = C3P0Connection.getInstance().getConnection();
    }

    public static SortDao getInstance() {
        if(instance == null) {
            try {
                instance = new SortDaoImpl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public int getCount() {
        int count = 0;
        String sql = "select count(*) as cou from t_sort";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                count = Integer.parseInt(rs.getString("cou"));
            }
            DBUtils.Close(ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List getSortAndCount() {
        List<Sort> sortList = new ArrayList<>();
        String sql = "select count(*) num,b.name,a.sort_id from t_article a left join t_sort b on a.sort_id = b.id group by a.sort_id";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Sort sort = new Sort();
                sort.setId(rs.getInt("sort_id"));
                sort.setName(rs.getString("name"));
                sort.setArticle_num(rs.getInt("num"));
                sortList.add(sort);
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sortList;
    }

    @Override
    public Sort getSortById(int id) {
        Sort sort = null;
        String sql = "select * from t_sort where id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                sort = new Sort();
                sort.setName(rs.getString("name"));
                sort.setId(rs.getInt("id"));
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sort;
    }

    @Override
    public List<Sort> getAllSort() {
        List<Sort> sortList = new ArrayList<>();
        String sql = "select * from t_sort order by create_time desc";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Sort sort = new Sort();
                sort.setId(rs.getInt("id"));
                sort.setName(rs.getString("name"));
                sortList.add(sort);
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sortList;
    }

    @Override
    public Sort addSort(Sort s) {
        Sort sort = null;
        String sql = "insert into t_sort values(null,?,?,null)";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getName());
            ps.setString(2,s.getCreate_time());
            result = ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result != 0) sort =  this.getLastSort();
        return sort;
    }

    public Sort getLastSort() {
        Sort sort = null;
        String sql = "select * from t_sort order by id desc limit 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                sort = new Sort();
                sort.setId(rs.getInt("id"));
                sort.setName(rs.getString("name"));
                sort.setCreate_time(rs.getString("create_time"));
                sort.setUpdate_time(rs.getString("update_time"));
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sort;
    }

    @Override
    public Sort getSortByName(String name) {
        Sort sort = null;
        String sql = "select * from t_sort where name = ? order by id desc limit 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                sort = new Sort();
                sort.setId(rs.getInt("id"));
                sort.setName(rs.getString("name"));
                sort.setCreate_time(rs.getString("create_time"));
                sort.setUpdate_time(rs.getString("update_time"));
            }
            DBUtils.Close(ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sort;
    }

    @Override
    public boolean updateSort(int sortId, String sortName) {
        int result = 1;
        String sql = "update t_sort set name = ? where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,sortName);
            ps.setInt(2,sortId);
            result = ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result <= 0) return false;
        return true;
    }

    @Override
    public boolean deleteSort(int sortId) {
        int result = 1;
        String sql = "delete from t_sort where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,sortId);
            result = ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result <= 0 ) return false;
        return true;
    }
}
