package by.epam.lab.task.service.impl;

import by.epam.lab.task.entity.Comment;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.repository.CommentsRepository;
import by.epam.lab.task.service.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ivan Dzinhala
 * @see CommentService
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{

    private final static Logger logger= Logger.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentsRepository commentsRepository;
    /**
     * Implementation of CommentService method create.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long create(Comment comment) throws ServiceException {
        logger.debug("Creating comment in CommentServiceImpl");
        Long commentId = null;
        try{
            comment.setCreationDate(new Timestamp(new Date().getTime()));
            commentId=commentsRepository.create(comment);
        }catch (DAOException e){
            logger.error("DAOException while creating comment in CommentServiceImpl");
            throw new ServiceException("ServiceException while creating comment",e);
        }
        return commentId;
    }
    /**
     * Implementation of CommentService method readAllByNewsId.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Comment> readAllByNewsId(Long newsId) throws ServiceException {
        logger.debug("Reading all comments by news id in CommentServiceImpl");
        List<Comment> comments = null;
        List<Long> commentIds=null;
        try{
            commentIds=commentsRepository.readCommentsIdByNewsId(newsId);
            comments=new ArrayList<>();
            for(Long commentsId: commentIds){
                comments.add(commentsRepository.read(commentsId));
            }
        }catch (DAOException e){
            logger.error("DAOException while reading all comments by news id in CommentServiceImpl");
            throw new ServiceException("ServiceException while reading all comments by news id",e);
        }
        return comments;
    }
    /**
     * Implementation of CommentService method delete.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Comment comment) throws ServiceException {
        logger.debug("Deleting comment in CommentServiceImpl");
        try{
            commentsRepository.delete(comment.getId());
        } catch (DAOException e) {
            logger.error("DAOException while deleting comment in CommentServiceImpl");
            throw new ServiceException("ServiceException while deleting comment",e);
        }
    }
    /**
     * Implementation of CommentService method update.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Comment comment) throws ServiceException {
        logger.debug("Updating comment in CommentServiceImpl");
        try{
            commentsRepository.update(comment.getId(),comment);
        } catch (DAOException e) {
            logger.error("DAOException while updating comment in CommentServiceImpl");
            throw new ServiceException("ServiceException while updating comment",e);
        }
    }
    /**
     * Implementation of CommentService method read.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Comment read(Long commentId) throws ServiceException {
        logger.debug("Reading comment in CommentServiceImpl");
        Comment comment=null;
        try{
            comment=commentsRepository.read(commentId);
        } catch (DAOException e) {
            logger.error("DAOException while reading comment in CommentServiceImpl");
            throw new ServiceException("ServiceException while reading comment",e);
        }
        return comment;
    }
}
