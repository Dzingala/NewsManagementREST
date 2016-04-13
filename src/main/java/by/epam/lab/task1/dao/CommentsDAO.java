package by.epam.lab.task1.dao;


import by.epam.lab.task1.entity.Comment;
import by.epam.lab.task1.exceptions.DAOException;

import java.util.ArrayList;

public interface CommentsDAO extends GenericDAO<Comment>{
    ArrayList<Long> readCommentsIdByNewsId(Long newsId) throws DAOException;
}
