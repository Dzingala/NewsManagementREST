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
import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value = {"/news", "/news/{page}"}, method = RequestMethod.GET)
    public String getNews(ModelMap model,
                          @PathVariable Optional<Long> page,
                          @ModelAttribute SearchCriteria searchCriteria
                          ) throws ServiceException {

        List<News> newsList = newsService.readBySearchCriteria(searchCriteria, page.orElse(1l));
        List<NewsTO> newsTOList=new ArrayList<>();
        for (News news : newsList) {
            NewsTO newsTO = newsService.readDataByNewsId(news.getId());
            newsTOList.add(newsTO);
        }
        long pagesAmount=0;
        if(searchCriteria.getAuthorId()==null && searchCriteria.getTagsId()==null){
            pagesAmount= newsService.countPages();
            model.addAttribute("pagesAmount",pagesAmount);
        }else{
            pagesAmount = newsService.getCriteriaPagesAmount(searchCriteria ,1l);
            model.addAttribute("pagesAmountCriteria",pagesAmount);
        }
        model.addAttribute("currentPage", page.orElse(1l));
        model.addAttribute("searchCriteria", searchCriteria);
        model.addAttribute("tagList", tagService.readAll());
        model.addAttribute("authorList", authorService.readAll());
        model.addAttribute("newsList", newsTOList);
        return "news_index";
    }
    @RequestMapping(value = "/news/filter", method = RequestMethod.GET)
    public String filterNews(ModelMap model, @RequestParam Long page, @ModelAttribute SearchCriteria searchCriteria) throws ServiceException {
        List<News> newsList = newsService.readBySearchCriteria(searchCriteria,page);
        List<NewsTO> newsTOList=new ArrayList<>();
        for (News news : newsList) {
            NewsTO newsTO = newsService.readDataByNewsId(news.getId());
            newsTOList.add(newsTO);
        }
        model.addAttribute("newsList", newsTOList);
        long pagesAmount = newsService.getCriteriaPagesAmount(searchCriteria,page);
        model.addAttribute("pagesAmountCriteria", pagesAmount);
        model.addAttribute("tagList", tagService.readAll());
        model.addAttribute("authorList", authorService.readAll());
        return "news_index";
    }

    @RequestMapping(value = "/news/view/{id}", method = RequestMethod.GET)
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
    public String deleteNews(@RequestParam ArrayList<Long> newsToDelList) throws ServiceException {
        NewsTO newsTO;
        if (!newsToDelList.isEmpty()) {
            for (Long newsId : newsToDelList) {
                newsTO = newsService.readDataByNewsId(newsId);
                newsService.delete(newsTO);
            }
        }
        return "redirect:/news";
    }


}
