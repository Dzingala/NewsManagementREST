package com.epam.command.impl;

import by.epam.lab.task.entity.SearchCriteria;
import by.epam.lab.task.entity.dto.NewsTO;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.service.NewsService;
import com.epam.command.Command;
import com.epam.exception.CommandException;
import com.epam.exception.HandlerException;
import com.epam.utils.impl.RequestHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ivan Dzinhala
 */
@Component("viewNews")
public class ViewNewsCommand implements Command {
    @Autowired
    private NewsService newsService;

    private static final String SEARCH_CRITERIA = "searchCriteria";
    private static final String NEWS_TO = "newsTO";
    private static final String CURRENT_PAGE = "curPage";
    private static final String NEWS_ID = "newsId";

    private static final String PAGE="/WEB-INF/jsp/news_view.jsp";

    private static final RequestHandlerImpl requestHandler = RequestHandlerImpl.getInstance();

    /**
     * @see com.epam.command.Command
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            SearchCriteria searchCriteria = requestHandler.parseSearchCriteria(request);

            long page = requestHandler.parsePage(request);

            Long newsId = requestHandler.parseNewsId(request);
            NewsTO newsTO  = newsService.readDataByNewsId(newsId);

            request.setAttribute(NEWS_ID, newsId);
            request.setAttribute(CURRENT_PAGE, page);
            request.setAttribute(SEARCH_CRITERIA, searchCriteria);
            request.setAttribute(NEWS_TO, newsTO);

        } catch (ServiceException | HandlerException e) {
            throw new CommandException(e);
        }

        return PAGE;
    }
}
