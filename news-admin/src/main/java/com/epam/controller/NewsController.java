package com.epam.controller;

import by.epam.lab.task.entity.Comment;
import by.epam.lab.task.entity.News;
import by.epam.lab.task.entity.dto.NewsTO;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.service.AuthorService;
import by.epam.lab.task.service.CommentService;
import by.epam.lab.task.service.NewsService;
import by.epam.lab.task.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class NewsController {
    @Autowired
    NewsService newsService;
    @Autowired
    CommentService commentService;
    @Autowired
    AuthorService authorService;
    @Autowired
    TagService tagService;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String printNews(ModelMap model) throws ServiceException {
        ArrayList<News> newsList=newsService.readAll();
        ArrayList<NewsTO> newsTOList=new ArrayList<>();
        for (News news : newsList) {
            NewsTO newsTO = newsService.readDataByNewsId(news.getId());
            newsTOList.add(newsTO);
        }
        model.addAttribute("newsTOList", newsTOList);
        return "news_index";
    }
    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    public String viewFullNewsContent(@PathVariable Long id,ModelMap model) throws ServiceException {
        NewsTO newsTO=newsService.readDataByNewsId(id);
        model.addAttribute("comment", new Comment());
        model.addAttribute("newsTO",newsTO);
        return "news_full";
    }

    //@RequestMapping(value = "/news/edit/{newsId}")
}
