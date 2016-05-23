package by.epam.lab.task.service.impl;

import by.epam.lab.task.entity.*;
import by.epam.lab.task.entity.dto.NewsTO;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.repository.NewsRepository;
import by.epam.lab.task.repository.impl.NewsRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
        Comment comment = new Comment();
        Tag tag = new Tag();

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
        Mockito.verify(authorService).create(author);
        Mockito.verify(newsRepository).joinNewsWithAuthor(newsId, authorId);
        Mockito.verify(commentService).create(comment);
        Mockito.verify(tagService).create(tag);
        Mockito.verify(newsRepository).joinNewsWithTag(newsId, tagId);
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
        author.setExpired();
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
        tag.setId(1l);
        final Tag tag2 = new Tag();
        tag2.setId(2l);
        SearchCriteria searchCriteria = new SearchCriteria();
        Author author = new Author();
        author.setId(1L);
        searchCriteria.setAuthor(author);
        searchCriteria.addTag(tag);
        searchCriteria.addTag(tag2);
        String query = NewsRepositoryImpl.composeSearchCriteriaQuery(searchCriteria);
        Mockito.when(newsRepository.readBySearchCriteria(query)).thenReturn(newsList);
        Mockito.when(newsRepository.read(newsList.get(0).getId())).thenReturn(news);

        ArrayList<News> serviceNewsList=newsService.readBySearchCriteria(searchCriteria);
        assertTrue(serviceNewsList.equals(newsList));
        Mockito.verify(newsRepository).readBySearchCriteria(query);
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
        Mockito.when(newsRepository.readSortedByComments()).thenReturn(newsList);
        Mockito.when(newsRepository.read(newsList.get(0).getId())).thenReturn(news);


        ArrayList<News> serviceNewsList = newsService.readSortedByComments();

        assertTrue(newsList.equals(serviceNewsList));
        Mockito.verify(newsRepository).readSortedByComments();

    }

}
