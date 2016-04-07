package by.epam.lab.task1.dao.impl;

import by.epam.lab.task1.dao.UserDAO;
import by.epam.lab.task1.model.User;
import by.epam.lab.task1.model.dto.UserTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDAO implements UserDAO {
    private final static String INSERT_QSER_QUERY="INSERT INTO USERS(USERNAME,LOGIN,PASSWORD) " +
            "VALUES (?,?,?)";
    private UserTO userTO;
    @Autowired
    private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public boolean insertUser(User user) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_QSER_QUERY);
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
        return false;
    }

    @Override
    public User findUserById(long userId) {

        return null;
    }

    @Override
    public boolean updateUserById(User user, long userId) {
        return false;
    }

    @Override
    public boolean deleteUserById(long userId) {
        return false;
    }
}
