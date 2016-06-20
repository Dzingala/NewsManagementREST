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

    @RequestMapping(value = {"/news","news/{page}"}, method = RequestMethod.GET)
    public String printNews(ModelMap model,
                            @ModelAttribute SearchCriteria searchCriteria,
                            @PathVariable Optional<Long> page) throws ServiceException {
        ArrayList<News> newsList=null;
        Long pagesAmount = null;
        if(searchCriteria.getAuthorId() == null && searchCriteria.getTagsId() == null) {
            pagesAmount=newsService.countPages();
            System.out.println("PAGES AMOUNT:"+pagesAmount);
            newsList= newsService.readAll();
            model.addAttribute("searchCriteria", new SearchCriteria());
        }
        else{
            pagesAmount=newsService.countCriteriaPages(searchCriteria);
            newsList = newsService.readBySearchCriteria(searchCriteria);
            model.addAttribute("searchCriteria", searchCriteria);
        }



        ArrayList<NewsTO> newsTOList=new ArrayList<>();
        for (News news : newsList) {
            NewsTO newsTO = newsService.readDataByNewsId(news.getId());
            newsTOList.add(newsTO);
        }

        ArrayList<Tag> tagList = tagService.readAll();
        ArrayList<Author> authorList = authorService.readAll();

        model.addAttribute("currentPage",page.orElse(1l));
        model.addAttribute("pagesAmount",pagesAmount);
        model.addAttribute("newsList", newsTOList);
        model.addAttribute("tagList",tagList);
        model.addAttribute("authorList",authorList);

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
    public String deleteNews(@RequestParam ArrayList<Long> newsToDelList)throws ServiceException{
        NewsTO newsTO;
        if(!newsToDelList.isEmpty()) {
            for (Long newsId : newsToDelList) {
                newsTO = newsService.readDataByNewsId(newsId);
                newsService.delete(newsTO);
            }
        }
        return "redirect:/news";
    }
//    @RequestMapping(value = "/news/filter", method = RequestMethod.GET)
//    public String filterNews(ModelMap model, @RequestParam Long page, @ModelAttribute SearchCriteria searchCriteria) throws ServiceException {
//        List<NewsTO> newsTOList = newsHeaderService.findBySearchCriteria(searchCriteria, page);
//        model.addAttribute(NEWS_TO_LIST, newsTOList);
//        Integer numberOfNews = newsService.countNewsBySearchCriteria(searchCriteria);
//        model.addAttribute(NUMBER_OF_PAGES_FILTER, numberOfNews);
//        List<Tag> tagList = tagService.readAll();
//        List<Author> authorList = authorService.readAll();
//        model.addAttribute(TAG_LIST, tagList);
//        model.addAttribute(AUTHOR_LIST, authorList);
//        return "newsList";
//    }

}
