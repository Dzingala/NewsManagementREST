package by.epam.lab.task1.dao.impl;


import by.epam.lab.task1.dao.NewsDAO;
import by.epam.lab.task1.entity.News;
import by.epam.lab.task1.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

@Component
public class JdbcNewsDAO implements NewsDAO {
    private final static Logger logger= Logger.getLogger(JdbcNewsDAO.class);
    private static final String CREATE_NEWS_QUERY = " INSERT INTO DZINHALA.NEWS " +
            "(TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE," +
            "MODIFICATION_DATE) VALUES (?,?,?,?,?) ";
    private static final String READ_NEWS_BY_ID_QUERY = " SELECT NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE " +
            "FROM DZINHALA.NEWS WHERE NEWS_ID = ? ";
    private static final String UPDATE_NEWS_QUERY = " UPDATE DZINHALA.NEWS SET TITLE = ?," +
            "SHORT_TEXT = ?,FULL_TEXT = ?, CREATION_DATE = ?, " +
            "MODIFICATION_DATE = ? WHERE NEWS_ID = ? ";
    private static final String DELETE_NEWS_QUERY = " DELETE FROM DZINHALA.NEWS  WHERE NEWS_ID = ? ";
    private static final String READ_ALL_NEWS_QUERY = " SELECT * FROM DZINHALA.NEWS";
    private static final String COUNT_NEWS_QUERY = " SELECT COUNT(NEWS_ID) FROM DZINHALA.NEWS";

    private static final String CONNECT_NEWS_TAGS_QUERY = " INSERT INTO DZINHALA.NEWS_TAG (NEWS_ID,TAG_ID)" +
            " VALUES (?, ?) ";
    private static final String CONNECT_NEWS_AUTHOR_QUERY = " INSERT INTO DZINHALA.NEWS_AUTHOR (NEWS_ID,AUTHOR_ID)" +
            " VALUES (?, ?) ";


    private static final String DISCONNECT_NEWS_TAG_QUERY = "  DELETE FROM NEWS_TAG WHERE NEWS_ID = ? AND TAG_ID=?  ";
    private static final String DISCONNECT_NEWS_AUTHOR_QUERY = " DELETE FROM NEWS_AUTHOR WHERE NEWS_ID = ? AND AUTHOR_ID=? ";


    private static final String COLUMN_NAME_ID = "NEWS_ID";
    private static final String COLUMN_NAME_TITLE = "TITLE";
    private static final String COLUMN_NAME_SHORT_TEXT = "SHORT_TEXT";
    private static final String COLUMN_NAME_FULL_TEXT = "FULL_TEXT";
    private static final String COLUMN_NAME_CREATION_DATE = "CREATION_DATE";
    private static final String COLUMN_NAME_MODIFICATION_DATE = "MODIFICATION_DATE";
    @Autowired
    private DataSource dataSource;

    public Long create(News news) throws DAOException {
        logger.debug("Creating news in JdbcNewsDAO");
        Connection conn =null;
        Long newsId=null;
        String[] keys = {COLUMN_NAME_ID};
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(CREATE_NEWS_QUERY, keys)) {

                ps.setString(1, news.getTitle());
                ps.setString(2, news.getShortText());
                ps.setString(3, news.getFullText());
                ps.setTimestamp(4, news.getCreationDate());
                ps.setDate(5, news.getModificationDate());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                    newsId = rs.getLong(1);
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while creating news in JdbcNewsDAO");
            logger.debug("News was not created");
            throw new DAOException(e);
        }

