package by.epam.lab.task1.service;


import by.epam.lab.task1.entity.dto.NewsTO;
import by.epam.lab.task1.exceptions.service.ServiceException;
/**
 * @author Ivan Dzinhala
 */
public interface NewsService {

    /**
     * Add news and all data that is connected
     * with news (author, tag ...) to database.
     * @param newsTO
     * @throws ServiceException
     */
    void create(NewsTO newsTO)throws ServiceException;

    /**
     * Get news and all data that is connected
     * with news by news id.
     * @param id
     * @throws ServiceException
     * @return role id of required user id
     */
    NewsTO readDataByNewsId(Long id)throws ServiceException;

    /**
     * Edit news and data that is connected
     * with that news.
     * @param newsTO
     * @throws ServiceException
     */
    void update(NewsTO newsTO)throws ServiceException;

    /**
     * Delete news and data that is connected
     * with that news.
     * @param newsTO
     * @throws ServiceException
     */
    void delete(NewsTO newsTO)throws ServiceException;

}
