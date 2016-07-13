package by.epam.lab.task.repository.impl;

import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.AuthorRepository;
import by.epam.lab.task.entity.Author;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorRepositoryImpl implements AuthorRepository {
    private final static Logger logger= Logger.getLogger(AuthorRepositoryImpl.class);
    private static final String CREATE_AUTHOR_QUERY= " INSERT INTO DZINHALA.AUTHOR (AUTHOR_NAME) VALUES (?) ";
    private static final String READ_AUTHOR_QUERY= " SELECT AUTHOR_ID,AUTHOR_NAME,EXPIRED FROM DZINHALA.AUTHOR WHERE AUTHOR_ID = ? ";
    private static final String READ_AUTHOR_ID_BY_NEWS_ID_QUERY = " SELECT AUTHOR_ID FROM DZINHALA.NEWS_AUTHOR WHERE NEWS_ID = ? ";
    private static final String UPDATE_AUTHOR_QUERY = " UPDATE DZINHALA.AUTHOR SET AUTHOR_NAME = ? ,EXPIRED = ?  WHERE AUTHOR_ID = ? ";
    private static final String DELETE_AUTHOR_QUERY = " DELETE FROM DZINHALA.AUTHOR WHERE AUTHOR_ID = ? ";
    private static final String READ_ALL_AUTHORS_QUERY="SELECT AUTHOR_ID,AUTHOR_NAME,EXPIRED FROM DZINHALA.AUTHOR";

    private static final String COLUMN_NAME_AUTHOR_ID = "AUTHOR_ID";
    private static final String COLUMN_NAME_AUTHOR_NAME = "AUTHOR_NAME";
    private static final String COLUMN_NAME_EXPIRED = "EXPIRED";



    @Autowired
    private DataSource dataSource;
    /**
     * Implementation of AuthorRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(Author author) throws DAOException {
        logger.debug("Creating author in AuthorRepositoryImpl");
        Session session=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(author);
            session.getTransaction().commit();
        }catch (Exception e) {
            logger.error("DAOException while creating author in AuthorRepositoryImpl");
            logger.debug("Author was not created");
            throw new DAOException();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while closing connection");
                }
            }
        }
        return null;
    }
    /**
     * Implementation of AuthorRepository method read.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     * @see by.epam.lab.task.exceptions.dao.NoSuchEntityException
     */
    @Override
    public Author read(Long authorId) throws DAOException {
        logger.debug("Reading author in AuthorRepositoryImpl");
        Session session=null;
        Author author = null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            author=(Author)session.load(Author.class,authorId);
        }catch (Exception e){
            logger.error("DAOException while reading author in AuthorRepositoryImpl");
            logger.debug("Author was not read");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try{
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while reading an author");
                }
            }
        }
        return author;
    }

    /**
     * Implementation of AuthorRepository method update.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void update(Long id, Author author) throws DAOException {
        logger.debug("Updating author in AuthorRepositoryImpl");
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            author.setId(id);
            session.update(author);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("DAOException while updating author in AuthorRepositoryImpl");
            logger.debug("Author was not updated");
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
     * Implementation of AuthorRepository method delete.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void delete(Long id) throws DAOException {
        logger.debug("Deleting author in AuthorRepositoryImpl");
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Author toDeleteAuthor = (Author)session.load(Author.class,id);
            session.delete(toDeleteAuthor);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("DAOException while deleting author in AuthorRepositoryImpl");
            logger.debug("Author was not deleted");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while deleting an author");
                }
            }
        }

    }
    /**
     * Implementation of AuthorRepository method readAll.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public List<Author> readAll() throws DAOException {
        logger.debug("Reading all authors in AuthorRepositoryImpl");
        Session session= null;
        List<Author> authors=new ArrayList<>();
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            authors=session.createCriteria(Author.class).list();
        }catch (Exception e){
            logger.error("DAOException while reading author in AuthorRepositoryImpl");
            logger.debug("Authors was not read");
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading all authors");
                }
            }
        }
        return authors;
    }
    /**
     * Implementation of AuthorRepository method readAuthorIdByNewsId.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     * @see by.epam.lab.task.exceptions.dao.NoSuchEntityException
     */
    @Override
    public Long readAuthorIdByNewsId(Long newsId) throws DAOException {
        logger.debug("Reading author's id by news id in AuthorRepositoryImpl");
//        Long authorId=null;
//        Session session=null;
//        try{
//            session=HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            authorId=(Long)session.createSQLQuery(READ_AUTHOR_ID_BY_NEWS_ID_QUERY);
//        }
        Connection conn=null;
        Long authorId = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_AUTHOR_ID_BY_NEWS_ID_QUERY)) {
                ps.setLong(1, newsId);
                try (ResultSet resultSet = ps.executeQuery()) {
                    if (resultSet.next()) {
                        authorId = resultSet.getLong(1);
                    }
                    else {
                        logger.debug("News with id="+newsId+" have not author assigned");
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            logger.error("DAOException while reading author's id by news id in AuthorRepositoryImpl");
            logger.debug("Author's id was not received");
            throw new DAOException(e);
        }
        if (authorId == null) {
            throw new DAOException("News id="+newsId+" have not author assigned");
        }
        return authorId;
    }



}
