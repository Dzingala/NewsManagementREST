package com.epam.command.impl;

import by.epam.lab.task.entity.Comment;
import by.epam.lab.task.entity.SearchCriteria;
import by.epam.lab.task.entity.dto.NewsTO;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.service.CommentService;
import by.epam.lab.task.service.NewsService;
import com.epam.command.Command;
import com.epam.exception.CommandException;
import com.epam.exception.HandlerException;
import com.epam.utils.impl.RequestHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * @author Ivan Dzinhala
 */
@Component("addComment")
public class CommentNewsCommand implements Command {
    @Autowired
    private CommentService commentService;

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
            Comment comment = new Comment();

            Timestamp creationDate = new Timestamp(System.currentTimeMillis());
            comment.setCreationDate(creationDate);

            Long newsId = requestHandler.parseNewsId(request);
            comment.setNewsId(newsId);

            String comText = requestHandler.parseCommentText(request);
            validateComment(comText);
            comment.setText(comText);
            comment.setNewsId(newsId);
            commentService.create(comment);

            SearchCriteria searchCriteria = requestHandler.parseSearchCriteria(request);

            long page = requestHandler.parsePage(request);

            NewsTO news = newsService.readDataByNewsId(newsId);

            request.setAttribute(NEWS_ID, newsId);
            request.setAttribute(CURRENT_PAGE, page);
            request.setAttribute(SEARCH_CRITERIA, searchCriteria);
            request.setAttribute(NEWS_TO, news);

        } catch (ServiceException| HandlerException e) {
            throw new CommandException(e);
        }
        return PAGE;
    }

    /**
     * Validate comment text
     * @param comText
     * @throws CommandException
     */
    private void validateComment(String comText) throws CommandException {
        if(comText.isEmpty() || comText.length() > 30){
            throw new CommandException("Invalid comment");
        }
    }
}
