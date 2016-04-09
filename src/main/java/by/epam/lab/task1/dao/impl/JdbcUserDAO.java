package by.epam.lab.task1.dao.impl;

import by.epam.lab.task1.dao.UserDAO;
import by.epam.lab.task1.model.User;
import by.epam.lab.task1.model.dto.UserTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class JdbcUserDAO implements UserDAO {
    private final static Logger logger= Logger.getLogger(JdbcUserDAO.class);
    private final static String CREATE_USER_QUERY="INSERT INTO DZINHALA.USERS(USER_NAME,LOGIN,PASSWORD) VALUES (?,?,?)";
    private final static String READ_USER_QUERY="SELECT * FROM DZINHALA.USERS WHERE USER_ID=?";
    private final static String UPDATE_USER_QUERY="UPDATE DZINHALA.USERS SET USER_NAME=?, LOGIN=?, PASSWORD=?  WHERE USER_ID=?";
    private final static String DELETE_USER_QUERY="DELETE FROM DZINHALA.USERS WHERE USER_ID=?";

    private UserTO userTO;

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public boolean createUser(User user) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(CREATE_USER_QUERY);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return true;
    }

    @Override
    public User readUserById(long userId) {
        Connection conn = null;
        User user=null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(READ_USER_QUERY);
            ps.setLong(1,userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user= new User();
                user.setUserName(rs.getString("USER_NAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setUserId(rs.getLong("USER_ID"));
                user.setLogin(rs.getString("LOGIN"));
            }
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return user;
    }

    @Override
    public boolean updateUserById(User user) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_USER_QUERY);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getLogin());
            ps.setString(3,user.getPassword());
            ps.setLong(4,user.getUserId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return true;
    }

    @Override
    public boolean deleteUserById(long userId) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_USER_QUERY);
            ps.setLong(1, userId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return true;
    }
}
