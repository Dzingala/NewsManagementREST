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
import java.util.List;

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
        logger.debug("Getting news record in NewsServiceImpl");
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
        logger.debug("Updating news and all information connected with it by the news record in NewsServiceImpl");
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
    /**
     * Implementation of NewsService method deleteTag.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional
    @Override
    public void deleteTag(Tag tag)throws ServiceException{
        logger.debug("Deleting tag from news in NewsServiceImpl");
        ArrayList<Long> newsToDisjoinList=null;
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
        Long newsPerPage=4l;
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

    @Transactional
    @Override
    public Long countCriteriaPages(SearchCriteria searchCriteria)throws ServiceException{
        logger.debug("Counting pages of News according the search criteria in NewsServiceImpl");
        Long criteriaNewsAmount;
        String query = composeCriteriaNewsAmountQuery(searchCriteria);
        System.out.println("QUERY BUILT:" +query);
        Long newsPerPage = 4l;
        if (query == null) {
            return null;
        }
        try {
            criteriaNewsAmount = newsRepository.countNews(query);
            if (criteriaNewsAmount % 4 != 0) {
                return criteriaNewsAmount / newsPerPage + 1;
            }
        } catch (DAOException e) {
            logger.error("DAOException while counting pages of News according the search criteria in NewsServiceImpl");
            throw new ServiceException("ServiceException while counting pages of News according the search criteria", e);
        }
        return criteriaNewsAmount / newsPerPage;

    }


    private static String GET_PAGES_AMOUNT_BY_SEARCH_CRITERIA_QUERY_PART_1="SELECT COUNT(DISTINCT NEWS.NEWS_ID)" +
            " FROM" +
            " (SELECT T.*,ROWNUM RN FROM (SELECT DISTINCT NEWS.NEWS_ID, NEWS.TITLE, NEWS.SHORT_TEXT," +
            "NEWS.FULL_TEXT, NEWS.CREATION_DATE," +
            "NEWS.MODIFICATION_DATE,COUNT(COMMENTS.COMMENT_ID)AS " +
            "count FROM NEWS LEFT JOIN NEWS_AUTHOR ON NEWS.NEWS_ID=NEWS_AUTHOR.NEWS_ID " +
            "LEFT JOIN NEWS_TAG ON NEWS.NEWS_ID=NEWS_TAG.NEWS_ID   FULL OUTER JOIN COMMENTS ON COMMENTS.NEWS_ID =NEWS.NEWS_ID ";
    private static String GET_PAGES_AMOUNT_BY_SEARCH_CRITERIA_QUERY_PART_2 = " GROUP BY NEWS.NEWS_ID, NEWS.TITLE, NEWS.SHORT_TEXT, NEWS.FULL_TEXT, NEWS.CREATION_DATE," +
            "NEWS.MODIFICATION_DATE ORDER BY count DESC, NEWS.MODIFICATION_DATE DESC) T)";
    @Override
    public String composeCriteriaNewsAmountQuery(SearchCriteria searchCriteria){
        StringBuffer query = new StringBuffer(GET_PAGES_AMOUNT_BY_SEARCH_CRITERIA_QUERY_PART_1);
        Long authorId = searchCriteria.getAuthorId();
        List<Long> tagIdList = searchCriteria.getTagsId();
        if (authorId == null && tagIdList == null) {
            return null;
        } else {
            query.append("WHERE ");
            if (authorId != null && tagIdList != null) {
                query.append("NEWS_AUTHOR.AUTHOR_ID IN(");
                query.append(authorId + ")");
                query.append("AND ");
                query.append("NEWS_TAG.TAG_ID IN(");
                for (Long tagId : tagIdList) {
                    query.append(tagId);
                    query.append(",");
                }
                query.deleteCharAt(query.length() - 1);
                query.append(")");
            } else {
                if (authorId != null) {
                    query.append("NEWS_AUTHOR.AUTHOR_ID IN(");
                    query.append(authorId);
                    query.append(")");
                }
                if (tagIdList != null) {
                    query.append("NEWS_TAG.TAG_ID IN(");
                    for (Long tagId : tagIdList) {
                        query.append(tagId);
                        query.append(",");
                    }
                    query.deleteCharAt(query.length() - 1);
                    query.append(")");
                }
            }
        }
        query.append(GET_PAGES_AMOUNT_BY_SEARCH_CRITERIA_QUERY_PART_2);
        return query.toString();
    }
}
