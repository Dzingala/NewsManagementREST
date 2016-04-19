package by.epam.lab.task1.repository;

import by.epam.lab.task1.entity.News;
import by.epam.lab.task1.exceptions.dao.DAOException;

import java.util.ArrayList;

/**
 * @author Ivan Dzinhala
 * @see by.epam.lab.task1.repository.GenericRepository
 */
public interface NewsRepository extends GenericRepository<News> {
    /**
     * Read all existing news
     * @throws DAOException
     * @return list of all existing news
     */
    ArrayList<News> readAll() throws DAOException;

    /**
     * Count all existing news
     * @throws DAOException
     * @return amount of news
     */
    Long countNews() throws DAOException;

    /**
     * Connect news and tag.
     * @param newsId
     * @param tagId
     * @throws DAOException
     */
    void joinNewsWithTag(Long newsId, Long tagId) throws DAOException;

    /**
     * Connect news and author.
     * @param newsId
     * @param authorId
     * @throws DAOException
     */
    void joinNewsWithAuthor(Long newsId, Long authorId) throws DAOException;

    /**
     * Disconnect news and tag.
     * @param newsId
     * @param tagId
     * @throws DAOException
     */
    void disjoinNewsWithTag(Long newsId, Long tagId) throws DAOException;

    /**
     * Disconnect news and author.
     * @param newsId
     * @param authorId
     * @throws DAOException
     */
    void disjoinNewsWithAuthor(Long newsId, Long authorId) throws DAOException;

    /**
     * Read all news sorted by descending.
     * @throws DAOException
     * @return list of sorted by amount of comments' descending news
     */
    ArrayList<News> readSortedByComments()throws DAOException;
}
