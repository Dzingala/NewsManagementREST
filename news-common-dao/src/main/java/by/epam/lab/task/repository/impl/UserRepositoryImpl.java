package by.epam.lab.task.repository.impl;

import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.UserRepository;
import by.epam.lab.task.entity.User;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.utils.hibernate.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
/**
 * Represents the means of manipulating with User entity and database.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger= Logger.getLogger(UserRepositoryImpl.class);
    private static final String READ_USER_ID_BY_LOGIN_QUERY = "SELECT USER_ID FROM DZINHALA.USERS WHERE LOGIN = :login ";
    @Value("${db.user}")
    private String DBUSER;
    @Value("${db.driver}")
    private String DBDRIVER;
    @Value("${db.url}")
    private String DBURL;
    @Value("${db.password}")
    private String DBPASSWORD;
    @Value("\nDriver: ${db.driver}\nUrl: ${db.url}\nUsername: ${db.user}\nPassword: ${db.password}")
    public void setDriverClassName(String dbConfigString){
        logger.debug("Connected to the database:");
        logger.debug(dbConfigString);
        logger.debug("@Value data:\n" +
                        "Driver: "+DBDRIVER+"\n" +
                        "Url: "+DBURL+"\n" +
                        "Username: "+ DBUSER+"\n" +
                        "Password: "+DBPASSWORD
        );
    }
    /**
     * Implementation of UserRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(User user) throws DAOException {
        logger.debug("Creating user in UserRepositoryImpl");
        Session session=null;
        Long userId=null;
        try{
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            userId=(Long)session.save(user);
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
                    logger.error("HibernateException while creating a user: "+e);
                }
            }
        }

        return userId;
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
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try{
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while reading a user: "+e);
                }
            }
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
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while closing connection: "+e);
                }
            }
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
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while deleting a user: "+e);
                }
            }
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
                    logger.error("Hibernate exception while reading all users: "+e);
                }
            }
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
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading user's id by login: "+e);
                }
            }
        }
        return id;
    }



}