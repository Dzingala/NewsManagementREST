package by.epam.lab.task.head;

import by.epam.lab.task.entity.dto.NewsTO;
import by.epam.lab.task.entity.dto.NewsTORecord;
import by.epam.lab.task.exceptions.service.ServiceException;

/**
 * @author Ivan Dzinhala
 */
public interface NewsAuthorService {

    /**
     * Creates news with all information concerned according to the certain news record.
     * @param newsTORecord news transfer object record
     * @return The identifier(id) of news created
     * @throws ServiceException
     */
    Long createNews(NewsTORecord newsTORecord) throws ServiceException;
}
