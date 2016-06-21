package by.epam.lab.task.repository.impl;


import by.epam.lab.task.entity.Author;
import by.epam.lab.task.entity.SearchCriteria;
import by.epam.lab.task.entity.Tag;
import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.NewsRepository;
import by.epam.lab.task.entity.News;
import by.epam.lab.task.exceptions.dao.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

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
            " VALUES (?, ?) ";
    private static final String CONNECT_NEWS_AUTHOR_QUERY = " INSERT INTO DZINHALA.NEWS_AUTHOR (NEWS_ID,AUTHOR_ID)" +
            " VALUES (?, ?) ";

    private static final String READ_NEWS_SORTED_BY_COMMENTS_QUERY="SELECT NEWS.NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, " +
            " NEWS.CREATION_DATE, MODIFICATION_DATE, COUNT(COMMENT_ID) " +
            " FROM DZINHALA.NEWS " +
            " LEFT JOIN DZINHALA.COMMENTS " +
            " ON NEWS.NEWS_ID = COMMENTS.NEWS_ID " +
            " GROUP BY NEWS.NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, " +
            " NEWS.CREATION_DATE, MODIFICATION_DATE " +
            " ORDER BY 2 DESC, 5 DESC, 6 DESC";


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

    /**
     * Implementation of NewsRepository method readSortedByComments.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public ArrayList<News> readSortedByComments() throws DAOException {
        logger.debug("Reading all news sorted by comments descendingin NewsRepositoryImpl");
        ArrayList<News> newsList = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_NEWS_SORTED_BY_COMMENTS_QUERY);
                 ResultSet rs = ps.executeQuery()) {
                newsList = new ArrayList<>();
                while (rs.next()) {
                    newsList.add(new News(
                                    rs.getLong(COLUMN_NAME_ID),
                                    rs.getString(COLUMN_NAME_TITLE),
                                    rs.getString(COLUMN_NAME_SHORT_TEXT),
                                    rs.getString(COLUMN_NAME_FULL_TEXT),
                                    rs.getTimestamp(COLUMN_NAME_CREATION_DATE),
                                    rs.getDate(COLUMN_NAME_MODIFICATION_DATE)
                            )
                    );
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            logger.error("DAOException while reading all news sorted by comments descending in NewsRepositoryImpl");
            logger.debug("Sorted news weren't read");
            throw new DAOException(e);
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
        Connection conn =null;
        Long newsId=null;
        String[] keys = {COLUMN_NAME_ID};
        int rowsCount=0;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(CREATE_NEWS_QUERY, keys)) {

                ps.setString(1, news.getTitle());
                ps.setString(2, news.getShortText());
                ps.setString(3, news.getFullText());
                ps.setTimestamp(4, news.getCreationDate());
                ps.setDate(5, news.getModificationDate());
                rowsCount=ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                    newsId = rs.getLong(1);
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while creating news in NewsRepositoryImpl");
            logger.debug("News was not created");
            throw new DAOException(e);
        }
        if(rowsCount==0){//The same with if(newsId==null) check
            logger.debug("0 rows were returned");
            throw new DAOException("News was not created");
        }
        return newsId;
    }
    /**
     * Implementation of NewsRepository method read.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     * @see by.epam.lab.task.exceptions.dao.NoSuchEntityException
     */
    @Override
    public News read(Long newsId) throws DAOException {
        logger.debug("Reading news in NewsRepositoryImpl");
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
                    }else throw new NoSuchEntityException("News do not exist");
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading news in NewsRepositoryImpl");
            logger.debug("News was not read");
            throw new DAOException(e);
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
        Connection conn = null;
        try {
            conn = dataSource.getConnection();

            try (PreparedStatement ps = conn.prepareStatement(UPDATE_NEWS_QUERY)) {

                ps.setString(1, news.getTitle());
                ps.setString(2, news.getShortText());
                ps.setString(3, news.getFullText());
                ps.setTimestamp(4, news.getCreationDate());
//                ps.setDate(5, news.getModificationDate());
                ps.setDate(5,new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                ps.setLong(6, id);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while updating news in NewsRepositoryImpl");
            logger.debug("News was not updated");
            throw new DAOException(e);
        }
    }
    /**
     * Implementation of NewsRepository method delete.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void delete(Long id) throws DAOException {
        logger.debug("Deleting news in NewsRepositoryImpl");
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
            logger.error("DAOException while updating news in NewsRepositoryImpl");
            logger.debug("News was not updated");
            throw new DAOException(e);
        }
    }
    /**
     * Implementation of NewsRepository method readAll.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public ArrayList<News> readAll() throws DAOException {
        logger.debug("Reading all news in NewsRepositoryImpl");
        ArrayList<News> news = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_ALL_NEWS_QUERY); ResultSet rs = ps.executeQuery()) {
                news=new ArrayList<>();
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
                if(news.isEmpty()) {
                    logger.debug("Here is no news in the database");
                }

            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading all news in NewsRepositoryImpl");
            logger.debug("News was not read");
            throw new DAOException(e);
        }
        return news;
    }
    /**
     * Implementation of NewsRepository method countNews.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long countNews() throws DAOException {
        Long newsAmount = null;
        logger.debug("Counting news in NewsRepositoryImpl");
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
            logger.error("DAOException while counting news in NewsRepositoryImpl");
            logger.debug("News was not counted");
            throw new DAOException(e);
        }
        return newsAmount;
    }
    /**
     * Implementation of NewsRepository method joinNewsWithTags.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void joinNewsWithTag(Long newsId, Long tagId) throws DAOException {
        logger.debug("Connecting news with tags in NewsRepositoryImpl");
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
            logger.error("DAOException while connecting news with tags in NewsRepositoryImpl");
            logger.debug("News was not connected with tags");
            throw new DAOException(e);
        }

    }
    /**
     * Implementation of NewsRepository method joinNewsWithAuthor.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void joinNewsWithAuthor(Long newsId, Long authorId) throws DAOException {
        logger.debug("Connecting news with author in NewsRepositoryImpl");
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
            logger.error("DAOException while connecting news with author in NewsRepositoryImpl");
            logger.debug("News was not connected with author");
            throw new DAOException(e);
        }

    }
    /**
     * Implementation of NewsRepository method disjoinNewsWithTag.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void disjoinNewsWithTag(Long newsId, Long tagId) throws DAOException {
        logger.debug("Disconnecting news with tags in NewsRepositoryImpl");
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
            logger.error("DAOException while disconnecting news with tags in NewsRepositoryImpl");
            logger.debug("News was not disconnected with tags");
            throw new DAOException(e);
        }

    }
    /**
     * Implementation of NewsRepository method disjoinNewsWithAuthor.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void disjoinNewsWithAuthor(Long newsId, Long authorId) throws DAOException {
        logger.debug("Disconnecting news with author in NewsRepositoryImpl");
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
            logger.error("DAOException while disconnecting news with author in NewsRepositoryImpl");
            logger.debug("News was not disconnected with author");
            throw new DAOException(e);
        }

    }
    /**
     * Implementation of NewsRepository method readBySearchCriteria.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public ArrayList<News> readBySearchCriteria(final String SEARCH_CRITERIA_QUERY,Long page, int newsPerPage) throws DAOException{
        logger.debug("Reading by search criteria in NewsRepositoryImpl");
        System.out.println("CRITERIA:");
        System.out.println(SEARCH_CRITERIA_QUERY);
        Connection conn = null;
        ArrayList<News> news =null;
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
    public ArrayList<News> readBySearchCriteriaFull(final String SEARCH_CRITERIA_QUERY) throws DAOException{
        logger.debug("Reading by search criteria fully in NewsRepositoryImpl");
        System.out.println("CRITERIA:");
        System.out.println(SEARCH_CRITERIA_QUERY);
        Connection conn = null;
        ArrayList<News> news =null;
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
            logger.error("DAOException while reading full news by search criteria in NewsRepositoryImpl");
            logger.debug("News was not disconnected with author");
            throw new DAOException(e);
        }
        return news;
    }
    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }
    public static ArrayList<News> getPageNews(ArrayList<News> news,Long page,int newsPerPage) {//18,4,5
        ArrayList<News> newsOnPage = new ArrayList<>();
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
        ArrayList<Long> tagsId = criteria.getTagsId();
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
        Long criteriaNewsAmount = 0l;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(countQuery)) {
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    criteriaNewsAmount = resultSet.getLong(1);
                }
            }finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while counting criteria News in NewsRepositoryImpl");
            logger.debug("Criteria news were not counted");
            throw new DAOException(e);
        }
        return criteriaNewsAmount;
    }

}
