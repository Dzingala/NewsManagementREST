package by.epam.lab.task.dao.impl;

import by.epam.lab.task.dao.NewsAuthorDAO;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.utils.hibernate.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * @author Ivan Dzinhala
 */
public class NewsAuthorDAOImpl implements NewsAuthorDAO {
    private static final Logger logger= Logger.getLogger(NewsAuthorDAOImpl.class);

    private static final String NEWS_ID="newsId";
    private static final String AUTHOR_ID="authorId";


    private static final String CONNECT_NEWS_AUTHOR_QUERY = " INSERT INTO DZINHALA.NEWS_AUTHOR (NEWS_ID,AUTHOR_ID)" +
            " VALUES (:newsId, :authorId) ";
    @Override
    public void joinNewsWithAuthor(Long newsId, Long authorId) throws DAOException {
        logger.debug("Connecting news with author in NewsRepositoryImpl");
        Session session = null;
        try{
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery(CONNECT_NEWS_AUTHOR_QUERY).setLong(NEWS_ID,newsId).setLong(AUTHOR_ID,authorId).executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            logger.error("DAOException while connecting news with author in NewsRepositoryImpl");
            logger.debug("News was not connected with author");
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception "+e);
                }
            }
        }
    }
}
