package by.epam.lab.task1.dao.impl;

import by.epam.lab.task1.dao.NewsDAO;
import by.epam.lab.task1.entity.News;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringDatasourceTest.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml")
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
public class TestJdbcNewsDAO {
    @Autowired
    private JdbcNewsDAO newsDAO;

    @Autowired
    private JdbcAuthorDAO authorDAO;

    @Autowired
    private JdbcTagDAO tagDAO;

    private static final String tempTitle="temptitle";
    private static final String tempShortText="tempshorttext";
    private static final String tempFullText="tempfulltext";
    @Test
    public void createTest() throws DAOException, ParseException {
        News news = new News();
        news.setTitle("TempTitle1");
        news.setShortText("TempShortText1");
        news.setFullText("TempFullText1");
        news.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        news.setModificationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        Long newsId = newsDAO.create(news);

    }

    @Test
    public void readTest() throws DAOException {
        News news = newsDAO.read(1L);
        assertNotNull(news);
    }

    @Test
    public void updateTest() throws DAOException {
        News news = new News();
        news.setTitle(tempTitle);
        news.setShortText(tempShortText);
        news.setFullText(tempFullText);
        news.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        news.setModificationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        Long id= newsDAO.create(news);
        news.setId(id);
        News expectedNews=newsDAO.read(id);
        if(news.equals(expectedNews))assertTrue(true);

    }

    @Test
    public void deleteTest() throws DAOException {
        Long newsId = 3L;
        newsDAO.delete(newsId);
        assertNull(newsDAO.read(newsId));
    }

    @Test
    public void readAllTest() throws DAOException {
        ArrayList<News> newsList = newsDAO.readAll();
        assertNotNull(newsList);
    }

    @Test
    public void countNewsTest() throws DAOException {
        Long newsAmount = newsDAO.countNews();
        assertNotNull(newsAmount);
    }

    @Test
    public void connectNewsWithAuthorTest() throws DAOException {
        News news = new News();
        news.setTitle(tempTitle);
        news.setShortText(tempShortText);
        news.setFullText(tempFullText);
        news.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        news.setModificationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        Long newsId = newsDAO.create(news);
        Long authorId = 3L;
        newsDAO.connectNewsWithAuthor(newsId, authorId);
        Long expectedAuthorId = authorDAO.readAuthorIdByNewsId(newsId);
        assertTrue(authorId.longValue() == expectedAuthorId.longValue());
    }

    @Test
    public void connectNewsWithTagsTest() throws DAOException {
        News news = new News();
        news.setTitle(tempTitle);
        news.setShortText(tempShortText);
        news.setFullText(tempFullText);
        news.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        news.setModificationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        Long newsId = newsDAO.create(news);
        Long tagId = 2L;
        newsDAO.connectNewsWithTags(newsId, tagId);
        ArrayList<Long> expectedTagsId = tagDAO.readTagsIdByNewsId(newsId);
        assertTrue(expectedTagsId.contains(tagId));
    }

    @Test
    public void disconnectNewsWithTagTest() throws DAOException {
        Long newsId = 1L;
        Long tagId = 2L;
        newsDAO.disconnectNewsWithTag(newsId, tagId);
        ArrayList<Long> tagsIdExpected = tagDAO.readTagsIdByNewsId(newsId);
        assertFalse(tagsIdExpected.contains(tagId));
    }

    @Test
    public void disconnectNewsWithAuthorTest() throws DAOException {
        Long newsId = 1L;
        Long authorId = 3L;
        newsDAO.disconnectNewsWithAuthor(newsId, authorId);
        authorId=authorDAO.readAuthorIdByNewsId(newsId);

    }

}
