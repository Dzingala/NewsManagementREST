package com.epam.utils;


import by.epam.lab.task.entity.SearchCriteria;
import com.epam.exception.HandlerException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author Ivan Dzinhala
 */
public interface RequestHandler {
    /**
     * Create a serach criteria from request
     * @param request
     * @return search criteria entity
     * @throws HandlerException
     */
    public SearchCriteria parseSearchCriteria(HttpServletRequest request) throws HandlerException;

    /**
     * Parse a page number from request
     * @param request
     * @return page number
     * @throws HandlerException
     */
    public Long parsePage(HttpServletRequest request) throws HandlerException;

    /**
     * Parse news id from request
     * @param request
     * @return news id
     * @throws HandlerException
     */
    public Long parseNewsId(HttpServletRequest request) throws HandlerException;

    /**
     * Parse comment text from request
     * @param request
     * @return comment text
     * @throws HandlerException
     */
    public String parseCommentText(HttpServletRequest request) throws HandlerException;

    /**
     * Create a set of id from request
     * Use key to identify the nature of id
     * @param request
     * @param key - authId, tagId
     * @return set of id
     * @throws HandlerException
     */
    public ArrayList<Long> parseIdList(HttpServletRequest request, String key) throws HandlerException;

    /**
     * Get an id of the author reflected in string.
     * @param entity
     * @return
     * @throws HandlerException
     */
    Long parseAuthorEntity(String entity)throws HandlerException;
}
