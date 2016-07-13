package by.epam.lab.task.service;


import by.epam.lab.task.entity.Author;
import by.epam.lab.task.exceptions.service.ServiceException;

import java.util.List;

/**
 * @author Ivan Dzinhala
 */
public interface AuthorService {
    /**
     * Get author from database by id.
     * @param id
     * @return required author.
     * @throws ServiceException
     */
    Author read (Long id) throws ServiceException;
    /**
     * Read all authors from database.
     * @return list of all authors
     * @throws ServiceException
     */
    List<Author> readAll() throws ServiceException;
    /**
     * Add author to database.
     * @param author
     * @throws ServiceException
     * @return id of row that has been created
     */
    Long create (Author author) throws ServiceException;

    /**
     * Get author by news id.
     * @param newsId
     * @throws ServiceException
     * @return author
     */
    Author readByNewsId(Long newsId) throws ServiceException;

    /**
     * Make the author expired.
     * @param author
     * @throws ServiceException
     */
    void delete(Author author) throws ServiceException;

    /**
     * Update information about certain author.
     * @param author
     * @throws ServiceException
     */
    void update(Author author) throws ServiceException;
}
