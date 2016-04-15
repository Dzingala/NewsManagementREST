package by.epam.lab.task1.dao.impl;

import by.epam.lab.task1.dao.AuthorDAO;
import by.epam.lab.task1.entity.Author;
import by.epam.lab.task1.exceptions.DAOException;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringDatasourceTest.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml")
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
public class TestJdbcAuthorDAO {
    @Autowired
    private AuthorDAO authorDAO;

    private static final String tempName="tempname";

    @Test
    public void createTest() throws DAOException {
        Author author = new Author();
        author.setName(tempName);
        Long authorId = authorDAO.create(author);
    }

    @Test
    public void readTest() throws DAOException {
        Author author = authorDAO.read(2L);
        assertNotNull(author);
    }

    @Test
    public void updateTest() throws DAOException {
        Long authorId = 3L;
        Author author = authorDAO.read(authorId);
        author.setName(tempName);
        author.setExpired(new Timestamp(new java.util.Date().getTime()));
        authorDAO.update(authorId, author);
        Author authorExpected = authorDAO.read(authorId);
        assertTrue(author.equals(authorExpected));
    }

    @Test
    public void deleteTest() throws DAOException {
        Long authorId = 3L;
        authorDAO.delete(authorId);
        assertNull(authorDAO.read(authorId));
    }

    @Test
    public void readAuthorIdByNewsIdTest() throws DAOException {
        Long newsId = 2L;
        Long authorId = authorDAO.readAuthorIdByNewsId(newsId);
        assertNotNull(authorId);
    }

    @Test
    public void readAllTest() throws DAOException{
        Author author = new Author();
        author.setName(tempName);
        Long authorId=authorDAO.create(author);
        ArrayList<Author> authors=authorDAO.readAll();
        assertFalse(authors.isEmpty());
    }


}