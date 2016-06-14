package by.epam.lab.task.repository.impl;


import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.TagRepository;
import by.epam.lab.task.entity.Tag;
import by.epam.lab.task.exceptions.dao.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Component
public class TagRepositoryImpl implements TagRepository {
    private final static Logger logger= Logger.getLogger(TagRepositoryImpl.class);
    private static final String CREATE_TAG_QUERY = " INSERT INTO DZINHALA.TAG (TAG_NAME) VALUES (?) ";
    private static final String READ_TAG_QUERY = " SELECT TAG_ID,TAG_NAME FROM DZINHALA.TAG WHERE TAG_ID = ? ";
    private static final String UPDATE_TAG_QUERY = " UPDATE DZINHALA.TAG SET TAG_NAME = ? WHERE TAG_ID = ? ";
    private static final String DELETE_TAG_QUERY = " DELETE FROM DZINHALA.TAG  WHERE TAG_ID = ? ";
    private static final String READ_TAGS_ID_BY_NEWS_ID_QUERY = " SELECT TAG_ID FROM DZINHALA.NEWS_TAG WHERE NEWS_ID = ? ";
    private static final String READ_ALL_TAGS_QUERY="SELECT TAG_ID, TAG_NAME FROM DZINHALA.TAG";
    private static final String READ_NEWS_ID_BY_TAG_ID_QUERY = " SELECT NEWS_ID FROM DZINHALA.NEWS_TAG WHERE TAG_ID = ? ";

    private static final String COLUMN_NAME_TAG_NAME = "TAG_NAME";
    private static final String COLUMN_NAME_TAG_ID = "TAG_ID";
    private static final String COLUMN_NAME_NEWS_TAG_TAG_ID = "TAG_ID";
    private static final String COLUMN_NAME_NEWS_TAGS_NEWS_ID = "NEWS_ID";


    @Autowired
    private DataSource dataSource;

    /**
     * Implementation of TagRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(Tag tag) throws DAOException {
        logger.debug("Creating tag in TagRepositoryImpl");
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
                    logger.debug("Tag id="+tagId+" was created");
                }
            }finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e){
            logger.error("DAOException while creating tag in TagRepositoryImpl");
            logger.debug("Tag was not created");
            throw new DAOException();
        }
        return tagId;
    }
    /**
     * Implementation of TagRepository method read.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Tag read(Long tagId) throws DAOException {
        logger.debug("Reading tag in TagRepositoryImpl");
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
            logger.error("DAOException while reading tag in TagRepositoryImpl");
            logger.debug("Tag was not read");
            throw new DAOException(e);
        }
        if(tag==null){
            logger.debug("Tag with id="+tagId+" does not exist");
            throw new NoSuchEntityException("Tag with id="+tagId+" does not exist");
        }
        return tag;
    }
    /**
     * Implementation of TagRepository method update.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void update(Long id, Tag tag) throws DAOException {
        logger.debug("Updating tag in TagRepositoryImpl");
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
            logger.error("DAOException while updating tag in TagRepositoryImpl");
            logger.debug("Tag was not updated");
            throw new DAOException(e);
        }
    }
    /**
     * Implementation of TagRepository method delete.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void delete(Long id) throws DAOException {
        logger.debug("Deleting tag id="+id+" in TagRepositoryImpl");
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
            logger.error("DAOException while deleting tag in TagRepositoryImpl");
            logger.debug("Tag was not deleted");
            throw new DAOException(e);
        }

    }
    /**
     * Implementation of TagRepository method readAll.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public ArrayList<Tag> readAll() throws DAOException {
        logger.debug("Reading all tags in TagRepositoryImpl");
        Connection conn=null;
        ArrayList<Tag> tags = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_ALL_TAGS_QUERY)){
                try (ResultSet rs = ps.executeQuery()) {
                    tags = new ArrayList<>();
                    while (rs.next()) {
                        tags.add(new Tag(rs.getLong(COLUMN_NAME_TAG_ID),
                                rs.getString(COLUMN_NAME_TAG_NAME)
                        ));
                    }
                    if (tags.isEmpty()) {
                        logger.debug("There are no tags in database");
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading tag in TagRepositoryImpl");
            logger.debug("Tags was not read");
            throw new DAOException(e);
        }
        return tags;
    }
    /**
     * Implementation of TagRepository method readTagsIdByNewsId.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public ArrayList<Long> readTagsIdByNewsId(Long newsId) throws DAOException {
        logger.debug("Reading tags id by news id TagRepositoryImpl");
        Connection conn=null;
        ArrayList<Long> tagsIdList = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_TAGS_ID_BY_NEWS_ID_QUERY)) {
                ps.setLong(1,newsId);
                try (ResultSet rs = ps.executeQuery()) {
                    tagsIdList = new ArrayList<>();
                    while (rs.next()) {
                        tagsIdList.add(rs.getLong(COLUMN_NAME_NEWS_TAG_TAG_ID));
                    }
                    if (tagsIdList.isEmpty()) {
                        logger.debug("Here is no tags for news id=" + newsId);
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading tags' id by news id in TagRepositoryImpl");
            logger.debug("Tags' id was not received");
            throw new DAOException(e);
        }
        return tagsIdList;
    }
    /**
     * Implementation of TagRepository method readNewsIdByTagId.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public ArrayList<Long> readNewsIdByTagId(Long tagId) throws DAOException
    {
        logger.debug("Reading all news' id by tag id in TagRepositoryImpl");
        Connection conn = null;
        ArrayList<Long> newsIdList = null;
        Long newsId;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement st = conn.prepareStatement(READ_NEWS_ID_BY_TAG_ID_QUERY)) {
                newsIdList = new ArrayList<>();
                st.setLong(1, tagId);
                ResultSet resultSet = st.executeQuery();
                while (resultSet.next()) {
                    newsId = resultSet.getLong(COLUMN_NAME_NEWS_TAGS_NEWS_ID);
                    newsIdList.add(newsId);
                }
            }finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading all news' id by tag id in TagRepositoryImpl");
            logger.debug("Tags' id was not received");
            throw new DAOException("Exception in TagDAOImpl ", e);
        }
        return newsIdList;
    }
}
