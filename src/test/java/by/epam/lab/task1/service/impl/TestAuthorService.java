package by.epam.lab.task1.service.impl;

import by.epam.lab.task1.entity.Author;
import by.epam.lab.task1.exceptions.dao.DAOException;
import by.epam.lab.task1.exceptions.service.ServiceException;
import by.epam.lab.task1.repository.AuthorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Ivan Dzinhala
 */
public class TestAuthorService {
    @Mock
    private AuthorRepository authorDAO;

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
        Mockito.verify(authorDAO).create(author);
    }

    @Test
    public void deleteTest() throws DAOException, ServiceException {
        Author author = new Author();
        authorService.delete(author);
        Mockito.verify(authorDAO).update(author.getId(), author);
    }
}
