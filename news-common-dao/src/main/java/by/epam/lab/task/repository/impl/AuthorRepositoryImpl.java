package by.epam.lab.task.repository.impl;

import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.AuthorRepository;
import by.epam.lab.task.entity.Author;
import by.epam.lab.task.exceptions.dao.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Component
public class AuthorRepositoryImpl implements AuthorRepository {
    private final static Logger logger= Logger.getLogger(AuthorRepositoryImpl.class);
    private static final String CREATE_AUTHOR_QUERY= " INSERT INTO DZINHALA.AUTHOR (AUTHOR_NAME) VALUES (?) ";
    private static final String READ_AUTHOR_QUERY= " SELECT AUTHOR_ID,AUTHOR_NAME,EXPIRED FROM DZINHALA.AUTHOR WHERE AUTHOR_ID = ? ";
    private static final String READ_AUTHOR_ID_BY_NEWS_ID_QUERY = " SELECT AUTHOR_ID FROM DZINHALA.NEWS_AUTHOR WHERE NEWS_ID = ? ";
    private static final String UPDATE_AUTHOR_QUERY = " UPDATE DZINHALA.AUTHOR SET AUTHOR_NAME = ? ,EXPIRED = ?  WHERE AUTHOR_ID = ? ";
    private static final String DELETE_AUTHOR_QUERY = " DELETE FROM DZINHALA.AUTHOR WHERE AUTHOR_ID = ? ";
    private static final String READ_ALL_AUTHORS_QUERY="SELECT AUTHOR_ID,AUTHOR_NAME,EXPIRED FROM DZINHALA.AUTHOR";

    private static final String COLUMN_NAME_AUTHOR_ID = "AUTHOR_ID";
    private static final String COLUMN_NAME_AUTHOR_NAME = "AUTHOR_NAME";
    private static final String COLUMN_NAME_EXPIRED = "EXPIRED";

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
    /**
     * Implementation of AuthorRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(Author author) throws DAOException {
        logger.debug("Creating author in AuthorRepositoryImpl");
        Connection conn=null;
        Long authorId=null;
        String[] keys = {COLUMN_NAME_AUTHOR_ID};
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(CREATE_AUTHOR_QUERY, keys)) {
                ps.setString(1, author.getName());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                    authorId = rs.getLong(1);
                }
            }finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e){
            logger.error("DAOException while creating author in AuthorRepositoryImpl");
            logger.debug("Author was not created");
            throw new DAOException();
        }
        return authorId;
    }
    /**
     * Implementation of AuthorRepository method read.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     * @see by.epam.lab.task.exceptions.dao.NoSuchEntityException
     */
    @Override
    public Author read(Long authorId) throws DAOException {
        logger.debug("Reading author in AuthorRepositoryImpl");
        Connection conn=null;
        Author author = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_AUTHOR_QUERY)) {
                ps.setLong(1, authorId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        author = new Author(authorId,
                                rs.getString(COLUMN_NAME_AUTHOR_NAME),
                                rs.getTimestamp(COLUMN_NAME_EXPIRED)
                        );
                    }
                    else{
                        logger.debug("Author with id="+authorId+" does not exist");
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            logger.error("DAOException while reading author in AuthorRepositoryImpl");
            logger.debug("Author was not read");
            throw new DAOException(e);
        }
        if(author==null){
            logger.debug("Author with id="+authorId+" does not exist");
            throw new NoSuchEntityException("Author with id="+authorId+" does not exist");
        }
        return author;
    }

    /**
     * Implementation of AuthorRepository method update.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void update(Long id, Author author) throws DAOException {
        logger.debug("Updating author in AuthorRepositoryImpl");
        Connection conn =null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(UPDATE_AUTHOR_QUERY)) {

                ps.setString(1, author.getName());
                ps.setTimestamp(2, author.getExpired());
                ps.setLong(3, id);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            logger.error("DAOException while updating author in AuthorRepositoryImpl");
            logger.debug("Author was not updated");
            throw new DAOException(e);
        }
    }
    /**
     * Implementation of AuthorRepository method delete.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void delete(Long id) throws DAOException {
        logger.debug("Deleting author in AuthorRepositoryImpl");
        Connection conn=null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(DELETE_AUTHOR_QUERY)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            logger.error("DAOException while deleting author in AuthorRepositoryImpl");
            logger.debug("Author was not deleted");
            throw new DAOException(e);
        }

    }
    /**
     * Implementation of AuthorRepository method readAll.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public ArrayList<Author> readAll() throws DAOException {
        logger.debug("Reading all authors in AuthorRepositoryImpl");
        Connection conn=null;
        ArrayList<Author> authors=null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_ALL_AUTHORS_QUERY)) {

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        authors=new ArrayList<>();
                        authors.add(new Author(rs.getLong(COLUMN_NAME_AUTHOR_ID),
                                rs.getString(COLUMN_NAME_AUTHOR_NAME),
                                rs.getTimestamp(COLUMN_NAME_EXPIRED)
                        ));
                        while(rs.next()){
                            authors.add(new Author(rs.getLong(COLUMN_NAME_AUTHOR_ID),
                                    rs.getString(COLUMN_NAME_AUTHOR_NAME),
                                    rs.getTimestamp(COLUMN_NAME_EXPIRED)
                            ));
                        }
                    }
                    else{
                        logger.debug("There are no authors in database");
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            logger.error("DAOException while reading author in AuthorRepositoryImpl");
            logger.debug("Authors was not read");
            throw new DAOException(e);
        }
        return authors;
    }
    /**
     * Implementation of AuthorRepository method readAuthorIdByNewsId.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     * @see by.epam.lab.task.exceptions.dao.NoSuchEntityException
     */
    @Override
    public Long readAuthorIdByNewsId(Long newsId) throws DAOException {
        logger.debug("Reading author's id by news id in AuthorRepositoryImpl");
        Connection conn=null;
        Long authorId = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_AUTHOR_ID_BY_NEWS_ID_QUERY)) {
                ps.setLong(1, newsId);
                try (ResultSet resultSet = ps.executeQuery()) {
                    if (resultSet.next()) {
                        authorId = resultSet.getLong(1);
                    }
                    else {
                        logger.debug("News with id="+newsId+" have not author assigned");
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            logger.error("DAOException while reading author's id by news id in AuthorRepositoryImpl");
            logger.debug("Author's id was not received");
            throw new DAOException(e);
        }
        if (authorId == null) {
            throw new DAOException("News id="+newsId+" have not author assigned");
        }
        return authorId;
    }



}
