package by.epam.lab.task1.dao.impl;


import by.epam.lab.task1.dao.TagDAO;
import by.epam.lab.task1.entity.Tag;
import by.epam.lab.task1.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Component
public class JdbcTagDAO implements TagDAO{
    private final static Logger logger= Logger.getLogger(JdbcTagDAO.class);
    private static final String CREATE_TAG_QUERY = " INSERT INTO DZINHALA.TAG (TAG_NAME) VALUES (?) ";
    private static final String READ_TAG_QUERY = " SELECT TAG_ID,TAG_NAME FROM DZINHALA.TAG WHERE TAG_ID = ? ";
    private static final String UPDATE_TAG_QUERY = " UPDATE DZINHALA.TAG SET TAG_NAME = ? WHERE TAG_ID = ? ";
    private static final String DELETE_TAG_QUERY = " DELETE FROM DZINHALA.TAG  WHERE TAG_ID = ? ";
    private static final String READ_TAGS_ID_BY_NEWS_ID_QUERY = " SELECT TAG_ID FROM DZINHALA.NEWS_TAG WHERE NEWS_ID = ? ";


    private static final String COLUMN_NAME_TAG_NAME = "TAG_NAME";
    private static final String COLUMN_NAME_TAG_ID = "TAG_ID";
    private static final String COLUMN_NAME_NEWS_TAG_TAG_ID = "TAG_ID";


    @Autowired
    private DataSource dataSource;

    public Long create(Tag tag) throws DAOException {
        logger.debug("Creating tag in JdbcTagDAO");
        Connection conn=null;
        Long tagId=null;
        String[] keys = {COLUMN_NAME_TAG_ID};
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(CREATE_TAG_QUERY, keys)) {
                ps.setString(1, tag.getName());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                    tagId = rs.getLong(1);
                }
            }finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e){
            logger.error("DAOException while creating tag in JdbcTagDAO");
            logger.debug("Tag was not created");
            throw new DAOException();
        }
        return tagId;
    }

    public Tag read(Long tagId) throws DAOException {
        logger.debug("Reading tag in JdbcTagDAO");
        Connection conn=null;
        Tag tag = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_TAG_QUERY)){
                ps.setLong(1, tagId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        tag = new Tag(tagId,
                                rs.getString(COLUMN_NAME_TAG_NAME)
                        );
                    }
                    else{
                        logger.debug("Tag with id="+tagId+" does not exist");
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading tag in JdbcTagDAO");
            logger.debug("Tag was not read");
            throw new DAOException(e);
        }
        return tag;
    }

    public void update(Long id, Tag tag) throws DAOException {
        logger.debug("Updating tag in JdbcTagDAO");
        Connection conn =null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(UPDATE_TAG_QUERY)) {
                ps.setString(1, tag.getName());
                ps.setLong(2, id);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while updating tag in JdbcTagDAO");
            logger.debug("Tag was not updated");
            throw new DAOException(e);
        }
    }

    public void delete(Long id) throws DAOException {
        logger.debug("Deleting tag in JdbcTagDAO");
        Connection conn=null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(DELETE_TAG_QUERY)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while deleting tag in JdbcTagDAO");
            logger.debug("Tag was not deleted");
            throw new DAOException(e);
        }

    }

    @Override
    public ArrayList<Long> readTagsIdByNewsId(Long newsId) throws DAOException {
        Connection conn=null;
        ArrayList<Long> tagsIdList = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_TAGS_ID_BY_NEWS_ID_QUERY)) {
                ps.setLong(1,newsId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        tagsIdList = new ArrayList<>();
                        tagsIdList.add(rs.getLong(COLUMN_NAME_NEWS_TAG_TAG_ID));
                        while(rs.next()){
                            tagsIdList.add(rs.getLong(COLUMN_NAME_NEWS_TAG_TAG_ID));
                        }
                    }else {
                        logger.debug("Here is no tags for news id="+newsId);
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading tags' id by news id in JdbcTagDAO");
            logger.debug("Tags' id was not received");
            throw new DAOException(e);
        }
        if (tagsIdList == null) {
            throw new DAOException("News id="+newsId+" have no tags assigned");
        }
        return tagsIdList;
    }
}
