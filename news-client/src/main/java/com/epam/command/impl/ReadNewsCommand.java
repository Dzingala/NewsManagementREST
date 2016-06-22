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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Dzinhala
 */
@Component("readNews")
public class ReadNewsCommand implements Command {
    @Autowired
    private NewsService newsService;

    @Autowired
    private TagService tagService;

    @Autowired
    private AuthorService authorService;

    private static final String PAGES = "pages";
    private static final String CURRENT_PAGE = "curPage";
    private static final String SEARCH_CRITERIA = "searchCriteria";
    private static final String TAG_LIST = "tagList";
    private static final String AUTHOR_LIST = "authorList";
    private static final String NEWS = "newsSet";

    private static final String TO_GO="/WEB-INF/jsp/news_index.jsp";

    private static final RequestHandlerImpl requestHandler = RequestHandlerImpl.getInstance();

    /**
     * @see com.epam.command.Command
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            SearchCriteria searchCriteria = requestHandler.parseSearchCriteria(request);

            long numberOfPages = newsService.countCriteriaPages(searchCriteria);

            long page = requestHandler.parsePage(request);

            ArrayList<News> newsList = newsService.readBySearchCriteria(searchCriteria, page);
            ArrayList<NewsTO> news = new ArrayList<>();
            for (News singleNew : newsList) {
                NewsTO newsTO = newsService.readDataByNewsId(singleNew.getId());
                news.add(newsTO);
            }

            request.setAttribute(PAGES, numberOfPages);
            request.setAttribute(CURRENT_PAGE, page);
            request.setAttribute(SEARCH_CRITERIA, searchCriteria);
            request.setAttribute(TAG_LIST, tagService.readAll());
            request.setAttribute(AUTHOR_LIST, authorService.readAll());
            request.setAttribute(NEWS, news);

        } catch (ServiceException | HandlerException e) {
            throw new CommandException(e);
        }

        return TO_GO;
    }
}
