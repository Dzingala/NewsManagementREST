package by.epam.lab.task.repository.impl;


import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.CommentsRepository;
import by.epam.lab.task.entity.Comment;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.utils.hibernate.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentsRepositoryImpl implements CommentsRepository {

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

    private static final Logger logger= Logger.getLogger(CommentsRepositoryImpl.class);
    private static final String FIND_COMMENTS_ID_BY_NEWS_ID_QUERY = " SELECT COMMENT_ID FROM DZINHALA.COMMENTS WHERE NEWS_ID = :newsId";


    /**
     * Implementation of CommentsRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(Comment comment) throws DAOException {
        logger.debug("Creating comment in CommentsRepositoryImpl");
        Session session=null;
        Long commentId=null;
        try{
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            commentId=(Long)session.save(comment);
            session.getTransaction().commit();
        }catch (Exception e) {
            logger.error("SQLException while creating comment in CommentsRepositoryImpl");
            logger.debug("Comment was not created");
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
        return commentId;
    }
    /**
     * Implementation of CommentsRepository method read.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     * @see by.epam.lab.task.exceptions.dao.NoSuchEntityException
     */
    @Override
    public Comment read(Long commentId) throws DAOException {
        logger.debug("Reading a comment in CommentsRepositoryImpl");
        Session session=null;
        Comment comment = null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            comment=(Comment) session.load(Comment.class,commentId);
        }catch (Exception e){
            logger.error("DAOException while reading a comment in CommentRepositoryImpl");
            logger.debug("Comment was not read");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try{
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while reading a comment: "+e);
                }
            }
        }
        if(comment==null){
            logger.debug("Here is no comment with id="+commentId);
            throw new NoSuchEntityException();
        }
        return comment;
    }
    /**
     * Implementation of CommentsRepository method update.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void update(Long id, Comment comment) throws DAOException {
        logger.debug("Updating comment in CommentsRepositoryImpl");
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            comment.setId(id);
            session.update(comment);
            logger.debug("Comment update operation is completed");
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("DAOException while updating comment in CommentRepositoryImpl");
            logger.debug("Comment was not updated");
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
     * Implementation of CommentsRepository method delete.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void delete(Long id) throws DAOException {
        logger.debug("Deleting comment in CommentsRepositoryImpl");
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Comment toDeleteComment = (Comment) session.load(Comment.class,id);
            session.delete(toDeleteComment);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("DAOException while deleting a comment in CommentRepositoryImpl");
            logger.debug("Comment was not deleted");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while deleting a comment: "+e);
                }
            }
        }
    }
    /**
     * Implementation of CommentsRepository method readAll.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     * @see by.epam.lab.task.exceptions.dao.NoSuchEntityException
     */
    @Override
    public List<Comment> readAll() throws DAOException {
        logger.debug("Reading all comments in CommentsRepositoryImpl");
        Session session= null;
        List<Comment> comments = new ArrayList<>();
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            comments=session.createCriteria(Comment.class).list();
        }catch (Exception e){
            logger.error("DAOException while reading all comments in AuthorRepositoryImpl");
            logger.debug("Comments was not read");
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading all comments: "+e);
                }
            }
        }
        return comments;
    }
    /**
     * Implementation of CommentsRepository method readCommentsIdByNewsId.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public List<Long> readCommentsIdByNewsId(Long newsId) throws DAOException {
        logger.debug("Reading comments id list in CommentsRepositoryImpl");
        List<Long> commentsIds = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<BigDecimal> bigDecCommentIds = (List<BigDecimal>) session.createSQLQuery(FIND_COMMENTS_ID_BY_NEWS_ID_QUERY).setParameter("newsId", newsId).list();

            commentsIds.addAll(bigDecCommentIds.stream().map(BigDecimal::longValue).collect(Collectors.toList()));
        }catch (Exception e){
            logger.error("DAOException while getting comments list in CommentsRepositoryImpl");
            logger.debug("Comments list was not read");
            throw new DAOException(e);
        }finally {
            if(session!=null && session.isOpen()){
                try{
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading comments' ids by news id: "+e);
                }
            }
        }
        return commentsIds;
    }


}
