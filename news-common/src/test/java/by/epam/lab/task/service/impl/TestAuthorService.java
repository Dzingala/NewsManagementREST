package by.epam.lab.task.service.impl;

import by.epam.lab.task.entity.Author;
import by.epam.lab.task.entity.News;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.repository.AuthorRepository;
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
public class TestAuthorService {
    @Mock
    private AuthorRepository authorRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    public void createAuthorTest() throws DAOException, ServiceException {
        Author author = new Author();
        authorService.create(author);
        Mockito.verify(authorRepository).create(author);
    }
    @Test
    public void readTest() throws DAOException, ServiceException {
        Long authorId=1l;
        authorService.read(authorId);
        Mockito.verify(authorRepository).read(authorId);
    }
    @Test
    public void deleteTest() throws DAOException, ServiceException {
        Author author = new Author();
        authorService.delete(author);
        Mockito.verify(authorRepository).update(author.getId(), author);
    }
    @Test
    public void readByNewsIdTest() throws  DAOException, ServiceException{
        News news = new News();
        Author author= authorService.readByNewsId(news.getId());
        Mockito.verify(authorRepository).readAuthorIdByNewsId(news.getId());
    }
    @Test
    public void updateTest() throws DAOException,ServiceException{
        Author author=new Author();
        authorService.update(author);
        Mockito.verify(authorRepository).update(author.getId(),author);
    }
    @Test
    public void readAllTest()throws DAOException,ServiceException{
        Author authorExpected = new Author();
        ArrayList<Author> authorsExpected=new ArrayList<>();
        authorsExpected.add(authorExpected);
        Mockito.when(authorRepository.readAll()).thenReturn(authorsExpected);
        ArrayList<Author> authors =authorService.readAll();
        assertTrue(authors.equals(authorsExpected));
    }
}
