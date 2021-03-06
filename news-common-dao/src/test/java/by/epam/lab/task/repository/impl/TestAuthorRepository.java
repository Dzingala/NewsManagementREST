package by.epam.lab.task.repository.impl;

import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.AuthorRepository;
import by.epam.lab.task.entity.Author;
import by.epam.lab.task.exceptions.dao.DAOException;
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
import java.util.List;

import static org.junit.Assert.*;
/**
 * @author Ivan Dzinhala
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringDatasourceTest.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml")
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
public class TestAuthorRepository {
    @Autowired
    private AuthorRepository authorRepository;

    private static final String tempName="tempname";

    @Test
    public void create() throws DAOException {
        Long authorId=authorRepository.create(new Author(1L,tempName,new Timestamp(System.currentTimeMillis())));
        assertNotNull(authorId);
    }

    @Test
    public void read() throws DAOException {
        assertNotNull(authorRepository.read(2L));
    }

    @Test
    public void update() throws DAOException {
        Long authorId = 3L;
        Author author = authorRepository.read(authorId);
        author.setName(tempName);
        authorRepository.update(authorId, author);
        Author authorExpected = authorRepository.read(authorId);
        assertTrue(author.equals(authorExpected));
    }

    @Test(expected = DAOException.class)
    public void delete() throws DAOException {
        Long authorId = 3L;
        authorRepository.delete(authorId);
        assertNull(authorRepository.read(authorId));
    }

    @Test
    public void readAuthorIdByNewsId() throws DAOException {
        Long newsId = 2L;
        Long authorId = authorRepository.readAuthorIdByNewsId(newsId);
        assertNotNull(authorId);
    }

    @Test
    public void readAll() throws DAOException{
        List<Author> authors= authorRepository.readAll();
        assertFalse(authors.isEmpty());
    }


}