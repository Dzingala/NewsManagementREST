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
}
