package by.epam.lab.task.repository.impl;

import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.UserRepository;
import by.epam.lab.task.entity.User;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.util.HibernateUtil;
import org.apache.log4j.Logger;
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
import org.hibernate.*;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceUtils;
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final static Logger logger= Logger.getLogger(UserRepositoryImpl.class);
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
    private static final String READ_USER_ID_BY_LOGIN_QUERY = "SELECT USER_ID FROM DZINHALA.USERS WHERE LOGIN = :login ";
    private static final String SET_ROLE_QUERY="UPDATE DZINHALA.USERS SET ROLE_ID=:roleId WHERE USER_ID=:userId";
=======
    private static final String CREATE_USER_QUERY = " INSERT INTO DZINHALA.USERS " +
            "(USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (?,?,?,?) ";
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

    @Value("${db.user}")
    private String DBUSER;
    @Value("${db.driver}")
    private String DBDRIVER;
    @Value("${db.url}")
    private String DBURL;
    @Value("${db.password}")
    private String DBPASSWORD;
    @Value("\nDriver: #{dataSource.driverClassName}\nUrl: #{dataSource.url}\nUsername: #{dataSource.username}\nPassword: #{dataSource.password}")
    public void setDriverClassName(String dbConfigString){
        logger.debug("Connected to the database:");
        logger.debug(dbConfigString);
        logger.debug("@Value data got:\n" +
                "Driver: "+DBDRIVER+"\n" +
                "Url: "+DBURL+"\n" +
                "Username: "+ DBUSER+"\n" +
                "Password: "+DBPASSWORD
        );
    }

    @Autowired
    private DataSource dataSource;
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java

    /**
     * Implementation of UserRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(User user) throws DAOException {
        logger.debug("Creating user in UserRepositoryImpl");
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
        Session session=null;
        try{
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception e) {
            logger.error("DAOException while creating user in UserRepositoryImpl");
            logger.debug("User was not created");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while creating a user");
                }
            }
=======
        Connection conn =null;
        Long userId=null;
        String[] keys = {COLUMN_NAME_USER_ID};
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(CREATE_USER_QUERY, keys)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getLogin());
                ps.setString(3, user.getPassword());
                ps.setLong(4, user.getRoleId());
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
            throw new DAOException("DAOException while creating user in UserRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
        }

        return null;
    }
    /**
     * Implementation of UserRepository method setRoleIdById.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void setRoleIdById(Long userId, Long roleId) throws DAOException {
        logger.debug("Setting role to user in UserRepositoryImpl");
        Session session=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query=session.createSQLQuery(SET_ROLE_QUERY);
            query.setParameter("roleId",roleId);
            query.setParameter("userId",userId);
            int res=query.executeUpdate();
        }catch (Exception e) {
            logger.error("DAOException while setting role to user in UserRepositoryImpl");
            logger.debug("Role was not set to user");
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while setting role id by user id");
                }
            }
=======
            throw new DAOException("DAOException while setting role to user in UserRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
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
        Session session=null;
        User user = null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            user=(User)session.load(User.class,userId);
        }catch (Exception e){
            logger.error("DAOException while reading user in UserRepositoryImpl");
            logger.debug("User was not read");
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try{
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while reading a user");
                }
            }
=======
            throw new DAOException("DAOException while reading user in UserRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
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
        Session session = null;
        user.setId(id);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("DAOException while updating user in UserRepositoryImpl");
            logger.debug("User was not updated");
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while closing connection");
                }
            }
=======
            throw new DAOException("DAOException while updating user in UserRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
        }
    }
    /**
     * Implementation of UserRepository method delete.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void delete(Long id) throws DAOException {
        logger.debug("Deleting user in UserRepositoryImpl");
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            User toDeleteUser = (User)session.load(User.class,id);
            session.delete(toDeleteUser);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("DAOException while deleting user in UserRepositoryImpl");
            logger.debug("User was not updated");
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while deleting a user");
                }
            }
=======
            throw new DAOException("DAOException while deleting user in UserRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
        }
    }
    /**
     * Implementation of UserRepository method readAll.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public List<User> readAll() throws DAOException {
        logger.debug("Reading all users in UserRepositoryImpl");
        Session session= null;
        List<User> users=new ArrayList<>();
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            users=session.createCriteria(User.class).list();
        }catch (Exception e){
            logger.error("DAOException while reading all tags in TagRepositoryImpl");
            logger.debug("Authors was not read");
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading all users");
                }
            }
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
=======
        }catch (SQLException e) {
            logger.error("DAOException while reading user in UserRepositoryImpl");
            logger.debug("Users was not read");
            throw new DAOException("DAOException while reading user in UserRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
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
        Long id = null;
        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            BigDecimal bd =(BigDecimal)session.createSQLQuery(READ_USER_ID_BY_LOGIN_QUERY).setString("login",login).uniqueResult();
            id=bd.longValue();
        }catch (Exception e){
            logger.error("DAOException while reading user id by login in UserRepositoryImpl");
            logger.debug("User id was not read");
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading user's id by login");
                }
            }
=======
            throw new DAOException("DAOException while reading user id by login in UserRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/UserRepositoryImpl.java
        }
        return id;
    }



}
