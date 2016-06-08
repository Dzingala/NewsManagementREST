package by.epam.lab.task.service.impl;


import by.epam.lab.task.entity.*;
import by.epam.lab.task.entity.dto.NewsTO;
import by.epam.lab.task.entity.dto.NewsTORecord;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.repository.NewsRepository;
import by.epam.lab.task.repository.impl.NewsRepositoryImpl;
import by.epam.lab.task.service.AuthorService;
import by.epam.lab.task.service.CommentService;
import by.epam.lab.task.service.NewsService;
import by.epam.lab.task.service.TagService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

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

    /**
     * Implementation of NewsService method readAll.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ArrayList<News> readAll() throws ServiceException {
        logger.debug("Reading all authors in AuthorService");
        ArrayList<News> news = null;
        try{
            news=newsRepository.readAll();
        } catch (DAOException e) {
            logger.error("DAOException while reading all news in AuthorService");
            throw new ServiceException("ServiceException while reading all news", e);
        }
        return news;
    }

    /**
     * Implementation of NewsService method readSortedByComments.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ArrayList<News> readSortedByComments() throws ServiceException {
        logger.debug("Reading news sorted by comments in NewsServiceImpl");
        ArrayList<News> newsList = null;
        try {
            newsList = newsRepository.readSortedByComments();
        } catch (DAOException e) {
            logger.error("DAOException while reading news sorted by comments in NewsServiceImpl");
            throw new ServiceException("ServiceException while reading news sorted by comments");
        }
        return newsList;
    }
    /**
     * Implementation of NewsService method readBySearchCriteria.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ArrayList<News> readBySearchCriteria(SearchCriteria searchCriteria) throws ServiceException {
        logger.debug("Reading news sorted by search criteria in NewsServiceImpl");
        ArrayList<News> newsList = null;
        String query = NewsRepositoryImpl.composeSearchCriteriaQuery(searchCriteria);
        if(query==null){
            logger.error("ServiceException while creating a search criteria query in NewsServiceImpl");
            throw new ServiceException("ServiceException while creating a search criteria query.");
        }
        try{
            newsList=newsRepository.readBySearchCriteria(query);
        } catch (DAOException e) {
            logger.error("DAOException while reading news sorted by search criteria in NewsServiceImpl");
            throw new ServiceException("ServiceException while reading news sorted by search criteria");
        }
        return newsList;
    }
    /**
     * Implementation of NewsService method create.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(NewsTO newsTO) throws ServiceException {
        logger.debug("Creating news and all information connected with it in NewsServiceImpl");
        //validation
        News news = newsTO.getNews();
        Author author = newsTO.getAuthor();
        ArrayList<Comment> commentList = newsTO.getCommentList();
        ArrayList<Tag> tagList = newsTO.getTagList();
        try {
            Long newsId = null;
            if(news!=null){
                newsId=newsRepository.create(news);
                if(commentList!=null) {
                    for (Comment com : commentList) {
                        com.setNewsId(newsId);
                        commentService.create(com);
                    }
                }
                if(author!=null) {
                    Long authorId= authorService.create(author);
                    newsRepository.joinNewsWithAuthor(newsId, authorId);
                }
                if(tagList!=null) {
                    for (Tag tag : tagList) {
                        Long tagId = tagService.create(tag);
                        newsRepository.joinNewsWithTag(newsId, tagId);
                    }
                }
            }else {
                logger.debug("News = null while creating news in NewsServiceImpl");
            }
        } catch (DAOException e) {
            logger.error("DAOException while filling news in NewsServiceImpl");
            throw new ServiceException("ServiceException while filling news", e);
        }
    }
    /**
     * Implementation of NewsService method readDataByNewsId.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public NewsTO readDataByNewsId(Long id) throws ServiceException {
        logger.debug("Reading news and all information connected with it in NewsServiceImpl");
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
            logger.error("DAOException while reading news in NewsServiceImpl");
            throw new ServiceException("ServiceException while reading news", e);
        }
        return newsTO;
    }
    /**
     * Implementation of NewsService method update.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(NewsTO newsTO) throws ServiceException {
        logger.debug("Updating news and all information connected with it in NewsServiceImpl");
        try {
            newsTO.getNews().setModificationDate(new Date(Calendar.getInstance().getTime().getTime()));
            newsRepository.update(newsTO.getNews().getId(),newsTO.getNews());
        } catch (DAOException e) {
            logger.error("DAOException while updating news in NewsServiceImpl");
            throw new ServiceException("ServiceException while updating news", e);
        }
    }
    /**
     * Implementation of NewsService method delete.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(NewsTO newsTO) throws ServiceException {
        logger.debug("Deleting news and all information connected with it in NewsServiceImpl");
        News news = newsTO.getNews();
        Author author = newsTO.getAuthor();
        ArrayList<Comment> commentList = newsTO.getCommentList();
        ArrayList<Tag> tagList = newsTO.getTagList();
        try {
            if(author!=null) {
                newsRepository.disjoinNewsWithAuthor(news.getId(), author.getId());
            }
            if(commentList!=null) {
                for (Comment com : commentList) {
                    commentService.delete(com);
                }
            }
            if(tagList!=null) {
                for (Tag tag : tagList) {
                    newsRepository.disjoinNewsWithTag(news.getId(), tag.getId());
                }
            }
            newsRepository.delete(news.getId());
        } catch (DAOException e) {
            logger.error("DAOException while deleting news and connected information in NewsServiceImpl");
            throw new ServiceException("ServiceException while deleting news", e);
        }
    }
    /**
     * Implementation of NewsService method getNewsForEditing.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Override
    public NewsTORecord getNewsForEditing(Long newsId)throws ServiceException{
        NewsTORecord newsTORecord;
        NewsTO newsTO = readDataByNewsId(newsId);
        Author author;
        ArrayList<Tag> tagList;
        newsTORecord= new NewsTORecord();
        author=authorService.readByNewsId(newsId);
        tagList=tagService.readTagsByNewsId(newsId);
        newsTORecord.setNews(newsTO.getNews());
        newsTORecord.setAuthorId(author.getId());
        ArrayList<Long> tagIdList = new ArrayList<>();
        for(Tag tag: tagList){
            tagIdList.add(tag.getId());
        }
        newsTORecord.setTagIdList(tagIdList);
        return newsTORecord;
    }
    /**
     * Implementation of NewsService method updateNews.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Override
    public void updateNews(NewsTORecord newsTORecord) throws ServiceException{
        News news = newsTORecord.getNews();
        NewsTO newsTO=new NewsTO();
        newsTO.setNews(news);
        update(newsTO);
        ArrayList<Long> tagIdList=newsTORecord.getTagIdList();
        ArrayList<Tag> tagListBefore= tagService.readTagsByNewsId(news.getId());
        try {
            if (tagListBefore.isEmpty()) {
                if(tagIdList!=null) {
                    for (Long tagId : tagIdList) {
                        newsRepository.joinNewsWithTag(news.getId(), tagId);
                    }
                }
            }
            else{
                for(Tag tag:tagListBefore){
                    newsRepository.disjoinNewsWithTag(news.getId(),tag.getId());
                }
                if(tagIdList!=null) {
                    for (Long tagId : tagIdList) {
                        newsRepository.joinNewsWithTag(news.getId(), tagId);
                    }
                }
            }
            Long authorId = newsTORecord.getAuthorId();
            if(authorId!=null){
                Author oldAuthor = authorService.readByNewsId(news.getId());
                newsRepository.disjoinNewsWithAuthor(news.getId(),oldAuthor.getId());
                newsRepository.joinNewsWithAuthor(news.getId(),authorId);
            }
        } catch (DAOException e) {
            logger.error("DAOException while updating news record and connected information in NewsServiceImpl");
            throw new ServiceException("ServiceException while updating news record", e);
        }

    }
}
