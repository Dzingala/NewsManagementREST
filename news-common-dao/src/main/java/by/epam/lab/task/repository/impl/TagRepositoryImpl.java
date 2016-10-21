package by.epam.lab.task.repository.impl;


import by.epam.lab.task.repository.TagRepository;
import by.epam.lab.task.entity.Tag;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.util.HibernateUtil;
import org.apache.log4j.Logger;
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
import org.hibernate.HibernateException;
import org.hibernate.Session;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceUtils;
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagRepositoryImpl implements TagRepository {
    private final static Logger logger= Logger.getLogger(TagRepositoryImpl.class);
    private static final String READ_TAGS_ID_BY_NEWS_ID_QUERY = " SELECT TAG_ID FROM DZINHALA.NEWS_TAG WHERE NEWS_ID = :newsId ";
    private static final String READ_NEWS_ID_BY_TAG_ID_QUERY = " SELECT NEWS_ID FROM DZINHALA.NEWS_TAG WHERE TAG_ID = :tagId ";

<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
=======
    private static final String COLUMN_NAME_TAG_NAME = "TAG_NAME";
    private static final String COLUMN_NAME_TAG_ID = "TAG_ID";
    private static final String COLUMN_NAME_NEWS_TAG_TAG_ID = "TAG_ID";

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
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java

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
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
            throw new DAOException();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while creating a tag");
                }
            }
=======
            throw new DAOException("DAOException while creating tag in TagRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
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
        Session session = null;
        Tag tag = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tag = (Tag) session.load(Tag.class, tagId);
        } catch (Exception e) {
            logger.error("DAOException while reading tag in TagRepositoryImpl");
            logger.debug("Tag was not read");
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    logger.error("HibernateException while reading a tag");
                }
            }
=======
            throw new DAOException("DAOException while reading tag in TagRepositoryImpl",e);
        }
        if(tag==null){
            logger.debug("Tag with id="+tagId+" does not exist");
            throw new NoSuchEntityException("Tag with id="+tagId+" does not exist");
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
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
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
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
            throw new DAOException("DAOException while updating tag in TagRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
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
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while deleting a tag");
                }
            }
=======
            throw new DAOException("DAOException while deleting tag in TagRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
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
                    logger.error("Hibernate exception while reading all tags");
                }
            }
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
=======
        }catch (SQLException e) {
            logger.error("DAOException while reading tag in TagRepositoryImpl");
            logger.debug("Tags was not read");
            throw new DAOException("DAOException while reading tag in TagRepositoryImpl",e);
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
        }
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
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading tags ids by news id");
                }
            }
=======
            throw new DAOException("DAOException while reading tags' id by news id in TagRepositoryImpl",e);
        }
        if (tagsIdList == null) {
            throw new NoSuchEntityException("News id="+newsId+" have no tags assigned");
>>>>>>> develop/netcracker:news-common-dao/src/main/java/by/epam/lab/task/repository/impl/TagRepositoryImpl.java
        }
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

        return newsIdList;
    }
}
