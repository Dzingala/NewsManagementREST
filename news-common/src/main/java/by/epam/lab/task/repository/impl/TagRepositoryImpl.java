package by.epam.lab.task.repository.impl;


import by.epam.lab.task.repository.TagRepository;
import by.epam.lab.task.entity.Tag;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagRepositoryImpl implements TagRepository {
    private final static Logger logger= Logger.getLogger(TagRepositoryImpl.class);
    private static final String CREATE_TAG_QUERY = " INSERT INTO DZINHALA.TAG (TAG_NAME) VALUES (?) ";
    private static final String READ_TAG_QUERY = " SELECT TAG_ID,TAG_NAME FROM DZINHALA.TAG WHERE TAG_ID = ? ";
    private static final String UPDATE_TAG_QUERY = " UPDATE DZINHALA.TAG SET TAG_NAME = ? WHERE TAG_ID = ? ";
    private static final String DELETE_TAG_QUERY = " DELETE FROM DZINHALA.TAG  WHERE TAG_ID = ? ";
    private static final String READ_TAGS_ID_BY_NEWS_ID_QUERY = " SELECT TAG_ID FROM DZINHALA.NEWS_TAG WHERE NEWS_ID = :newsId ";
    private static final String READ_ALL_TAGS_QUERY="SELECT TAG_ID, TAG_NAME FROM DZINHALA.TAG";
    private static final String READ_NEWS_ID_BY_TAG_ID_QUERY = " SELECT NEWS_ID FROM DZINHALA.NEWS_TAG WHERE TAG_ID = :tagId ";

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
        Session session=null;
        try{
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(tag);
            session.getTransaction().commit();
        }catch (Exception e) {
            logger.error("DAOException while creating tag in TagRepositoryImpl");
            logger.debug("Tag was not created");
            throw new DAOException();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while creating a tag");
                }
            }
        }
        return null;
    }
    /**
     * Implementation of TagRepository method read.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Tag read(Long tagId) throws DAOException {
        logger.debug("Reading tag in TagRepositoryImpl");
        Session session=null;
        Tag tag = null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tag=(Tag)session.load(Tag.class,tagId);
        }catch (Exception e){
            logger.error("DAOException while reading tag in TagRepositoryImpl");
            logger.debug("Tag was not read");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try{
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while reading a tag");
                }
            }
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
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            tag.setId(id);
            session.update(tag);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("DAOException while updating a tag in TagRepositoryImpl");
            logger.debug("Tag was not updated");
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
     * Implementation of TagRepository method delete.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void delete(Long id) throws DAOException {
        logger.debug("Deleting tag id="+id+" in TagRepositoryImpl");
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Tag toDeleteTag = (Tag)session.load(Tag.class,id);
            session.delete(toDeleteTag);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("DAOException while deleting a tag in TagRepositoryImpl");
            logger.debug("Tag was not deleted");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while deleting a tag");
                }
            }
        }

    }
    /**
     * Implementation of TagRepository method readAll.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public List<Tag> readAll() throws DAOException {
        logger.debug("Reading all tags in TagRepositoryImpl");
        Session session= null;
        List<Tag> tags=new ArrayList<>();
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tags=session.createCriteria(Tag.class).list();
        }catch (Exception e){
            logger.error("DAOException while reading all tags in TagRepositoryImpl");
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
//        Connection conn=null;
//        ArrayList<Tag> tags = null;
//        try {
//            conn = dataSource.getConnection();
//            try (PreparedStatement ps = conn.prepareStatement(READ_ALL_TAGS_QUERY)){
//                try (ResultSet rs = ps.executeQuery()) {
//                    tags = new ArrayList<>();
//                    while (rs.next()) {
//                        tags.add(new Tag(rs.getLong(COLUMN_NAME_TAG_ID),
//                                rs.getString(COLUMN_NAME_TAG_NAME)
//                        ));
//                    }
//                    if (tags.isEmpty()) {
//                        logger.debug("There are no tags in database");
//                    }
//                }
//            } finally {
//                DataSourceUtils.releaseConnection(conn, dataSource);
//            }
//        }catch (SQLException e) {
//            logger.error("DAOException while reading tag in TagRepositoryImpl");
//            logger.debug("Tags was not read");
//            throw new DAOException(e);
//        }
        return tags;
    }
    /**
     * Implementation of TagRepository method readTagsIdByNewsId.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public List<Long> readTagsIdByNewsId(Long newsId) throws DAOException {
        logger.debug("Reading tags id by news id TagRepositoryImpl");
        Session session = null;
        List<Long> tagsIdList = new ArrayList<>();
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            List<BigDecimal> bdTagsIdList = (List<BigDecimal>)session.createSQLQuery(READ_TAGS_ID_BY_NEWS_ID_QUERY).setParameter("newsId",newsId).list();

            tagsIdList.addAll(bdTagsIdList.stream().map(BigDecimal::longValue).collect(Collectors.toList()));
        }catch (Exception e){
            logger.error("DAOException while reading tags' id by news id in TagRepositoryImpl");
            logger.debug("Tags' id was not received");
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading tags ids by news id");
                }
            }
        }

//        Connection conn=null;
//        ArrayList<Long> tagsIdList = null;
//        try {
//            conn = dataSource.getConnection();
//            try (PreparedStatement ps = conn.prepareStatement(READ_TAGS_ID_BY_NEWS_ID_QUERY)) {
//                ps.setLong(1,newsId);
//                try (ResultSet rs = ps.executeQuery()) {
//                    tagsIdList = new ArrayList<>();
//                    while (rs.next()) {
//                        tagsIdList.add(rs.getLong(COLUMN_NAME_NEWS_TAG_TAG_ID));
//                    }
//                    if (tagsIdList.isEmpty()) {
//                        logger.debug("Here is no tags for news id=" + newsId);
//                    }
//                }
//            } finally {
//                DataSourceUtils.releaseConnection(conn, dataSource);
//            }
//        }catch (SQLException e) {
//            logger.error("DAOException while reading tags' id by news id in TagRepositoryImpl");
//            logger.debug("Tags' id was not received");
//            throw new DAOException(e);
//        }
        return tagsIdList;
    }
    /**
     * Implementation of TagRepository method readNewsIdByTagId.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public List<Long> readNewsIdByTagId(Long tagId) throws DAOException
    {
        logger.debug("Reading all news' id by tag id in TagRepositoryImpl");


        Session session = null;
        List<Long> newsIdList = new ArrayList<>();
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            List<BigDecimal> bdNewsIdList = (List<BigDecimal>)session.createSQLQuery(READ_NEWS_ID_BY_TAG_ID_QUERY).setParameter("tagId",tagId).list();

            newsIdList.addAll(bdNewsIdList.stream().map(BigDecimal::longValue).collect(Collectors.toList()));
        }catch (Exception e){
            logger.error("DAOException while reading all news' id by tag id in TagRepositoryImpl");
            logger.debug("Tags' id was not received");
            throw new DAOException("Exception in TagDAOImpl ", e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading news ids by tag id");
                }
            }
        }


//        Connection conn = null;
//        ArrayList<Long> newsIdList = null;
//        Long newsId;
//        try {
//            conn = dataSource.getConnection();
//            try (PreparedStatement st = conn.prepareStatement(READ_NEWS_ID_BY_TAG_ID_QUERY)) {
//                newsIdList = new ArrayList<>();
//                st.setLong(1, tagId);
//                ResultSet resultSet = st.executeQuery();
//                while (resultSet.next()) {
//                    newsId = resultSet.getLong(COLUMN_NAME_NEWS_TAGS_NEWS_ID);
//                    newsIdList.add(newsId);
//                }
//            }finally {
//                DataSourceUtils.releaseConnection(conn, dataSource);
//            }
//        }catch (SQLException e) {
//            logger.error("DAOException while reading all news' id by tag id in TagRepositoryImpl");
//            logger.debug("Tags' id was not received");
//            throw new DAOException("Exception in TagDAOImpl ", e);
//        }
        return newsIdList;
    }
}
