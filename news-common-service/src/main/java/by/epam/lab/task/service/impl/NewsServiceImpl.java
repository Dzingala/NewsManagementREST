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
import java.util.List;
import java.util.Calendar;
import java.util.List;

/**
 * @author Ivan Dzinhala
 * @see NewsService
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService {

    private static final Logger logger= Logger.getLogger(NewsServiceImpl.class);
    private static final String GET_PAGES_AMOUNT_BY_SEARCH_CRITERIA_QUERY_PART_1="SELECT COUNT(SELECT DISTINCT NEWS.NEWS_ID,NEWS.TITLE,NEWS.SHORT_TEXT," +
            "NEWS.FULL_TEXT,NEWS.CREATION_DATE,NEWS.MODIFICATION_DATE" +
            " FROM DZINHALA.NEWS LEFT JOIN DZINHALA.COMMENTS ON NEWS.NEWS_ID=COMMENTS.NEWS_ID" +
            " LEFT JOIN DZINHALA.NEWS_AUTHOR ON NEWS.NEWS_ID=NEWS_AUTHOR.NEWS_ID" +
            " LEFT JOIN DZINHALA.NEWS_TAG ON NEWS.NEWS_ID=NEWS_TAG.NEWS_ID ";

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    private static final int NEWS_PER_PAGE=4;

    /**
     * Implementation of NewsService method readAll.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<News> readAll() throws ServiceException {
        logger.debug("Reading all authors in AuthorService");
        List<News> news = null;
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
    public List<News> readSortedByComments() throws ServiceException {
        logger.debug("Reading news sorted by comments in NewsServiceImpl");
        List<News> newsList = null;
        try {
            newsList = newsRepository.readAll();
        } catch (DAOException e) {
            logger.error("DAOException while reading news sorted by comments in NewsServiceImpl");
            throw new ServiceException("ServiceException while reading news sorted by comments",e);
        }
        return newsList;
    }
    /**
     * Implementation of NewsService method readBySearchCriteria.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<News> readBySearchCriteria(SearchCriteria searchCriteria, Long page) throws ServiceException {
        logger.debug("Reading news sorted by search criteria in NewsServiceImpl");
        List<News> newsList = null;
        String query = NewsRepositoryImpl.composeSearchCriteriaQuery(searchCriteria);
        try{
            if(query==null){
                logger.debug("Null query has come");
                newsList=newsRepository.readAll();
                newsList=NewsRepositoryImpl.getPageNews(newsList,page,NEWS_PER_PAGE);
            }
            else {
                logger.debug("Not null query has come:\n"+query);
                newsList = newsRepository.readBySearchCriteria(query, page, NEWS_PER_PAGE);
            }
        } catch (DAOException e) {
            logger.error("DAOException while reading news sorted by search criteria in NewsServiceImpl");
            throw new ServiceException("ServiceException while reading news sorted by search criteria",e);
        }
        return newsList;
    }

    /**
     * Implementation of NewsService method getCriteriaPagesAmount.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long getCriteriaPagesAmount(SearchCriteria searchCriteria, Long page)throws ServiceException{
        logger.debug("Counting criteria pages in NewsServiceImpl");
        Long pagesAmount =null;
        List<News> news = null;
        String query=NewsRepositoryImpl.composeSearchCriteriaQuery(searchCriteria);
        try{
            news = newsRepository.readBySearchCriteriaFull(query);
            Long size =(long)news.size();
            pagesAmount=size%NEWS_PER_PAGE==0?size/NEWS_PER_PAGE:size/NEWS_PER_PAGE+1;
        } catch (DAOException e) {
            logger.error("DAOException while counting news sorted by search criteria in NewsServiceImpl");
            throw new ServiceException("ServiceException while reading news sorted by search criteria",e);
        }

        return pagesAmount;
    }
    /**
     * Implementation of NewsService method createNews.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public Long createNews(NewsTORecord newsTORecord) throws ServiceException {
        logger.debug("Extraction news and all information connected with it from the news record in NewsServiceImpl");
        News news = newsTORecord.getNews();
        Long authorId=newsTORecord.getAuthorId();
        List<Long> tagsId=newsTORecord.getTagIdList();
        NewsTO newsTO = new NewsTO();
        newsTO.setNews(news);
        Author author;
        if(authorId!=null) {
            author=authorService.read(authorId);
        }
        else{
            return null;
        }
        newsTO.setAuthor(author);
        List<Tag> tags=null;
        if(tagsId!=null){
            tags=new ArrayList<>();
            for(Long id: tagsId){
                tags.add(tagService.readById(id));
            }
        }
        newsTO.setTagList(tags);
        return create(newsTO);

    }

    /**
     * Implementation of NewsService method create.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long create(NewsTO newsTO) throws ServiceException {
        logger.debug("Creating news and all information connected with it in NewsServiceImpl");
        //validation
        News news = newsTO.getNews();
        Author author = newsTO.getAuthor();
        List<Comment> commentList = newsTO.getCommentList();
        List<Tag> tagList = newsTO.getTagList();
        Long newsId = null;
        try {
            if(news!=null){
                newsId=newsRepository.create(news);
                if(commentList!=null) {
                    for (Comment com : commentList) {
                        com.setNewsId(newsId);
                        commentService.create(com);
                    }
                }
                if(author!=null) {
                    newsRepository.joinNewsWithAuthor(newsId, author.getId());
                }
                if(tagList!=null) {
                    for (Tag tag : tagList) {
                        newsRepository.joinNewsWithTag(newsId, tag.getId());
                    }
                }
            }else {
                logger.debug("News = null while creating news in NewsServiceImpl");
            }
        } catch (DAOException e) {
            logger.error("DAOException while filling news in NewsServiceImpl");
            throw new ServiceException("ServiceException while filling news", e);
        }
        return newsId;
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
            List<Tag> tagList = tagService.readTagsByNewsId(id);
            List<Comment> commentList = commentService.readAllByNewsId(id);
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
        List<Comment> commentList = newsTO.getCommentList();
        List<Tag> tagList = newsTO.getTagList();
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
        logger.debug("Getting news record in NewsServiceImpl");
        NewsTORecord newsTORecord;
        NewsTO newsTO = readDataByNewsId(newsId);
        Author author;
        List<Tag> tagList;
        newsTORecord= new NewsTORecord();
        author=authorService.readByNewsId(newsId);
        tagList=tagService.readTagsByNewsId(newsId);
        newsTORecord.setNews(newsTO.getNews());
        newsTORecord.setAuthorId(author.getId());
        List<Long> tagIdList = new ArrayList<>();
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
        logger.debug("Updating news and all information connected with it by the news record in NewsServiceImpl");
        News news = newsTORecord.getNews();
        NewsTO newsTO=new NewsTO();
        newsTO.setNews(news);
        update(newsTO);
        List<Long> tagIdList=newsTORecord.getTagIdList();
        List<Tag> tagListBefore= tagService.readTagsByNewsId(news.getId());
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
    /**
     * Implementation of NewsService method deleteTag.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional
    @Override
    public void deleteTag(Tag tag)throws ServiceException{
        logger.debug("Deleting tag from news in NewsServiceImpl");
        List<Long> newsToDisjoinList=null;
        try {
            newsToDisjoinList = tagService.readNewsIdByTagId(tag.getId());
            for (Long newsId : newsToDisjoinList) {
                newsRepository.disjoinNewsWithTag(newsId, tag.getId());
            }
        }catch (DAOException e){
            logger.error("DAOException while deleting tag from news in NewsServiceImpl");
            throw new ServiceException("ServiceException while deleting tag from news", e);
        }
        tagService.delete(tag);
    }
    /**
     * Implementation of NewsService method countPages.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional
    @Override
    public Long countPages()throws ServiceException{
        Long newsAmount;
        Long newsPerPage= 4L;
        try{
            newsAmount=newsRepository.countNews();
            if(newsAmount % newsPerPage !=0){
                return newsAmount/newsPerPage +1;
            }
        } catch (DAOException e) {
            logger.error("DAOException while counting pages in NewsServiceImpl");
            throw new ServiceException("ServiceException while counting pages", e);
        }
        return newsAmount/newsPerPage;
    }

    @Override
    public String composeCriteriaNewsAmountQuery(SearchCriteria searchCriteria){
        StringBuilder sb= new StringBuilder(GET_PAGES_AMOUNT_BY_SEARCH_CRITERIA_QUERY_PART_1);
        Long authorId =searchCriteria.getAuthorId();
        List<Long> tagsId = searchCriteria.getTagsId();
        if(authorId == null && tagsId == null){
            return null;
        }
        sb.append("WHERE ");
        if(authorId!=null){
            sb.append("NEWS_AUTHOR.AUTHOR_ID=").append(authorId);
            if(tagsId!=null){
                sb.append(" AND ").append("NEWS_TAG.TAG_ID IN(");
                for (Long tagId : tagsId) {
                    sb.append(tagId);
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(")");
            }
        }
        else{
            sb.append("NEWS_TAG.TAG_ID IN(");
            for (Long tagId : tagsId) {
                sb.append(tagId);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
        }
        sb.append(')');
        logger.debug("Criteria query was made:\n"+sb);
        return sb.toString();
    }
}