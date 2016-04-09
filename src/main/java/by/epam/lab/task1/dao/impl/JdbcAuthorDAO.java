package by.epam.lab.task1.dao.impl;

import by.epam.lab.task1.dao.AuthorDAO;
import by.epam.lab.task1.model.Author;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class JdbcAuthorDAO implements AuthorDAO {
    private final static Logger logger= Logger.getLogger(JdbcAuthorDAO.class);
    private final static String CREATE_AUTHOR_QUERY="INSERT INTO DZINHALA.AUTHOR(AUTHOR_NAME,EXPIRED) VALUES (?,?)";
    private final static String READ_AUTHOR_QUERY="SELECT * FROM DZINHALA.AUTHOR WHERE AUTHOR_ID=?";
    private final static String UPDATE_AUTHOR_QUERY="UPDATE DZINHALA.AUTHOR SET AUTHOR_NAME=?,EXPIRED=? WHERE AUTHOR_ID=?";
    private final static String DELETE_AUTHOR_QUERY="DELETE FROM DZINHALA.AUTHOR WHERE AUTHOR_ID=?";

    @Autowired
    private DataSource dataSource;

    @Override
    public boolean createAuthor(Author author) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(CREATE_AUTHOR_QUERY);
            ps.setString(1, author.getAuthorName());
            ps.setTimestamp(2,author.getExpired());
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
    public Author readAuthorById(long authorId) {
        Connection conn = null;
        Author author=null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(READ_AUTHOR_QUERY);
            ps.setLong(1, authorId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                author=new Author();
                author.setAuthorName(rs.getString("AUTHOR_NAME"));
                author.setAuthorId(rs.getLong("AUTHOR_ID"));
                author.setExpired(rs.getTimestamp("EXPIRED"));
                logger.debug( author);
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
        return author;
    }

    @Override
    public boolean updateAuthorById(Author author) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_AUTHOR_QUERY);
            ps.setString(1, author.getAuthorName());
            ps.setTimestamp(2, author.getExpired());
            ps.setLong(3,author.getAuthorId());
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
    public boolean deleteAuthorById(long authorId) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_AUTHOR_QUERY);
            ps.setLong(1, authorId);
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
