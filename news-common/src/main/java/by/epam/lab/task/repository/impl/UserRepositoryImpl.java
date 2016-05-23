package by.epam.lab.task.repository.impl;

import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.UserRepository;
import by.epam.lab.task.entity.User;
import by.epam.lab.task.exceptions.dao.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final static Logger logger= Logger.getLogger(UserRepositoryImpl.class);
    private static final String CREATE_USER_QUERY = " INSERT INTO DZINHALA.USERS " +
            "(USER_NAME,LOGIN,PASSWORD) VALUES (?,?,?) ";
    private static final String READ_USER_QUERY = " SELECT USER_ID, USER_NAME," +
            " LOGIN, PASSWORD,ROLE_ID FROM DZINHALA.USERS WHERE USER_ID = ? ";
    private static final String UPDATE_USER_QUERY = " UPDATE DZINHALA.USERS SET USER_NAME = ?," +
            " LOGIN = ?,PASSWORD = ? WHERE USER_ID = ? ";
    private static final String DELETE_USER_QUERY = " DELETE FROM DZINHALA.USERS  WHERE USER_ID = ? ";
    private static final String READ_USER_ID_BY_LOGIN_QUERY = "SELECT USER_ID FROM DZINHALA.USERS WHERE LOGIN = ? ";
    private static final String READ_ALL_USERS_QUERY=" SELECT USER_ID, USER_NAME," +
            " LOGIN, PASSWORD,ROLE_ID FROM DZINHALA.USERS";
    private static final String SET_ROLE_QUERY="UPDATE DZINHALA.USERS SET ROLE_ID=? WHERE USER_ID=?";

    private static final String ORDER_BY_COMMENTS_QUERY = "SELECT NEWS.NEWS_ID,COUNT(NEWS.NEWS_ID) " +
            "NEWS_COUNT FROM DZINHALA.NEWS NS JOIN COMMENTS CS ON NS.NEWS_ID = CS.NEWS_ID " +
            "GROUP BY CS.NEWS_ID ORDER BY NEWS_COUNT DESC";


    private static final String COLUMN_NAME_ROLE_ID = "ROLE_ID";
    private static final String COLUMN_NAME_USER_NAME = "USER_NAME";
    private static final String COLUMN_NAME_USER_LOGIN = "LOGIN";
    private static final String COLUMN_NAME_USER_PASSWORD = "PASSWORD";
    private static final String COLUMN_NAME_USER_ID = "USER_ID";


    @Autowired
    private DataSource dataSource;

    /**
     * Implementation of UserRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(User user) throws DAOException {
        logger.debug("Creating user in UserRepositoryImpl");
        Connection conn =null;
        Long userId=null;
        String[] keys = {COLUMN_NAME_USER_ID};
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(CREATE_USER_QUERY, keys)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getLogin());
                ps.setString(3, user.getPassword());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                    userId = rs.getLong(1);
                    logger.debug("User id="+userId+" was created");
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while creating user in UserRepositoryImpl");
            logger.debug("User was not created");
            throw new DAOException(e);
        }
        return userId;
    }
    /**
     * Implementation of UserRepository method setRoleIdById.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void setRoleIdById(Long userId, Long roleId) throws DAOException {
        logger.debug("Setting role to user in UserRepositoryImpl");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(SET_ROLE_QUERY)) {
                ps.setLong(1, roleId);
                ps.setLong(2, userId);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while setting role to user in UserRepositoryImpl");
            logger.debug("Role was not set to user");
            throw new DAOException(e);
        }
    }
    /**
     * Implementation of UserRepository method read.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     * @see by.epam.lab.task.exceptions.dao.NoSuchEntityException
     */
    @Override
    public User read(Long userId) throws DAOException {
        logger.debug("Reading user in UserRepositoryImpl");
        Connection conn = null;
        User user = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_USER_QUERY)) {
                ps.setLong(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String password=rs.getString(COLUMN_NAME_USER_PASSWORD);
                        user = new User(
                                rs.getLong(COLUMN_NAME_ROLE_ID),
                                userId,
                                rs.getString(COLUMN_NAME_USER_NAME),
                                rs.getString(COLUMN_NAME_USER_LOGIN),
                                rs.getString(COLUMN_NAME_USER_PASSWORD)
                        );
                        user.setReadyPassword(password);
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading user in UserRepositoryImpl");
            logger.debug("User was not read");
            throw new DAOException(e);
        }
        if(user==null){
            logger.debug("There is no user with id="+userId);
            throw new NoSuchEntityException("There is no user with id="+userId);
        }
        return user;
    }
    /**
     * Implementation of UserRepository method update.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void update(Long id, User user) throws DAOException {
        logger.debug("Updating user in UserRepositoryImpl");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(UPDATE_USER_QUERY)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getLogin());
                ps.setString(3, user.getPassword());
                ps.setLong(4, id);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while updating user in UserRepositoryImpl");
            logger.debug("User was not updated");
            throw new DAOException(e);
        }
    }
    /**
     * Implementation of UserRepository method delete.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void delete(Long id) throws DAOException {
        logger.debug("Deleting user in UserRepositoryImpl");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(DELETE_USER_QUERY)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while deleting user in UserRepositoryImpl");
            logger.debug("User was not updated");
            throw new DAOException(e);
        }
    }
    /**
     * Implementation of UserRepository method readAll.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public ArrayList<User> readAll() throws DAOException {
        logger.debug("Reading all users in UserRepositoryImpl");
        Connection conn = null;
        ArrayList<User> users = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_ALL_USERS_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        users= new ArrayList<>();
                        String password=rs.getString(COLUMN_NAME_USER_PASSWORD);
                        User user = new User(
                                rs.getLong(COLUMN_NAME_ROLE_ID),
                                rs.getLong(COLUMN_NAME_USER_ID),
                                rs.getString(COLUMN_NAME_USER_NAME),
                                rs.getString(COLUMN_NAME_USER_LOGIN),
                                rs.getString(COLUMN_NAME_USER_PASSWORD)
                        );
                        user.setReadyPassword(password);
                        users.add(user);
                        while (rs.next()){
                            String password1=rs.getString(COLUMN_NAME_USER_PASSWORD);
                            User user1 = new User(
                                    rs.getLong(COLUMN_NAME_ROLE_ID),
                                    rs.getLong(COLUMN_NAME_USER_ID),
                                    rs.getString(COLUMN_NAME_USER_NAME),
                                    rs.getString(COLUMN_NAME_USER_LOGIN),
                                    rs.getString(COLUMN_NAME_USER_PASSWORD)
                            );
                            user.setReadyPassword(password1);
                            users.add(user1);
                        }
                    }
                    else{
                        logger.debug("There are no users registered in database");
                        throw new NoSuchEntityException("There are no users registered in database");
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading user in UserRepositoryImpl");
            logger.debug("Users was not read");
            throw new DAOException(e);
        }
        return users;
    }
    /**
     * Implementation of UserRepository method readIdByLogin.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     * @see by.epam.lab.task.exceptions.dao.NoSuchEntityException
     */

    @Override
    public Long readIdByLogin(String login) throws DAOException {
        logger.debug("Reading user's id by login in UserRepositoryImpl");
        Connection conn=null;
        Long userId = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_USER_ID_BY_LOGIN_QUERY)) {
                ps.setString(1, login);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        userId = rs.getLong(COLUMN_NAME_USER_ID);
                    }else {
                        logger.debug("Here is no user login="+login);
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            logger.error("DAOException while reading user id by login in UserRepositoryImpl");
            logger.debug("User id was not read");
            throw new DAOException(e);
        }
        if (userId == null) {
            logger.debug("Here is no user with login="+login);
            logger.error("NoSuchEntity while reading user id by login in UserRepositoryImpl");
            throw new NoSuchEntityException("User does not exist");

        }
        return userId;
    }



}
