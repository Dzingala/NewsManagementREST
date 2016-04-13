package by.epam.lab.task1.dao;

import by.epam.lab.task1.entity.News;
import by.epam.lab.task1.exceptions.DAOException;

import java.util.ArrayList;


public interface NewsDAO extends GenericDAO<News>{
    ArrayList<News> readAll() throws DAOException;

    Long countNews() throws DAOException;

    void connectNewsWithTags(Long newsId, Long tagId) throws DAOException;

    void connectNewsWithAuthor(Long newsId, Long authorId) throws DAOException;

    void disconnectNewsWithTags(Long newsId) throws DAOException;

    void disconnectNewsWithAuthor(Long newsId) throws DAOException;
}
