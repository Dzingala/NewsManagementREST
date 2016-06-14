package by.epam.lab.task.repository;


import by.epam.lab.task.entity.Tag;
import by.epam.lab.task.exceptions.dao.DAOException;

import java.util.ArrayList;
/**
 * @author Ivan Dzinhala
 * @see by.epam.lab.task.repository.GenericRepository
 */
public interface TagRepository extends GenericRepository<Tag> {

    /**
     * Find all tags id by news id.
     * That means that we select all tags for the news.
     * @param newsId
     * @throws DAOException
     * @return list of tags id
     */
    ArrayList<Long> readTagsIdByNewsId(Long newsId) throws DAOException;
    /**
     * This method is used for finding news according to the tag id
     * @param tagId Tag id which is using for finding
     * @return Returns list of news id
     * @throws DAOException
     */
    ArrayList<Long> readNewsIdByTagId(Long tagId) throws DAOException;
}
