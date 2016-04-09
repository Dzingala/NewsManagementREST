package by.epam.lab.task1.dao.impl;


import by.epam.lab.task1.dao.NewsDAO;
import by.epam.lab.task1.model.News;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

@Component
public class JdbcNewsDAO implements NewsDAO {
    private final static Logger logger= Logger.getLogger(JdbcNewsDAO.class);
    private final static String CREATE_NEWS_QUERY="INSERT INTO DZINHALA.NEWS(TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE) VALUES (?,?,?,?,?)";
    private final static String READ_NEWS_QUERY="SELECT * FROM DZINHALA.NEWS WHERE NEWS_ID=?";
    private final static String UPDATE_NEWS_QUERY="UPDATE DZINHALA.NEWS SET TITLE=?,SHORT_TEXT=?,FULL_TEXT=?,CREATION_DATE=?,MODIFICATION_DATE=? WHERE NEWS_ID=?";
    private final static String DELETE_NEWS_QUERY="DELETE FROM DZINHALA.NEWS WHERE NEWS_ID=?";

    @Autowired
    private DataSource dataSource;

    @Override
    public boolean createNews(News news) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(CREATE_NEWS_QUERY);
            ps.setString(1, news.getTitle());
            ps.setString(2,news.getShortText());
            ps.setString(3,news.getFullText());
            ps.setTimestamp(4, news.getCreationDate());
            ps.setDate(5,news.getModificationDate());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return true;
    }

    @Override
    public News readNewsById(long newsId) {
        Connection conn = null;
        News news = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(READ_NEWS_QUERY);
            ps.setLong(1, newsId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                news = new News();
                news.setTitle(rs.getString("TITLE"));
                news.setShortText(rs.getString("SHORT_TEXT"));
                news.setFullText(rs.getString("FULL_TEXT"));
                news.setCreationDate(rs.getTimestamp("CREATION_DATE"));
                news.setModificationDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                news.setNewsId(newsId);
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return news;
    }

    @Override
    public boolean updateNewsById(News news) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_NEWS_QUERY);
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getShortText());
            ps.setString(3, news.getFullText());
            ps.setTimestamp(4, news.getCreationDate());
            ps.setDate(5, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            ps.setLong(6,news.getNewsId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return true;
    }

    @Override
    public boolean deleteNewsById(long newsId) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_NEWS_QUERY);
            ps.setLong(1, newsId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return true;
    }
}
