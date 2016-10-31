package by.epam.lab.task.repository.impl;


import by.epam.lab.task.repository.TagRepository;
import by.epam.lab.task.entity.Tag;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.utils.hibernate.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Represents the means of manipulating with Tag entity and database.
 */
@Repository
public class TagRepositoryImpl implements TagRepository {
    private static final Logger logger= Logger.getLogger(TagRepositoryImpl.class);
    private static final String READ_TAGS_ID_BY_NEWS_ID_QUERY = " SELECT TAG_ID FROM DZINHALA.NEWS_TAG WHERE NEWS_ID = :newsId ";
    private static final String READ_NEWS_ID_BY_TAG_ID_QUERY = " SELECT NEWS_ID FROM DZINHALA.NEWS_TAG WHERE TAG_ID = :tagId ";


    /**
     * Implementation of TagRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(Tag tag) throws DAOException {
        logger.debug("Creating tag in TagRepositoryImpl");
        Session session=null;
        Long tagId=null;
        try{
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            tagId=(Long)session.save(tag);
            session.getTransaction().commit();
        }catch (Exception e) {
            logger.error("DAOException while creating tag in TagRepositoryImpl");
            logger.debug("Tag was not created");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while creating a tag: "+e);
                }
            }
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
        Session session = null;
        Tag tag = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tag = (Tag) session.get(Tag.class, tagId);
        }catch (Exception e) {
            logger.error("DAOException while reading tag in TagRepositoryImpl");
            logger.debug("Tag was not read");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    logger.error("HibernateException while reading a tag: "+e);
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
                    logger.error("HibernateException while closing connection: "+e);
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
                    logger.error("HibernateException while deleting a tag: "+e);
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
                    logger.error("Hibernate exception while reading all tags: "+e);
                }
            }
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
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading tags ids by news id: "+e);
                }
            }
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
                    logger.error("Hibernate exception while reading news ids by tag id: "+e);
                }
            }
        }

        return newsIdList;
    }
}