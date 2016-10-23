package by.epam.lab.task.repository.impl;

import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.AuthorRepository;
import by.epam.lab.task.entity.Author;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.utils.hibernate.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class AuthorRepositoryImpl implements AuthorRepository {
    private final static Logger logger= Logger.getLogger(AuthorRepositoryImpl.class);
    private static final String READ_AUTHOR_ID_BY_NEWS_ID_QUERY = " SELECT AUTHOR_ID FROM DZINHALA.NEWS_AUTHOR WHERE NEWS_ID = :newsId ";

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

    /**
     * Implementation of AuthorRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(Author author) throws DAOException {
        logger.debug("Creating author in AuthorRepositoryImpl");
        Session session=null;
        Long authorId=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            authorId=(Long)session.save(author);
            session.getTransaction().commit();
        }catch (Exception e) {
            logger.error("DAOException while creating author in AuthorRepositoryImpl: "+e);
            logger.debug("Author was not created");
            throw new DAOException();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while closing connection: "+e);
                }
            }
        }
        return authorId;
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
                    logger.error("HibernateException while reading an author: "+e);
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
                    logger.error("HibernateException while closing connection: "+e);
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
            Author toDeleteAuthor = (Author)session.get(Author.class,id);
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
                    logger.error("HibernateException while deleting an author: "+e);
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
            logger.error("DAOException while reading all authors in AuthorRepositoryImpl");
            logger.debug("Authors was not read");
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading all authors: "+e);
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
        Long authorId=null;
        Session session=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            BigDecimal bd =(BigDecimal)session.createSQLQuery(READ_AUTHOR_ID_BY_NEWS_ID_QUERY).setParameter("newsId",newsId).uniqueResult();
            authorId=bd.longValue();
        }catch (Exception e){
            logger.error("DAOException while reading author's id by news id in AuthorRepositoryImpl");
            logger.debug("Author's id was not read");
            throw new DAOException(e);
        }finally {
            if(session!=null && session.isOpen()){
                try{
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading author's id by news id: "+e);
                }
            }
        }
        return authorId;
    }




}
