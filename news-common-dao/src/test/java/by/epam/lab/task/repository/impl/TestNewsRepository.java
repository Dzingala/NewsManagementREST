package by.epam.lab.task.repository.impl;

import by.epam.lab.task.entity.Author;
import by.epam.lab.task.entity.News;
import by.epam.lab.task.entity.SearchCriteria;
import by.epam.lab.task.entity.Tag;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
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
public class TestNewsRepository {
    @Autowired
    private NewsRepositoryImpl newsRepository;

    @Autowired
    private AuthorRepositoryImpl authorRepository;

    @Autowired
    private TagRepositoryImpl tagRepository;

    private static final String tempTitle="temptitle";
    private static final String tempShortText="tempshorttext";
    private static final String tempFullText="tempfulltext";
    @Test
    public void createTest() throws DAOException, ParseException {
        News news = new News(1L,tempTitle,tempShortText,tempFullText,new Timestamp(new java.util.Date().getTime()),new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        Long newsId = newsRepository.create(news);
        assertNotNull(newsId);

    }

    @Test
    public void readTest() throws DAOException {
        News news = newsRepository.read(1L);
        assertNotNull(news);
    }

    @Test
    public void updateTest() throws DAOException {
        News news = new News(1L,tempTitle,tempShortText,tempFullText,new Timestamp(new java.util.Date().getTime()),new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        Long id = newsRepository.create(news);
        news.setId(id);
        news.setFullText("newFullText");
        newsRepository.update(id,news);
        News expectedNews= newsRepository.read(id);
        if(news.equals(expectedNews))assertTrue(true);

    }

    @Test(expected = DAOException.class)
    public void deleteTest() throws DAOException {
        Long newsId = 3L;
        newsRepository.delete(newsId);
        assertNull(newsRepository.read(newsId));
    }

    @Test
    public void readAllTest() throws DAOException {
        List<News> newsList = newsRepository.readAll();
        assertNotNull(newsList);
    }

    @Test
    public void countNewsTest() throws DAOException {
        Long newsAmount = newsRepository.countNews();
        assertNotNull(newsAmount);
    }

    @Test
    public void joinNewsWithAuthorTest() throws DAOException {
        News news = new News(1L,tempTitle,tempShortText,tempFullText,new Timestamp(new java.util.Date().getTime()),new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        Long newsId = newsRepository.create(news);
        Long authorId = 3L;
        newsRepository.joinNewsWithAuthor(newsId, authorId);
        Long expectedAuthorId = authorRepository.readAuthorIdByNewsId(newsId);
        assertTrue(authorId.longValue() == expectedAuthorId.longValue());
    }

    @Test
    public void joinNewsWithTagsTest() throws DAOException {
        News news = new News(1L,tempTitle,tempShortText,tempFullText,new Timestamp(new java.util.Date().getTime()),new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        Long newsId = newsRepository.create(news);
        Long tagId = 2L;
        newsRepository.joinNewsWithTag(newsId, tagId);
        List<Long> expectedTagsId = tagRepository.readTagsIdByNewsId(newsId);
        assertTrue(expectedTagsId.contains(tagId));
    }

    @Test
    public void disjoinNewsWithTagTest() throws DAOException {
        Long newsId = 1L;
        Long tagId = 2L;
        newsRepository.disjoinNewsWithTag(newsId, tagId);
        List<Long> tagsIdExpected = tagRepository.readTagsIdByNewsId(newsId);
        assertFalse(tagsIdExpected.contains(tagId));
    }

    @Test
    public void disjoinNewsWithAuthorTest() throws DAOException {
        Long newsId = 1L;
        Long authorId = 3L;
        newsRepository.disjoinNewsWithAuthor(newsId, authorId);
        authorId= authorRepository.readAuthorIdByNewsId(newsId);
        assertEquals(authorId,(Long)1L);
    }

    @Test
    public void readBySortedTest()throws DAOException{
        News news = new News(1L,tempTitle,tempShortText,tempFullText,new Timestamp(new java.util.Date().getTime()),new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        LinkedHashSet<Long> expectedIdSet=new LinkedHashSet<>();
        expectedIdSet.add(news.getId());
        assertTrue(!expectedIdSet.isEmpty());
    }
    @Test
    public void readBySearchCriteriaTest()throws DAOException{
        SearchCriteria searchCriteria = new SearchCriteria();
        final Author author = new Author();
        author.setId(1l);
        final Tag tag = new Tag();
        tag.setId(1L);
        final Tag tag2=new Tag();
        tag2.setId(2l);
        ArrayList<Tag> tagList = new ArrayList<Tag>() {
            {
                add(tag);
                add(tag2);
            }
        };
        searchCriteria.setAuthorId(author.getId());
        ArrayList<Long> tagsId = new ArrayList<>();
        for(Tag tag1: tagList){
            tagsId.add(tag1.getId());
        }
        searchCriteria.setTagsId(tagsId);
        String query = NewsRepositoryImpl.composeSearchCriteriaQuery(searchCriteria);
        List<News> newsList = newsRepository.readBySearchCriteria(query,1l,1);
        ArrayList<Long> newsIdListRequired = new ArrayList<>();
        newsIdListRequired.add(1L);
        assertTrue(newsList.get(0).getId() == (newsIdListRequired.get(0)));
    }

}
