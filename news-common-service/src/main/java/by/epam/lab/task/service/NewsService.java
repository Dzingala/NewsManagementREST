package by.epam.lab.task.service;


import by.epam.lab.task.entity.News;
import by.epam.lab.task.entity.SearchCriteria;
import by.epam.lab.task.entity.Tag;
import by.epam.lab.task.entity.dto.NewsTO;
import by.epam.lab.task.entity.dto.NewsTORecord;
import by.epam.lab.task.exceptions.service.ServiceException;

import java.util.List;

/**
 * @author Ivan Dzinhala
 */
public interface NewsService {
    /**
     * Get all news existing in database.
     * @return the list of all news.
     * @throws ServiceException
     */
    List<News> readAll() throws ServiceException;

    /**
     * Get all news that are sorted by comments.
     * @return the list of news which are sorted by comments
     * @throws ServiceException
     */
    List<News> readSortedByComments() throws ServiceException;

    /**
     * Get all news that are sorted by search criteria.
     * @param searchCriteria search criteria come
     * @param page single page number of news list
     * @return the list of news sorted by the search criteria
     * @throws ServiceException
     */
    List<News> readBySearchCriteria(SearchCriteria searchCriteria, Long page) throws ServiceException;

    /**
     * Add news and all data that is connected
     * with news (author, tag ...) to database.
     * @param newsTO news transfer object
     * @return The identifier(id) of news created
     * @throws ServiceException
     */
    Long create(NewsTO newsTO)throws ServiceException;

    /**
     * Get news and all data that is connected
     * with news by news id.
     * @param id news id
     * @throws ServiceException
     * @return NewsTO object consisting of all information required.
     */
    NewsTO readDataByNewsId(Long id)throws ServiceException;

    /**
     * Edit news and data that is connected
     * with that news.
     * @param newsTO news transfer object
     * @throws ServiceException
     */
    void update(NewsTO newsTO)throws ServiceException;

    /**
     * Delete news and data that is connected
     * with that news.
     * @param newsTO news transfer object
     * @throws ServiceException
     */
    void delete(NewsTO newsTO)throws ServiceException;

    /**
     * Get all information concerning news by news id in record format.
     * @param newsId news id
     * @return NewsTORecord object consisting of all information required in record form.
     * @throws ServiceException
     */
    NewsTORecord getNewsForEditing(Long newsId)throws ServiceException;

    /**
     * Update all information concerning certain piece of news given in record form.
     * @param newsTORecord news transfer object record
     * @throws ServiceException
     */
    void updateNews(NewsTORecord newsTORecord)throws ServiceException;

    /**
     * Deletes tag and disconnects it with the piece of news.
     * @param tag tag to be deleted
     * @throws ServiceException
     */
    void deleteTag(Tag tag)throws ServiceException;

    /**
     * Counts the amount of pages necessary.
     * @return The amount of pages depending on the amount of news.
     * @throws ServiceException
     */
    Long countPages()throws ServiceException;

    /**
     * Counts the amount of pages necessary to display search criteria news.
     * @return The amount of pages depending on the results of the search criteria.
     * @throws ServiceException
     */

    /**
     * Make query for getting the count of pages according certain search criteria.
     * @param searchCriteria search criteria come
     * @return Query that is ready for injection.
     */
    String composeCriteriaNewsAmountQuery(SearchCriteria searchCriteria);

    /**
     * Count pages suitable for search criteria.
     * @param searchCriteria search criteria come
     * @param page a single page of the news list constructed
     * @return The amount of pages suitable for search criteria.
     * @throws ServiceException
     */
    Long getCriteriaPagesAmount(SearchCriteria searchCriteria,Long page)throws ServiceException;

    /**
     * Creates news with all information concerned according to the certain news record.
     * @param newsTORecord news transfer object record
     * @return The identifier(id) of news created
     * @throws ServiceException
     */
    Long createNews(NewsTORecord newsTORecord) throws ServiceException;
}