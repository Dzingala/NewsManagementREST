package by.epam.lab.task.repository.impl;


import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.CommentsRepository;
import by.epam.lab.task.entity.Comment;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.util.HibernateUtil;
import org.apache.log4j.Logger;
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
import org.hibernate.HibernateException;
import org.hibernate.Session;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceUtils;
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentsRepositoryImpl implements CommentsRepository {
    private final static Logger logger= Logger.getLogger(CommentsRepositoryImpl.class);
    private static final String FIND_COMMENTS_ID_BY_NEWS_ID_QUERY = " SELECT COMMENT_ID FROM DZINHALA.COMMENTS WHERE NEWS_ID = :newsId";


<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
=======
    private static final String COLUMN_NAME_NEWS_ID = "NEWS_ID";
    private static final String COLUMN_NAME_COMMENTS_TEXT = "COMMENT_TEXT";
    private static final String COLUMN_NAME_COMMENTS_DATE = "CREATION_DATE";
    private static final String COLUMN_NAME_COMMENT_ID = "NEWS_ID";

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

>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
    /**
     * Implementation of CommentsRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(Comment comment) throws DAOException {
        logger.debug("Creating comment in CommentsRepositoryImpl");
        Session session=null;
        try{
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(comment);
            session.getTransaction().commit();
        }catch (Exception e) {
            logger.error("SQLException while creating comment in CommentsRepositoryImpl");
            logger.debug("Comment was not created");
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
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
            throw new DAOException("SQLException while creating comment in CommentsRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
        }
        return null;
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
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try{
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while reading a comment");
                }
            }
=======
            throw new DAOException("DAOException while reading comment in CommentsRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
        }
        if(comment==null){
            logger.debug("Here is no comment with id="+commentId);
            throw new NoSuchEntityException("Here is no comment with id="+commentId);
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
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
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
            throw new DAOException("DAOException while updating comment in CommentsRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
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
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
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
                    logger.error("HibernateException while deleting a comment");
                }
            }
=======
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(DELETE_COMMENT_QUERY)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while deleting a comment in CommentsRepositoryImpl");
            logger.debug("Comment was not updated");
            throw new DAOException("DAOException while deleting a comment in CommentsRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
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
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
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
                    logger.error("Hibernate exception while reading all comments");
                }
            }
=======
        Connection conn=null;
        ArrayList<Comment> comments = null;
        try {
            conn = dataSource.getConnection();

            try (PreparedStatement ps = conn.prepareStatement(READ_ALL_COMMENTS_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        comments=new ArrayList<>();
                        comments.add(new Comment(rs.getLong(COLUMN_NAME_COMMENT_ID),
                                rs.getLong(COLUMN_NAME_NEWS_ID),
                                rs.getString(COLUMN_NAME_COMMENTS_TEXT),
                                rs.getTimestamp(COLUMN_NAME_COMMENTS_DATE)
                        ));
                        while (rs.next()){
                            comments.add(new Comment(rs.getLong(COLUMN_NAME_COMMENT_ID),
                                    rs.getLong(COLUMN_NAME_NEWS_ID),
                                    rs.getString(COLUMN_NAME_COMMENTS_TEXT),
                                    rs.getTimestamp(COLUMN_NAME_COMMENTS_DATE)
                            ));
                        }
                    }
                    else{
                        logger.debug("There are no comments in database");
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading all comments in CommentsRepositoryImpl");
            logger.debug("Comments was not read");
            throw new DAOException("DAOException while reading all comments in CommentsRepositoryImpl",e);
        }
        if(comments==null){
            logger.debug("There are no comments in database");
            throw new NoSuchEntityException("There are no comments in database");
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
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
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
            throw new DAOException(e);
        }finally {
            if(session!=null && session.isOpen()){
                try{
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading comments' ids by news id");
                }
            }
=======
            throw new DAOException("DAOException while getting comments list in CommentsRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/CommentsRepositoryImpl.java
        }
        return commentsIds;
    }

}
