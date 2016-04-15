package by.epam.lab.task1.service.impl;

import by.epam.lab.task1.dao.AuthorDAO;
import by.epam.lab.task1.entity.Author;
import by.epam.lab.task1.exceptions.ServiceException;
import by.epam.lab.task1.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDAO authorDAO;
    @Override
    public Long create(Author author) throws ServiceException {
        return null;
    }

    @Override
    public Author readByNewsId(Long newsId) throws ServiceException {
        return null;
    }

    @Override
    public void delete(Author author) throws ServiceException {

    }

    @Override
    public void update(Author author) throws ServiceException {

    }
}
