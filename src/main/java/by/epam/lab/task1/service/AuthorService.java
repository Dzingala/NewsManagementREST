package by.epam.lab.task1.service;


import by.epam.lab.task1.entity.Author;
import by.epam.lab.task1.exceptions.dao.DAOException;
import by.epam.lab.task1.exceptions.service.ServiceException;
/**
 * @author Ivan Dzinhala
 */
public interface AuthorService {

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
    Author readByNewsId(Long newsId) throws ServiceException, DAOException;

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
