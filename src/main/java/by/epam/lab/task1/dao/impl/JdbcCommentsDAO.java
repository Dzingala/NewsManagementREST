package by.epam.lab.task1.dao.impl;


import by.epam.lab.task1.dao.CommentsDAO;
import by.epam.lab.task1.entity.Comment;
import by.epam.lab.task1.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Component
public class JdbcCommentsDAO implements CommentsDAO{
    private final static Logger logger= Logger.getLogger(JdbcCommentsDAO.class);
    private static final String CREATE_COMMENT_QUERY = " INSERT INTO DZINHALA.COMMENTS (COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (?,?,?)";
    private static final String READ_COMMENT_QUERY = " SELECT COMMENT_ID,NEWS_ID,COMMENT_TEXT,CREATION_DATE FROM DZINHALA.COMMENTS WHERE COMMENT_ID = ?";
    private static final String UPDATE_COMMENT_QUERY = " UPDATE DZINHALA.COMMENTS SET COMMENT_TEXT = ?   WHERE COMMENT_ID = ?";
    private static final String DELETE_COMMENT_QUERY = " DELETE FROM DZINHALA.COMMENTS WHERE COMMENT_ID = ?";
    private static final String FIND_COMMENTS_ID_BY_NEWS_ID_QUERY = " SELECT COMMENT_ID FROM DZINHALA.COMMENTS WHERE NEWS_ID = ?";


    private static final String COLUMN_NAME_NEWS_ID = "NEWS_ID";
    private static final String COLUMN_NAME_COMMENTS_TEXT = "COMMENT_TEXT";
    private static final String COLUMN_NAME_COMMENTS_DATE = "CREATION_DATE";
    private static final String COLUMN_NAME_COMMENT_ID = "NEWS_ID";


    @Autowired
    private DataSource dataSource;

    public Long create(Comment comment) throws DAOException {
        logger.debug("Creating comment in JdbcCommentsDAO");
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
            logger.error("SQLException while creating comment in JdbcCommentsDAO");
            logger.debug("Comment was not created");
            throw new DAOException(e);
        }

        return commentId;
    }

    public Comment read(Long commentId) throws DAOException {
        logger.debug("Reading comment in JdbcCommentsDAO");
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
            logger.error("DAOException while reading comment in JdbcCommentsDAO");
            logger.debug("Comment was not read");
            throw new DAOException(e);
        }
        if(comment==null){
            logger.debug("Here is no comment with id="+commentId);
            throw new DAOException();
        }
        return comment;
    }

    public void update(Long id, Comment comment) throws DAOException {
        logger.debug("Updating comment in JdbcCommentsDAO");
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
            logger.error("DAOException while updating comment in JdbcCommentsDAO");
            logger.debug("Comment was not updated");
            throw new DAOException(e);
        }

    }

    public void delete(Long id) throws DAOException {
        logger.debug("Deleting comment in JdbcCommentsDAO");
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

            throw new DAOException(e);
        }
    }

    @Override
    public ArrayList<Long> readCommentsIdByNewsId(Long newsId) throws DAOException {
        logger.debug("Reading comments id list in JdbcCommentsDAO");
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
            logger.error("DAOException while getting comments list in JdbcCommentsDAO");
            logger.debug("Comments list was not read");
            throw new DAOException(e);
        }
        return commentsIds;
    }

}
