package by.epam.lab.task1.repository;


import by.epam.lab.task1.entity.Tag;
import by.epam.lab.task1.exceptions.DAOException;

import java.util.ArrayList;
/**
 * @author Ivan Dzinhala
 * @see by.epam.lab.task1.repository.GenericRepository
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