        return newsId;
    }

    public News read(Long newsId) throws DAOException {
        logger.debug("Reading news in JdbcNewsDAO");
        Connection conn = null;
        News news = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_NEWS_BY_ID_QUERY)) {
                ps.setLong(1, newsId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        logger.debug("News with id="+newsId+" was read");
                        news = new News(newsId,
                                rs.getString(COLUMN_NAME_TITLE),
                                rs.getString(COLUMN_NAME_SHORT_TEXT),
                                rs.getString(COLUMN_NAME_FULL_TEXT),
                                rs.getTimestamp(COLUMN_NAME_CREATION_DATE),
                                rs.getDate(COLUMN_NAME_MODIFICATION_DATE)
                        );
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading news in JdbcNewsDAO");
            logger.debug("News was not read");
            throw new DAOException(e);
        }
        return news;
    }


    public void update(Long id, News news) throws DAOException {
        logger.debug("Updating news in JdbcNewsDAO");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();

            try (PreparedStatement ps = conn.prepareStatement(UPDATE_NEWS_QUERY)) {

                ps.setString(1, news.getTitle());
                ps.setString(2, news.getShortText());
                ps.setString(3, news.getFullText());
                ps.setTimestamp(4, news.getCreationDate());
                ps.setDate(5, news.getModificationDate());
                ps.setLong(6, id);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while updating news in JdbcNewsDAO");
            logger.debug("News was not updated");
            throw new DAOException(e);
        }
    }

    public void delete(Long id) throws DAOException {
        logger.debug("Deleting news in JdbcNewsDAO");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(DELETE_NEWS_QUERY)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while updating news in JdbcNewsDAO");
            logger.debug("News was not updated");
            throw new DAOException(e);
        }
    }

    public ArrayList<News> readAll() throws DAOException {
        logger.debug("Reading all news in JdbcNewsDAO");
        ArrayList<News> news = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_ALL_NEWS_QUERY); ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    news=new ArrayList<>();
                    news.add( new News(
                            rs.getLong(COLUMN_NAME_ID),
                            rs.getString(COLUMN_NAME_TITLE),
                            rs.getString(COLUMN_NAME_SHORT_TEXT),
                            rs.getString(COLUMN_NAME_FULL_TEXT),
                            rs.getTimestamp(COLUMN_NAME_CREATION_DATE),
                            rs.getDate(COLUMN_NAME_MODIFICATION_DATE)
                    ));
                    while (rs.next()){
                        news.add( new News(
                                rs.getLong(COLUMN_NAME_ID),
                                rs.getString(COLUMN_NAME_TITLE),
                                rs.getString(COLUMN_NAME_SHORT_TEXT),
                                rs.getString(COLUMN_NAME_FULL_TEXT),
                                rs.getTimestamp(COLUMN_NAME_CREATION_DATE),
                                rs.getDate(COLUMN_NAME_MODIFICATION_DATE)
                        ));
                    }
                }else {
                    logger.debug("Here is no news in the database");
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading all news in JdbcNewsDAO");
            logger.debug("News was not read");
            throw new DAOException(e);
        }
        return news;
    }

    public Long countNews() throws DAOException {
        Long newsAmount = null;
        logger.debug("Counting news in JdbcNewsDAO");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(COUNT_NEWS_QUERY);ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    newsAmount = rs.getLong(1);
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while counting news in JdbcNewsDAO");
            logger.debug("News was not counted");
            throw new DAOException(e);
        }
        return newsAmount;
    }

    public void connectNewsWithTags(Long newsId, Long tagId) throws DAOException {
        logger.debug("Connecting news with tags in JdbcNewsDAO");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(CONNECT_NEWS_TAGS_QUERY)) {
                ps.setLong(1, newsId);
                ps.setLong(2, tagId);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            logger.error("DAOException while connecting news with tags in JdbcNewsDAO");
            logger.debug("News was not connected with tags");
            throw new DAOException(e);
        }

    }

    public void connectNewsWithAuthor(Long newsId, Long authorId) throws DAOException {
        logger.debug("Connecting news with author in JdbcNewsDAO");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(CONNECT_NEWS_AUTHOR_QUERY)) {
                ps.setLong(1, newsId);
                ps.setLong(2, authorId);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            logger.error("DAOException while connecting news with author in JdbcNewsDAO");
            logger.debug("News was not connected with author");
            throw new DAOException(e);
        }

    }

    public void disconnectNewsWithTag(Long newsId,Long tagId) throws DAOException {
        logger.debug("Disconnecting news with tags in JdbcNewsDAO");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement st = conn.prepareStatement(DISCONNECT_NEWS_TAG_QUERY)) {
                st.setLong(1, newsId);
                st.setLong(2,tagId);
                st.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while disconnecting news with tags in JdbcNewsDAO");
            logger.debug("News was not disconnected with tags");
            throw new DAOException(e);
        }

    }

    public void disconnectNewsWithAuthor(Long newsId, Long authorId) throws DAOException {
        logger.debug("Disconnecting news with author in JdbcNewsDAO");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(DISCONNECT_NEWS_AUTHOR_QUERY)) {
                ps.setLong(1, newsId);
                ps.setLong(2, authorId);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            logger.error("DAOException while disconnecting news with author in JdbcNewsDAO");
            logger.debug("News was not disconnected with author");
            throw new DAOException(e);
        }

    }
}
