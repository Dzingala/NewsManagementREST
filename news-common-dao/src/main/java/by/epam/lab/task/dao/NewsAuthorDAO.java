package by.epam.lab.task.dao;

import by.epam.lab.task.exceptions.dao.DAOException;

/**
 * @author Ivan Dzinhala
 */
public interface NewsAuthorDAO {
    /**
     * Connect news and author.
     * @param newsId
     * @param authorId
     * @throws by.epam.lab.task.exceptions.dao.DAOException
     */
    void joinNewsWithAuthor(Long newsId, Long authorId) throws DAOException;
}
