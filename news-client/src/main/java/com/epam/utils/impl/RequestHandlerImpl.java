package com.epam.utils.impl;


import by.epam.lab.task.entity.SearchCriteria;
import com.epam.exception.HandlerException;
import com.epam.utils.RequestHandler;
import jdk.nashorn.internal.runtime.ParserException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ivan Dzinhala
 */
public class RequestHandlerImpl implements RequestHandler {

    private static final String NEWS_ID = "newsId";
    private static final String CURRENT_PAGE = "curPage";
    private static final String TAGS = "tags";
    private static final String AUTHOR = "author";
    private static final String COMMENT_TEXT = "comText";
    private static final String TAG_ID = "tagId";
    private static final String AUTHOR_ID = "authId";

    private static final String AUTHOR_ID_REGEX_START = ".*(";
    private static final String AUTHOR_ID_REGEX_END = ")=(\\d*).*";
    private static final String AND_REGEX = "&";
    private static final String EQUALS_REGEX = "=";
    private static final RequestHandlerImpl instance = new RequestHandlerImpl();

    public static RequestHandlerImpl getInstance(){
        return instance;
    }
    @Override
    public SearchCriteria parseSearchCriteria(HttpServletRequest request) throws HandlerException {
        SearchCriteria searchCriteria = new SearchCriteria();
        String[] tags = request.getParameterValues(TAGS);
        System.out.println("tagsgot:");
        if(tags!=null){
            System.out.println("all:");
            for (String tag : tags) System.out.println("sep"+tag+"sep");
        }
        String author = request.getParameter(AUTHOR);
        System.out.println("authorgot:"+author);
        ArrayList<Long> tagIdList = null;
        if(tags!=null && !(tags.length == 0)){
            System.out.println("parsing tags");
            tagIdList=parseTags(tags);
        }
        Long authorId = null;
        boolean isAuth=false;
        boolean areTags=false;
        if(author!=null && !author.equals("")){
            System.out.println("parsing author");
            authorId= Long.parseLong(author);
            System.out.println("authorid:"+authorId);
            isAuth=true;
        }
        if(tagIdList!=null){
            System.out.println("tagsidlist not null");
            areTags=true;
            for (Long id :
                    tagIdList) {
                System.out.println(id);
            }
        }
        if(isAuth || areTags){
            System.out.println("auth or tags !=null");
            if(isAuth){
                System.out.println("auth set");
                searchCriteria.setAuthorId(authorId);
            }
            if(areTags){
                System.out.println("tags set");
                searchCriteria.setTagsId(tagIdList);
            }
        }
        return searchCriteria;
    }

    @Override
    public Long parsePage(HttpServletRequest request) throws HandlerException {
        String page = request.getParameter(CURRENT_PAGE);
        System.out.println("pagegot:"+page);
        if(page == null){
            return 1L;
        }
        Long pageLong = null;
        try{
            pageLong = Long.valueOf(page);
        } catch (NumberFormatException e){
            throw new ParserException("String : " + page + " can not be parsed as an integer");
        }
        return pageLong;
    }

    @Override
    public Long parseNewsId(HttpServletRequest request) throws HandlerException {
        String newsId = request.getParameter(NEWS_ID);
        if(newsId == null){
            return 1L;
        }
        Long newsIdLong = null;
        try{
            newsIdLong = Long.valueOf(newsId);
        } catch (NumberFormatException e){
            throw new ParserException("String : " + newsId + " can not be parsed as an integer");
        }
        return newsIdLong;
    }

    @Override
    public String parseCommentText(HttpServletRequest request) throws HandlerException {
        String comText = request.getParameter(COMMENT_TEXT);
        if(comText == null){
            return "";
        }
        return comText;
    }

    @Override
    public ArrayList<Long> parseIdList(HttpServletRequest request, String key) throws HandlerException {
        Pattern pattern = Pattern.compile(AUTHOR_ID_REGEX_START + key + AUTHOR_ID_REGEX_END);
        StringBuffer sb = new StringBuffer(request.getQueryString());
        Matcher matcher = pattern.matcher(sb);
        ArrayList<Long> idSet =  new ArrayList<>();
        if (matcher.matches()) {
            String str1 = matcher.group(1);
            String str2 = matcher.group(2);
            int indexEnd = sb.indexOf(str2) + str2.length();
            int indexStart = sb.indexOf(str1);
            if(indexStart > indexEnd){
                int tmp = indexStart;
                indexStart = indexEnd;
                indexEnd = tmp;
            }
            String s2= sb.substring(indexStart, indexEnd);
            String[] s3 = s2.split(AND_REGEX);
            for(String str : s3){
                String[] s4 = str.split(EQUALS_REGEX);
                idSet.add(Long.valueOf(s4[1]));
            }
        }
        return idSet;
    }
    /**
     * Create a list of id from string like "20 99 43"
     * @param str
     * @return list of id or empty List
     */
    private ArrayList<Long> parseTags(String[] str) {
        ArrayList<Long> idList = null;
        if(str==null)return null;
        idList = new ArrayList<>();
        for (String aStr : str) {
            if (aStr.contains("[")) {
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(aStr);
                int start = 0;
                while (matcher.find(start)) {
                    String value = aStr.substring(matcher.start(), matcher.end());
                    Long result = Long.parseLong(value);
                    System.out.println(result);
                    start = matcher.end();
                    idList.add(result);
                    System.out.println("added in while:" + result);
                }
            } else {
                if (!aStr.equals("")) {
                    idList.add(Long.parseLong(aStr));
                    System.out.println("added" + aStr);
                }
            }
        }

        if(idList.isEmpty()){
            return null;
        }
        return idList;
    }
}
