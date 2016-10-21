package by.epam.lab.task.service;


import by.epam.lab.task.entity.Tag;
import by.epam.lab.task.exceptions.service.ServiceException;

<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/service/TagService.java
import java.util.List;
=======
import java.util.ArrayList;
>>>>>>> develop/netcracker:news-common-service/src/main/java/by/epam/lab/task/service/TagService.java

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
    List<Tag> readTagsByNewsId(Long newsId)throws ServiceException;

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

    /**
     * Read all tags from database.
     * @return list of all tags
     * @throws ServiceException
     */
    List<Tag> readAll()throws ServiceException;

    /**
     * Read all news' id by tag id given.
     * @param tagId
     * @return
     * @throws ServiceException
     */
    List<Long> readNewsIdByTagId(Long tagId)throws ServiceException;

}
