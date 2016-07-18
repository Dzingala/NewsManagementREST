package by.epam.lab.task.repository.impl;


import by.epam.lab.task.entity.SearchCriteria;
import by.epam.lab.task.repository.NewsRepository;
import by.epam.lab.task.entity.News;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class NewsRepositoryImpl implements NewsRepository {
    private final static Logger logger= Logger.getLogger(NewsRepositoryImpl.class);
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
            " VALUES (:newsId, :tagId) ";
    private static final String CONNECT_NEWS_AUTHOR_QUERY = " INSERT INTO DZINHALA.NEWS_AUTHOR (NEWS_ID,AUTHOR_ID)" +
            " VALUES (:newsId, :authorId) ";

    private static final String READ_NEWS_SORTED_BY_COMMENTS_QUERY="SELECT NEWS.NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, " +
            " NEWS.CREATION_DATE, MODIFICATION_DATE, COUNT(COMMENT_ID) " +
            " FROM DZINHALA.NEWS " +
            " LEFT JOIN DZINHALA.COMMENTS " +
            " ON NEWS.NEWS_ID = COMMENTS.NEWS_ID " +
            " GROUP BY NEWS.NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, " +
            " NEWS.CREATION_DATE, MODIFICATION_DATE " +
            " ORDER BY 2 DESC, 5 DESC, 6 DESC";


    private static final String DISCONNECT_NEWS_TAG_QUERY = "  DELETE FROM NEWS_TAG WHERE NEWS_ID = :newsId AND TAG_ID=:tagId  ";
    private static final String DISCONNECT_NEWS_AUTHOR_QUERY = " DELETE FROM NEWS_AUTHOR WHERE NEWS_ID = :newsId AND AUTHOR_ID=:authorId ";


    private static final String COLUMN_NAME_ID = "NEWS_ID";
    private static final String COLUMN_NAME_TITLE = "TITLE";
    private static final String COLUMN_NAME_SHORT_TEXT = "SHORT_TEXT";
    private static final String COLUMN_NAME_FULL_TEXT = "FULL_TEXT";
    private static final String COLUMN_NAME_CREATION_DATE = "CREATION_DATE";
    private static final String COLUMN_NAME_MODIFICATION_DATE = "MODIFICATION_DATE";


    @Autowired
    private DataSource dataSource;

    /**
     * Implementation of NewsRepository method readSortedByComments.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public List<News> readSortedByComments() throws DAOException {
        logger.debug("Reading all news sorted by comments descendingin NewsRepositoryImpl");
        List<News> newsList = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            newsList = (List<News>) session.createSQLQuery(READ_NEWS_SORTED_BY_COMMENTS_QUERY).list();
        } catch (Exception e) {
            logger.error("DAOException while reading all news sorted by comments descending in NewsRepositoryImpl");
            logger.debug("Sorted news weren't read");
            throw new DAOException(e);
        }finally {
            if(session!=null && session.isOpen()){
                try{
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading news sorted by comments");
                }
            }
        }
        return newsList;
    }
    /**
     * Implementation of NewsRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(News news) throws DAOException {
        logger.debug("Creating news in NewsRepositoryImpl");
        Session session=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(news);
            session.getTransaction().commit();
        }catch (Exception e) {
            logger.error("DAOException while creating news in NewsRepositoryImpl");
            logger.debug("News were not created");
            throw new DAOException();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while closing connection");
                }
            }
        }
        return null;
    }
    /**
     * Implementation of NewsRepository method read.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     * @see by.epam.lab.task.exceptions.dao.NoSuchEntityException
     */
    @Override
    public News read(Long newsId) throws DAOException {
        logger.debug("Reading news in NewsRepositoryImpl");
        Session session=null;
        News news = null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            news=(News)session.load(News.class,newsId);
        }catch (Exception e){
            logger.error("DAOException while reading news in NewsRepositoryImpl");
            logger.debug("News was not read");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try{
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while reading news");
                }
            }
        }
        return news;
    }

    /**
     * Implementation of NewsRepository method update.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void update(Long id, News news) throws DAOException {
        logger.debug("Updating news in NewsRepositoryImpl");
        news.setId(id);
        news.setModificationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(news);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("DAOException while updating news in NewsRepositoryImpl");
            logger.debug("News was not updated");
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
     * Implementation of NewsRepository method delete.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void delete(Long id) throws DAOException {
        logger.debug("Deleting news in NewsRepositoryImpl");
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            News toDeleteNews = (News)session.load(News.class,id);
            session.delete(toDeleteNews);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("DAOException while deleting news in NewsRepositoryImpl");
            logger.debug("News was not deleted");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while deleting a piece of news");
                }
            }
        }
    }
    /**
     * Implementation of NewsRepository method readAll.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public List<News> readAll() throws DAOException {
        logger.debug("Reading all news in NewsRepositoryImpl");
        List<News> news = new ArrayList<>();
        Session session= null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            news=session.createCriteria(News.class).list();
        }catch (Exception e){
            logger.error("DAOException while reading all news in NewsRepositoryImpl");
            logger.debug("News was not read");
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading all news");
                }
            }
        }
        return news;
    }
    /**
     * Implementation of NewsRepository method countNews.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long countNews() throws DAOException {
        logger.debug("Counting news in NewsRepositoryImpl");
        Long newsAmount = null;
        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            BigDecimal bd =(BigDecimal)session.createSQLQuery(COUNT_NEWS_QUERY).uniqueResult();
            newsAmount=bd.longValue();
        }catch (Exception e){
            logger.error("DAOException while counting news in NewsRepositoryImpl");
            logger.debug("News was not counted");
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while counting all news");
                }
            }
        }
        System.out.println("newsAmount:"+newsAmount);
        return newsAmount;
    }
    /**
     * Implementation of NewsRepository method joinNewsWithTags.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void joinNewsWithTag(Long newsId, Long tagId) throws DAOException {
        logger.debug("Connecting news with tags in NewsRepositoryImpl");
        Session session = null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            Query query =session.createSQLQuery(CONNECT_NEWS_TAGS_QUERY).setLong("newsId",newsId).setLong("tagId",tagId);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error("DAOException while connecting news with tags in NewsRepositoryImpl");
            logger.debug("News was not connected with tags");
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while joining news with tag");
                }
            }
        }

    }
    /**
     * Implementation of NewsRepository method joinNewsWithAuthor.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void joinNewsWithAuthor(Long newsId, Long authorId) throws DAOException {
        logger.debug("Connecting news with author in NewsRepositoryImpl");
        Session session = null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query =session.createSQLQuery(CONNECT_NEWS_AUTHOR_QUERY).setLong("newsId",newsId).setLong("authorId",authorId);
            query.executeUpdate();
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
                    logger.error("Hibernate exception while joining news with author");
                }
            }
        }
    }
    /**
     * Implementation of NewsRepository method disjoinNewsWithTag.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void disjoinNewsWithTag(Long newsId, Long tagId) throws DAOException {
        logger.debug("Disconnecting news with tags in NewsRepositoryImpl");
        Session session = null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            Query query =session.createSQLQuery(DISCONNECT_NEWS_TAG_QUERY).setLong("newsId",newsId).setLong("tagId",tagId);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error("DAOException while disconnecting news with tag in NewsRepositoryImpl");
            logger.debug("News was not disconnected with tags");
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
    }
    /**
     * Implementation of NewsRepository method disjoinNewsWithAuthor.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void disjoinNewsWithAuthor(Long newsId, Long authorId) throws DAOException {
        logger.debug("Disconnecting news with author in NewsRepositoryImpl");
        Session session = null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query =session.createSQLQuery(DISCONNECT_NEWS_AUTHOR_QUERY).setLong("newsId",newsId).setLong("authorId",authorId);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            logger.error("DAOException while disconnecting news with author in NewsRepositoryImpl");
            logger.debug("News was not disconnected with author");
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while joining news with author");
                }
            }
        }
    }
    /**
     * Implementation of NewsRepository method readBySearchCriteria.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public List<News> readBySearchCriteria(final String SEARCH_CRITERIA_QUERY, Long page, int newsPerPage) throws DAOException{
        logger.debug("Reading by search criteria in NewsRepositoryImpl");
        System.out.println("CRITERIA:");
        System.out.println(SEARCH_CRITERIA_QUERY);

        List<News> news =new ArrayList<>();
//        Session session = null;
//        try{
//            session=HibernateUtil.getSessionFactory().openSession();
//            System.out.println("session is opened");
//            session.beginTransaction();
//            Query query = session.createSQLQuery(SEARCH_CRITERIA_QUERY);
//            System.out.println("query is ready:"+query);
//            news = (List<News>)query.list();
//            session.getTransaction().commit();
//            System.out.println("here is an error");
//        }catch (Exception e){
//            logger.error("DAOException while reading news by search criteria in NewsRepositoryImpl");
//            logger.debug("News was not disconnected with author");
//            throw new DAOException(e);
//        }finally {
//            if (session != null && session.isOpen()) {
//                try {
//                    session.close();
//                }catch (HibernateException e){
//                    logger.error("Hibernate exception while reading news by search criteria with pages");
//                }
//            }
//        }
        Connection conn = null;
        try{
            conn=dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(SEARCH_CRITERIA_QUERY);
                 ResultSet rs = ps.executeQuery()){
                news = new ArrayList<>();
                while( rs.next() ){
                    news.add(new News(
                            rs.getLong(COLUMN_NAME_ID),
                            rs.getString(COLUMN_NAME_TITLE),
                            rs.getString(COLUMN_NAME_SHORT_TEXT),
                            rs.getString(COLUMN_NAME_FULL_TEXT),
                            rs.getTimestamp(COLUMN_NAME_CREATION_DATE),
                            rs.getDate(COLUMN_NAME_MODIFICATION_DATE)
                    ));
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            logger.error("DAOException while reading news by search criteria in NewsRepositoryImpl");
            logger.debug("News was not disconnected with author");
            throw new DAOException(e);
        }
        return getPageNews(news,page,newsPerPage);
    }
    /**
     * Implementation of NewsRepository method readBySearchCriteriaFull.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public List<News> readBySearchCriteriaFull(final String SEARCH_CRITERIA_QUERY) throws DAOException{
        logger.debug("Reading by search criteria fully in NewsRepositoryImpl");
        System.out.println("CRITERIA:");
        System.out.println(SEARCH_CRITERIA_QUERY);
        logger.debug("Reading by search criteria in NewsRepositoryImpl");
        System.out.println("CRITERIA:");
        System.out.println(SEARCH_CRITERIA_QUERY);

        List<News> news =new ArrayList<>();
//        Session session = null;
//        try{
//            session=HibernateUtil.getSessionFactory().openSession();
//            news = session.createSQLQuery(SEARCH_CRITERIA_QUERY).list();
//        }catch (Exception e){
//            logger.error("DAOException while reading news by search criteria in NewsRepositoryImpl");
//            logger.debug("News was not disconnected with author");
//            throw new DAOException(e);
//        }finally {
//            if (session != null && session.isOpen()) {
//                try {
//                    session.close();
//                }catch (HibernateException e){
//                    logger.error("Hibernate exception while reading news by search criteria");
//                }
//            }
//        }
        Connection conn = null;
        try{
            conn=dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(SEARCH_CRITERIA_QUERY);
                 ResultSet rs = ps.executeQuery()){
                news = new ArrayList<>();
                while( rs.next() ){
                    news.add(new News(
                            rs.getLong(COLUMN_NAME_ID),
                            rs.getString(COLUMN_NAME_TITLE),
                            rs.getString(COLUMN_NAME_SHORT_TEXT),
                            rs.getString(COLUMN_NAME_FULL_TEXT),
                            rs.getTimestamp(COLUMN_NAME_CREATION_DATE),
                            rs.getDate(COLUMN_NAME_MODIFICATION_DATE)
                    ));
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            logger.error("DAOException while reading news by search criteria in NewsRepositoryImpl");
            logger.debug("News was not disconnected with author");
            throw new DAOException(e);
        }
        return news;
    }
    private static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }
    public static List<News> getPageNews(List<News> news,Long page,int newsPerPage) {//18,4,5
        List<News> newsOnPage = new ArrayList<>();
        long from = (page - 1) * newsPerPage;
        long size = news.size();
        long difference = size - from;
        long to = difference >= newsPerPage ? from + newsPerPage : from + difference;
        for (long i = from; i < to; i++) {
            newsOnPage.add(news.get(safeLongToInt(i)));
        }
        return newsOnPage;
    }


    private static final String READ_NEWS_BY_AUTHOR_AND_TAGS_QUERY=
            "SELECT DISTINCT NEWS.NEWS_ID,NEWS.TITLE,NEWS.SHORT_TEXT," +
                    "NEWS.FULL_TEXT,NEWS.CREATION_DATE,NEWS.MODIFICATION_DATE" +
                    " FROM DZINHALA.NEWS LEFT JOIN DZINHALA.COMMENTS ON NEWS.NEWS_ID=COMMENTS.NEWS_ID" +
                    " LEFT JOIN DZINHALA.NEWS_AUTHOR ON NEWS.NEWS_ID=NEWS_AUTHOR.NEWS_ID" +
                    " LEFT JOIN DZINHALA.NEWS_TAG ON NEWS.NEWS_ID=NEWS_TAG.NEWS_ID ";


    /**
     * Static method for composing search criteria query according to the certain requirements.
     * @param criteria
     * @return SQL query which reflects search criteria' requirements and is ready for processing
     */
    public static String composeSearchCriteriaQuery(SearchCriteria criteria){
        logger.debug("Composing search criteria query in NewsRepositoryImpl");
        StringBuffer sb= new StringBuffer(READ_NEWS_BY_AUTHOR_AND_TAGS_QUERY);
        Long authorId =criteria.getAuthorId();
        List<Long> tagsId = criteria.getTagsId();
        if(authorId == null && tagsId == null){
            return null;
        }
        sb.append("WHERE ");
        if(authorId!=null){
            sb.append("NEWS_AUTHOR.AUTHOR_ID=").append(authorId);
            if(tagsId!=null){
                sb.append(" AND ").append("NEWS_TAG.TAG_ID IN(");
                for (Long tagId : tagsId) {
                    sb.append(tagId);
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(")");
            }
        }
        else{
            sb.append("NEWS_TAG.TAG_ID IN(");
            for (Long tagId : tagsId) {
                sb.append(tagId);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
        }
        return sb.toString();
    }

    @Override
    public Long countNews(final String countQuery)throws DAOException{
        logger.debug("Counting criteria News in NewsRepositoryImpl");
        Long criteriaNewsAmount = 0L;
        Session session = null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            BigDecimal bd = (BigDecimal)session.createSQLQuery(countQuery).uniqueResult();
            criteriaNewsAmount=bd.longValue();
        }catch (Exception e){
            logger.error("DAOException while counting criteria News in NewsRepositoryImpl");
            logger.debug("Criteria news were not counted");
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while counting news by count query");
                }
            }
        }
        return criteriaNewsAmount;
    }
}
