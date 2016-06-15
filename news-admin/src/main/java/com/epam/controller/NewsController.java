package com.epam.controller;

import by.epam.lab.task.entity.*;
import by.epam.lab.task.entity.dto.NewsTO;
import by.epam.lab.task.entity.dto.NewsTORecord;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.service.AuthorService;
import by.epam.lab.task.service.CommentService;
import by.epam.lab.task.service.NewsService;
import by.epam.lab.task.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    public String printNews(ModelMap model, @ModelAttribute SearchCriteria searchCriteria) throws ServiceException {
        ArrayList<News> newsList=null;
        ArrayList<NewsTO> newsTOList=new ArrayList<>();

        if(searchCriteria.getAuthor() == null && searchCriteria.getTags() == null) {
            newsList= newsService.readAll();
            model.addAttribute("searchCriteria", new SearchCriteria());
        }
        else{
            newsList = newsService.readBySearchCriteria(searchCriteria);
            model.addAttribute("searchCriteria", searchCriteria);
        }

        for (News news : newsList) {
            NewsTO newsTO = newsService.readDataByNewsId(news.getId());
            newsTOList.add(newsTO);
        }

        ArrayList<Tag> tagList = tagService.readAll();
        ArrayList<Author> authorList = authorService.readAll();

        model.addAttribute("newsList", newsTOList);
        model.addAttribute("tagList",tagList);
        model.addAttribute("authorList",authorList);

        return "news_index";
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    public String viewFullNewsContent(@PathVariable Long id, ModelMap model) throws ServiceException {
        NewsTO newsTO = newsService.readDataByNewsId(id);
        model.addAttribute("comment", new Comment());
        model.addAttribute("newsTO", newsTO);
        return "news_full";
    }


    @RequestMapping(value = "/news/edit/{newsId}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable Long newsId,
                              ModelMap model) throws ServiceException {
        NewsTORecord newsTORecord = newsService.getNewsForEditing(newsId);
        ArrayList<Author> authorList = authorService.readAll();
        ArrayList<Tag> tagList = tagService.readAll();
        model.addAttribute("newsTORecord", newsTORecord);
        model.addAttribute("authorList", authorList);
        model.addAttribute("tagList", tagList);
        return "news_edit";
    }

    @RequestMapping(value = "/news/update", method = RequestMethod.POST)
    public String editNews(
            @ModelAttribute NewsTORecord newsTORecord) throws ServiceException {
        newsService.updateNews(newsTORecord);
        return "redirect:/news";
    }

    @RequestMapping(value = "/news/delete", method = RequestMethod.POST)
    public String deleteNews(@RequestParam ArrayList<Long> newsToDelList)throws ServiceException{
        NewsTO newsTO;
        for(Long newsId : newsToDelList){
            newsTO=newsService.readDataByNewsId(newsId);
            newsService.delete(newsTO);
        }
        return "redirect:/news";
    }

}
