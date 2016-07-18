package by.epam.lab.task.repository.impl;

import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.UserRepository;
import by.epam.lab.task.entity.User;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;
import java.util.Date;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final static Logger logger= Logger.getLogger(UserRepositoryImpl.class);
    private static final String READ_USER_ID_BY_LOGIN_QUERY = "SELECT USER_ID FROM DZINHALA.USERS WHERE LOGIN = :login ";
    private static final String SET_ROLE_QUERY="UPDATE DZINHALA.USERS SET ROLE_ID=:roleId WHERE USER_ID=:userId";


    @Autowired
    private DataSource dataSource;

    /**
     * Implementation of UserRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(User user) throws DAOException {
        logger.debug("Creating user in UserRepositoryImpl");
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
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while setting role id by user id");
                }
            }
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
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try{
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while reading a user");
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
                    logger.error("HibernateException while closing connection");
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
                    logger.error("HibernateException while deleting a user");
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
                    logger.error("Hibernate exception while reading all users");
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
                    logger.error("Hibernate exception while reading user's id by login");
                }
            }
        }
        return id;
    }



}
