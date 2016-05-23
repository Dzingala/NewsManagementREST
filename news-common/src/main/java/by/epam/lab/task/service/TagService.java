package by.epam.lab.task.service;


import by.epam.lab.task.entity.Tag;
import by.epam.lab.task.exceptions.service.ServiceException;

import java.util.ArrayList;
/**
 * @author Ivan Dzinhala
 */
public interface TagService {
    /**
     * Add tag to database.
     * Validate parameters.
     * @param tag
     * @throws ServiceException
     * @return id of row that has been inserted
     */
    Long create(Tag tag)throws ServiceException;

    /**
     * Get tag by its id
     * @param id
     * @throws ServiceException
     * @return tag that was read by id
     */
    Tag readById(Long id)throws ServiceException;

    /**
     * Get all tags by news id.
     * @param newsId
     * @throws ServiceException
     */
    ArrayList<Tag> readTagsByNewsId(Long newsId)throws ServiceException;

    /**
     * Update tag's content.
     * @param tag
     * @throws ServiceException
     */
    void update(Tag tag)throws ServiceException;

    /**
     * Delete tag from database.
     * @param tag
     * @throws ServiceException
     */
    void delete(Tag tag)throws ServiceException;
}
