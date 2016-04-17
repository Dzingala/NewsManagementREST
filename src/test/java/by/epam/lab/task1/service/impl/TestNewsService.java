package by.epam.lab.task1.service.impl;

import by.epam.lab.task1.entity.Author;
import by.epam.lab.task1.entity.Comment;
import by.epam.lab.task1.entity.News;
import by.epam.lab.task1.entity.Tag;
import by.epam.lab.task1.entity.dto.NewsTO;
import by.epam.lab.task1.exceptions.dao.DAOException;
import by.epam.lab.task1.exceptions.service.ServiceException;
import by.epam.lab.task1.repository.NewsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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

        Mockito.verify(newsRepository).disjoinNewsWithAuthor(news.getId(), author.getId());
        Mockito.verify(authorService).delete(author);
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



}
