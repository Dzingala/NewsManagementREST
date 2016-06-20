package by.epam.lab.task.repository;

import by.epam.lab.task.entity.News;
import by.epam.lab.task.exceptions.dao.DAOException;

import java.util.ArrayList;

/**
 * @author Ivan Dzinhala
 * @see by.epam.lab.task.repository.GenericRepository
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

    /**
     * Read all news sorted by authors and tags.
     * @param SEARCH_CRITERIA_QUERY
     * @return set of news id
     * @throws DAOException
     */
    ArrayList<News> readBySearchCriteria(final String SEARCH_CRITERIA_QUERY)throws DAOException;

    /**
     * Counts all news corresponding to the search criteria.
     * @param countQuery
     * @return The amount of news corresponding to the search criteria.
     * @throws DAOException
     */
    Long countNews(final String countQuery)throws DAOException;
}
