package com.epam.command.impl;

import by.epam.lab.task.entity.News;
import by.epam.lab.task.entity.SearchCriteria;
import by.epam.lab.task.entity.dto.NewsTO;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.service.AuthorService;
import by.epam.lab.task.service.NewsService;
import by.epam.lab.task.service.TagService;
import com.epam.command.Command;
import com.epam.exception.CommandException;
import com.epam.exception.HandlerException;
import com.epam.utils.impl.RequestHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Ivan Dzinhala
 */
@Component("get-news")
public class ReadNewsCommand implements Command {
    @Autowired
    private NewsService newsService;

    @Autowired
    private TagService tagService;

    @Autowired
    private AuthorService authorService;

    private static final String PAGES = "pagesAmount";
    private static final String PAGES_CRITERIA = "pagesAmountCriteria";
    private static final String CURRENT_PAGE = "curPage";
    private static final String SEARCH_CRITERIA = "searchCriteria";
    private static final String TAG_LIST = "tagList";
    private static final String AUTHOR_LIST = "authorList";
    private static final String NEWS = "newsList";

    private static final String PAGE="/WEB-INF/jsp/news_index.jsp";

    private static final RequestHandlerImpl requestHandler = RequestHandlerImpl.getInstance();

    /**
     * @see com.epam.command.Command
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            SearchCriteria searchCriteria = requestHandler.parseSearchCriteria(request);
            Long page =(searchCriteria.getAuthorId()==null && searchCriteria.getTagsId()==null)?
                    (requestHandler.parsePage(request)):
                    (1L);

            List<News> newsList = newsService.readBySearchCriteria(searchCriteria, page);
            ArrayList<NewsTO> news = new ArrayList<>();
            for (News singleNew : newsList) {
                NewsTO newsTO = newsService.readDataByNewsId(singleNew.getId());
                news.add(newsTO);
            }
            Long numberOfPages =null;

            if(searchCriteria.getAuthorId()==null && searchCriteria.getTagsId()==null){
                numberOfPages=newsService.countPages();
                request.setAttribute(PAGES,numberOfPages);
            }
            else{
                numberOfPages=newsService.getCriteriaPagesAmount(searchCriteria,1L);
                System.out.println("criteria pages:"+numberOfPages);
                request.setAttribute(PAGES_CRITERIA,numberOfPages);
            }
            request.setAttribute(CURRENT_PAGE, page);
            request.setAttribute(SEARCH_CRITERIA, searchCriteria);
            request.setAttribute(TAG_LIST, tagService.readAll());
            request.setAttribute(AUTHOR_LIST, authorService.readAll());
            request.setAttribute(NEWS, news);

        } catch (ServiceException | HandlerException e) {
            throw new CommandException(e);
        }

        return PAGE;
    }
}
