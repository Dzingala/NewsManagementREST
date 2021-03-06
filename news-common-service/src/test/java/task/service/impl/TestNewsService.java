package task.service.impl;

import by.epam.lab.task.entity.*;
import by.epam.lab.task.entity.dto.NewsTO;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.repository.NewsRepository;
import by.epam.lab.task.repository.impl.NewsRepositoryImpl;
import by.epam.lab.task.service.impl.AuthorServiceImpl;
import by.epam.lab.task.service.impl.CommentServiceImpl;
import by.epam.lab.task.service.impl.NewsServiceImpl;
import by.epam.lab.task.service.impl.TagServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * @author Ivan Dzinhala
 */
public class TestNewsService {
    @Mock
    private NewsRepository newsRepository;

    @Mock
    private CommentServiceImpl commentService;

    @Mock
    private AuthorServiceImpl authorService;

    @Mock
    private TagServiceImpl tagService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private NewsServiceImpl newsService;

    @Test
    public void createTest() throws DAOException, ServiceException {
        News news = new News();
        Author author = new Author();
        authorService.create(author);
        Comment comment = new Comment();
        commentService.create(comment);
        Tag tag = new Tag();
        tagService.create(tag);
        ArrayList<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(tag);

        NewsTO newsTO = new NewsTO(news, author, tagList, commentList);

        Long newsId = 1L;
        Long authorId = 1L;
        Long tagId = 1L;

        Mockito.when(newsRepository.create(news)).thenReturn(newsId);
        Mockito.when(authorService.create(author)).thenReturn(authorId);
        Mockito.when(tagService.create(tag)).thenReturn(tagId);

        newsService.create(newsTO);

        Mockito.verify(newsRepository).create(news);
    }

    @Test
    public void updateTest() throws DAOException, ServiceException {
        News news = new News();
        Author author = new Author();
        Comment comment = new Comment();
        ArrayList<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        Tag tag = new Tag();
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(tag);

        NewsTO newsTO = new NewsTO(news, author, tagList, commentList);

        newsService.update(newsTO);

        Mockito.verify(newsRepository).update(news.getId(), news);
    }

    @Test
    public void deleteTest() throws DAOException, ServiceException {
        News news = new News();
        Author author = new Author();
        Comment comment = new Comment();
        ArrayList<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        Tag tag = new Tag();
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(tag);

        NewsTO newsTO = new NewsTO(news, author, tagList, commentList);

        newsService.delete(newsTO);
        author.setExpired(new Timestamp(System.currentTimeMillis()));
        Mockito.verify(newsRepository).disjoinNewsWithAuthor(news.getId(), author.getId());
        Mockito.verify(commentService).delete(comment);
        Mockito.verify(newsRepository).disjoinNewsWithTag(news.getId(), tag.getId());
        Mockito.verify(newsRepository).delete(news.getId());
    }

    @Test
    public void readTest () throws DAOException, ServiceException {
        News news = new News();
        Author author = new Author();
        Comment comment = new Comment();
        ArrayList<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        Tag tag = new Tag();
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(tag);

        NewsTO newsTO = new NewsTO(news, author, tagList, commentList);

        Mockito.when(newsRepository.read(news.getId())).thenReturn(news);
        Mockito.when(authorService.readByNewsId(news.getId())).thenReturn(author);
        Mockito.when(tagService.readTagsByNewsId(news.getId())).thenReturn(tagList);
        Mockito.when(commentService.readAllByNewsId(news.getId())).thenReturn(commentList);

        newsService.readDataByNewsId(news.getId());

        Mockito.verify(newsRepository).read(news.getId());
        Mockito.verify(authorService).readByNewsId(news.getId());
        Mockito.verify(tagService).readTagsByNewsId(news.getId());
        Mockito.verify(commentService).readAllByNewsId(news.getId());
    }

    @Test
    public void readBySearchCriteriaTest() throws DAOException, ServiceException {
        final News news = new News();
        news.setId(1L);
        ArrayList<News> newsList = new ArrayList<News>() {
            {
                add(news);
            }
        };
        final Tag tag = new Tag();
        tag.setId(1L);
        final Tag tag2 = new Tag();
        tag2.setId(2L);
        SearchCriteria searchCriteria = new SearchCriteria();
        Author author = new Author();
        author.setId(1L);
        searchCriteria.setAuthorId(author.getId());
        ArrayList<Long> tagsId= new ArrayList<>();
        tagsId.add(tag.getId());
        tagsId.add(tag2.getId());
        searchCriteria.setTagsId(tagsId);
        String query = NewsRepositoryImpl.composeSearchCriteriaQuery(searchCriteria);
        Mockito.when(newsRepository.readBySearchCriteria(query, 1L,1)).thenReturn(newsList);
        Mockito.when(newsRepository.read(newsList.get(0).getId())).thenReturn(news);

        List<News> serviceNewsList=newsService.readBySearchCriteria(searchCriteria, 1L);
        assertTrue(!serviceNewsList.equals(newsList));
    }

    @Test
    public void readSortedByCommentsTest() throws DAOException, ServiceException {
        final News news = new News();
        news.setId(1L);
        ArrayList<News> newsList = new ArrayList<News>() {
            {
                add(news);
            }
        };
        Mockito.when(newsRepository.read(newsList.get(0).getId())).thenReturn(news);


        List<News> serviceNewsList = newsService.readSortedByComments();

        assertTrue(!newsList.equals(serviceNewsList));

    }
    @Test
    public void readAllTest()throws DAOException,ServiceException{
        News newsExpected = new News();
        ArrayList<News> newsListExpected=new ArrayList<>();
        newsListExpected.add(newsExpected);
        Mockito.when(newsRepository.readAll()).thenReturn(newsListExpected);
        List<News> news =newsService.readAll();
        assertTrue(news.equals(newsListExpected));
    }

}
