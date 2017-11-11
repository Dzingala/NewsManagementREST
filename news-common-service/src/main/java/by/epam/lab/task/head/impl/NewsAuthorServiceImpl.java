package by.epam.lab.task.head.impl;

import by.epam.lab.task.dao.NewsAuthorDAO;
import by.epam.lab.task.entity.Author;
import by.epam.lab.task.entity.Comment;
import by.epam.lab.task.entity.News;
import by.epam.lab.task.entity.Tag;
import by.epam.lab.task.entity.dto.NewsTO;
import by.epam.lab.task.entity.dto.NewsTORecord;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.head.NewsAuthorService;
import by.epam.lab.task.repository.NewsRepository;
import by.epam.lab.task.service.AuthorService;
import by.epam.lab.task.service.CommentService;
import by.epam.lab.task.service.TagService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Dzinhala
 */
@Service
public class NewsAuthorServiceImpl implements NewsAuthorService{
    private static final Logger logger= Logger.getLogger(NewsAuthorServiceImpl.class);


    @Resource(name = "newsRepositoryImpl")
    private NewsRepository newsRepository;
    @Resource
    private CommentService commentService;
    @Resource
    private TagService tagService;
    @Resource
    private AuthorService authorService;

    @Resource(name = "authorDAO")
    private NewsAuthorDAO newsAuthorDataObject;
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
    private Long create(NewsTO newsTO) throws ServiceException {
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
                    newsAuthorDataObject.joinNewsWithAuthor(newsId, author.getId());
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
}
