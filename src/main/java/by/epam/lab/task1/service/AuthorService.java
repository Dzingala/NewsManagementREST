package by.epam.lab.task1.service;


import by.epam.lab.task1.entity.Author;
import by.epam.lab.task1.exceptions.ServiceException;

public interface AuthorService {
    Long create (Author author) throws ServiceException;
    Author readByNewsId(Long newsId) throws ServiceException;
    void delete(Author author) throws ServiceException;
    void update(Author author) throws ServiceException;
}
