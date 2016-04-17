package by.epam.lab.task1.service.impl;


import by.epam.lab.task1.entity.Author;
import by.epam.lab.task1.entity.Comment;
import by.epam.lab.task1.entity.News;
import by.epam.lab.task1.entity.Tag;
import by.epam.lab.task1.entity.dto.NewsTO;
import by.epam.lab.task1.exceptions.dao.DAOException;
import by.epam.lab.task1.exceptions.service.ServiceException;
import by.epam.lab.task1.repository.NewsRepository;
import by.epam.lab.task1.service.AuthorService;
import by.epam.lab.task1.service.CommentService;
import by.epam.lab.task1.service.NewsService;
import by.epam.lab.task1.service.TagService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author Ivan Dzinhala
 * @see NewsService
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService {

    private final static Logger logger= Logger.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(NewsTO newsTO) throws ServiceException {
        logger.debug("Creating news and all information connected with it in NewsService");
        //validation
        News news = newsTO.getNews();
        Author author = newsTO.getAuthor();
        ArrayList<Comment> commentList = newsTO.getCommentList();
        ArrayList<Tag> tagList = newsTO.getTagList();
        try {
            Long newsId = newsRepository.create(news);
            Long authorId = authorService.create(author);
            newsRepository.joinNewsWithAuthor(newsId, authorId);
            for(Comment com : commentList){
                com.setNewsId(newsId);
                commentService.create(com);
            }
            Long tagId = null;
            for(Tag tag : tagList){
                tagId = tagService.create(tag);
                newsRepository.joinNewsWithTag(newsId, tagId);
            }
        } catch (DAOException e) {
            logger.error("DAOException while filling news in NewsService");
            throw new ServiceException("ServiceException while filling news", e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public NewsTO readDataByNewsId(Long id) throws ServiceException {
        logger.debug("Reading news and all information connected with it in NewsService");
        NewsTO newsTO = null;
        try {
            News news = newsRepository.read(id);
            Author author = authorService.readByNewsId(id);
            ArrayList<Tag> tagList = tagService.readTagsByNewsId(id);
            ArrayList<Comment> commentList = commentService.readAllByNewsId(id);
            newsTO = new NewsTO();
            newsTO.setNews(news);
            newsTO.setAuthor(author);
            newsTO.setTagList(tagList);
            newsTO.setCommentList(commentList);
        } catch (DAOException e) {
            logger.error("DAOException while reading news in NewsService");
            throw new ServiceException("ServiceException while reading news", e);
        }
        return newsTO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(NewsTO newsTO) throws ServiceException {
        logger.debug("Updating news and all information connected with it in NewsService");
        try {
            newsRepository.update(newsTO.getNews().getId(), newsTO.getNews());
        } catch (DAOException e) {
            logger.error("DAOException while updating news in NewsService");
            throw new ServiceException("ServiceException while updating news", e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(NewsTO newsTO) throws ServiceException {
        logger.debug("Deleting news and all information connected with it in NewsService");
        News news = newsTO.getNews();
        Author author = newsTO.getAuthor();
        ArrayList<Comment> commentList = newsTO.getCommentList();
        ArrayList<Tag> tagList = newsTO.getTagList();
        try {
            newsRepository.disjoinNewsWithAuthor(news.getId(), author.getId());
            authorService.delete(author);
            for(Comment com : commentList){
                commentService.delete(com);
            }
            for(Tag tag : tagList){
                newsRepository.disjoinNewsWithTag(news.getId(), tag.getId());
            }
            newsRepository.delete(news.getId());

        } catch (DAOException e) {
            logger.error("DAOException while deleting news and connected information in NewsService");
            throw new ServiceException("ServiceException while deleting news", e);
        }
    }
}
