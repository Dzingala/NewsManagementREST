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
        News news = new News();
        news.setTitle("TempTitle1");
        news.setShortText("TempShortText1");
        news.setFullText("TempFullText1");
        news.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        news.setModificationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        Long newsId = newsRepository.create(news);

    }

    @Test
    public void readTest() throws DAOException {
        News news = newsRepository.read(1L);
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
        Long id= newsRepository.create(news);
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
        ArrayList<News> newsList = newsRepository.readAll();
        assertNotNull(newsList);
    }

    @Test
    public void countNewsTest() throws DAOException {
        Long newsAmount = newsRepository.countNews();
        assertNotNull(newsAmount);
    }

    @Test
    public void joinNewsWithAuthorTest() throws DAOException {
        News news = new News();
        news.setTitle(tempTitle);
        news.setShortText(tempShortText);
        news.setFullText(tempFullText);
        news.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        news.setModificationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        Long newsId = newsRepository.create(news);
        Long authorId = 3L;
        newsRepository.joinNewsWithAuthor(newsId, authorId);
        Long expectedAuthorId = authorRepository.readAuthorIdByNewsId(newsId);
        assertTrue(authorId.longValue() == expectedAuthorId.longValue());
    }

    @Test
    public void joinNewsWithTagsTest() throws DAOException {
        News news = new News();
        news.setTitle(tempTitle);
        news.setShortText(tempShortText);
        news.setFullText(tempFullText);
        news.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        news.setModificationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        Long newsId = newsRepository.create(news);
        Long tagId = 2L;
        newsRepository.joinNewsWithTag(newsId, tagId);
        ArrayList<Long> expectedTagsId = tagRepository.readTagsIdByNewsId(newsId);
        assertTrue(expectedTagsId.contains(tagId));
    }

    @Test
    public void disjoinNewsWithTagTest() throws DAOException {
        Long newsId = 1L;
        Long tagId = 2L;
        newsRepository.disjoinNewsWithTag(newsId, tagId);
        ArrayList<Long> tagsIdExpected = tagRepository.readTagsIdByNewsId(newsId);
        assertFalse(tagsIdExpected.contains(tagId));
    }

    @Test
    public void disjoinNewsWithAuthorTest() throws DAOException {
        Long newsId = 1L;
        Long authorId = 3L;
        newsRepository.disjoinNewsWithAuthor(newsId, authorId);
        authorId= authorRepository.readAuthorIdByNewsId(newsId);
    }

    @Test
    public void readBySortedTest()throws DAOException{
        News newtemp1=new News();
        newtemp1.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        newtemp1.setFullText(tempFullText);
        newtemp1.setShortText(tempShortText);
        newtemp1.setModificationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        newtemp1.setTitle(tempTitle);
        newtemp1.setId(1l);
        News newtemp2=new News();
        newtemp2.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        newtemp2.setFullText(tempFullText);
        newtemp2.setShortText(tempShortText);
        newtemp2.setModificationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        newtemp2.setTitle(tempTitle);
        newtemp2.setId(2l);
        News newtemp3=new News();
        newtemp3.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        newtemp3.setFullText(tempFullText);
        newtemp3.setShortText(tempShortText);
        newtemp3.setModificationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        newtemp3.setTitle(tempTitle);
        newtemp3.setId(5l);
        ArrayList<News> sortedNews=newsRepository.readSortedByComments();
        LinkedHashSet<Long> expectedIdSet=new LinkedHashSet<>();
        expectedIdSet.add(newtemp1.getId());
        expectedIdSet.add(newtemp2.getId());
        expectedIdSet.add(newtemp3.getId());
        LinkedHashSet<Long> sorted=new LinkedHashSet<>();
        sorted.add(sortedNews.get(0).getId());
        sorted.add(sortedNews.get(1).getId());
        sorted.add(sortedNews.get(2).getId());
        boolean isEqual=sorted.equals(expectedIdSet);
        assertTrue(isEqual);
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
        String query = newsRepository.composeSearchCriteriaQuery(searchCriteria);
        ArrayList<News> newsList = newsRepository.readBySearchCriteria(query,1l,1);
        ArrayList<Long> newsIdListRequired = new ArrayList<>();
        newsIdListRequired.add(1L);
        assertTrue(newsList.get(0).getId() == (newsIdListRequired.get(0)));
    }

}
