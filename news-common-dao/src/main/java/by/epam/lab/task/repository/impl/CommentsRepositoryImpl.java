package by.epam.lab.task.repository.impl;


import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.CommentsRepository;
import by.epam.lab.task.entity.Comment;
import by.epam.lab.task.exceptions.dao.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Component
public class CommentsRepositoryImpl implements CommentsRepository {
    private final static Logger logger= Logger.getLogger(CommentsRepositoryImpl.class);
    private static final String CREATE_COMMENT_QUERY = " INSERT INTO DZINHALA.COMMENTS (COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (?,?,?)";
    private static final String READ_COMMENT_QUERY = " SELECT COMMENT_ID,NEWS_ID,COMMENT_TEXT,CREATION_DATE FROM DZINHALA.COMMENTS WHERE COMMENT_ID = ?";
    private static final String UPDATE_COMMENT_QUERY = " UPDATE DZINHALA.COMMENTS SET COMMENT_TEXT = ?   WHERE COMMENT_ID = ?";
    private static final String DELETE_COMMENT_QUERY = " DELETE FROM DZINHALA.COMMENTS WHERE COMMENT_ID = ?";
    private static final String FIND_COMMENTS_ID_BY_NEWS_ID_QUERY = " SELECT COMMENT_ID FROM DZINHALA.COMMENTS WHERE NEWS_ID = ?";
    private static final String READ_ALL_COMMENTS_QUERY="SELECT COMMENT_ID,NEWS_ID,COMMENT_TEXT,CREATION_DATE FROM DZINHALA.COMMENTS";


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

    /**
     * Implementation of CommentsRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(Comment comment) throws DAOException {
        logger.debug("Creating comment in CommentsRepositoryImpl");
        Connection conn = null;
        Long commentId=null;
        String[] keys = {COLUMN_NAME_NEWS_ID};
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(CREATE_COMMENT_QUERY, keys)) {
                ps.setString(1, comment.getText());
                ps.setTimestamp(2, comment.getCreationDate());
                ps.setLong(3,comment.getNewsId());
                ps.executeUpdate();
                try (ResultSet resultSet = ps.getGeneratedKeys()) {
                    resultSet.next();
                    commentId = resultSet.getLong(1);
                    logger.debug("Comment with id="+commentId+" was created");
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("SQLException while creating comment in CommentsRepositoryImpl");
            logger.debug("Comment was not created");
            throw new DAOException("SQLException while creating comment in CommentsRepositoryImpl",e);
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
        logger.debug("Reading comment in CommentsRepositoryImpl");
        Connection conn=null;
        Comment comment = null;
        try {
            conn = dataSource.getConnection();

            try (PreparedStatement ps = conn.prepareStatement(READ_COMMENT_QUERY)) {
                ps.setLong(1, commentId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        comment = new Comment(commentId,
                                rs.getLong(COLUMN_NAME_NEWS_ID),
                                rs.getString(COLUMN_NAME_COMMENTS_TEXT),
                                rs.getTimestamp(COLUMN_NAME_COMMENTS_DATE)
                        );
                    }
                    else{
                        logger.debug("Comment with id="+commentId+" does not exist");
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading comment in CommentsRepositoryImpl");
            logger.debug("Comment was not read");
            throw new DAOException("DAOException while reading comment in CommentsRepositoryImpl",e);
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
        Connection conn=null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(UPDATE_COMMENT_QUERY)) {
                ps.setString(1, comment.getText());
                ps.setLong(2, id);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while updating comment in CommentsRepositoryImpl");
            logger.debug("Comment was not updated");
            throw new DAOException("DAOException while updating comment in CommentsRepositoryImpl",e);
        }

    }
    /**
     * Implementation of CommentsRepository method delete.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void delete(Long id) throws DAOException {
        logger.debug("Deleting comment in CommentsRepositoryImpl");
        Connection conn=null;
        try {
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
        }
    }
    /**
     * Implementation of CommentsRepository method readAll.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     * @see by.epam.lab.task.exceptions.dao.NoSuchEntityException
     */
    @Override
    public ArrayList<Comment> readAll() throws DAOException {
        logger.debug("Reading all comments in CommentsRepositoryImpl");
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
        }
        return comments;
    }
    /**
     * Implementation of CommentsRepository method readCommentsIdByNewsId.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public ArrayList<Long> readCommentsIdByNewsId(Long newsId) throws DAOException {
        logger.debug("Reading comments id list in CommentsRepositoryImpl");
        Connection conn=null;
        ArrayList<Long> commentsIds = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(FIND_COMMENTS_ID_BY_NEWS_ID_QUERY)) {
                ps.setLong(1,newsId);
                commentsIds = new ArrayList<>();
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        commentsIds.add(resultSet.getLong(COLUMN_NAME_COMMENT_ID));
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while getting comments list in CommentsRepositoryImpl");
            logger.debug("Comments list was not read");
            throw new DAOException("DAOException while getting comments list in CommentsRepositoryImpl",e);
        }
        return commentsIds;
    }

}
