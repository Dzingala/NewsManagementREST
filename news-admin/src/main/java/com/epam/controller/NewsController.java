package com.epam.controller;

import by.epam.lab.task.entity.Author;
import by.epam.lab.task.entity.Comment;
import by.epam.lab.task.entity.News;
import by.epam.lab.task.entity.Tag;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

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
        ArrayList<News> newsList = newsService.readAll();
        ArrayList<NewsTO> newsTOList = new ArrayList<>();
        for (News news : newsList) {
            NewsTO newsTO = newsService.readDataByNewsId(news.getId());
            newsTOList.add(newsTO);
        }
        model.addAttribute("newsList", newsTOList);
        return "news_index";
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    public String viewFullNewsContent(@PathVariable Long id, ModelMap model) throws ServiceException {
        NewsTO newsTO = newsService.readDataByNewsId(id);
        model.addAttribute("comment", new Comment());
        model.addAttribute("newsTO", newsTO);
        return "news_full";
    }

    //    @RequestMapping(value = "/news/edit/{id}",method = RequestMethod.GET)
//    public String editNewsById(@PathVariable Long id,ModelMap model)throws ServiceException{
//        NewsTO newsTO=newsService.readDataByNewsId(id);
//        model.addAttribute("newsTO", newsTO);
//        model.addAttribute("tags",tagService.readAll());
//        model.addAttribute("authors",authorService.readAll());
//        return "news_edit";
//
//    }
//    @RequestMapping(value = "/news/edit/{id}",method = RequestMethod.POST)
//    public String saveUpdates(@ModelAttribute NewsTO newsTO,@PathVariable Long id,BindingResult bindingResult,ModelMap model)throws ServiceException{
//        if(bindingResult.hasErrors()){
//            return editNewsById(id,model);
//        }
//        Date sqlDate = new Date(Calendar.getInstance().getTime().getTime());
//        newsTO.getNews().setModificationDate(sqlDate);
//        newsTO.getNews().setId(id);
//        newsService.update(newsTO);
//        return "redirect:/news/edit/"+id;
//    }
    @RequestMapping(value = "/news/edit/{newsId}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable Long newsId,
                              ModelMap model) throws ServiceException {
        System.out.println("GETTTTTT:");
        NewsTO newsTO = newsService.readDataByNewsId(newsId);
        System.out.println("READ NEWS:"+newsTO);
        model.addAttribute("newsTO", newsTO);
        ArrayList<Author> authorList = authorService.readAll();
        model.addAttribute("authorList", authorList);
        model.addAttribute("tagList", tagService.readAll());
        System.out.println("====== Edit News Get =======");
        return "news_edit";
    }

    @RequestMapping(value = "/news/update", method = RequestMethod.POST)
    public String editNews(
            @ModelAttribute NewsTORecord newsTORecord, @RequestParam Long id) throws ServiceException {
        System.out.println("POSTTTTTT:");
        System.out.println("GOTNEWS:"+newsTORecord);
        Date modificationDate = new Date(Calendar.getInstance().getTime().getTime());
        newsTORecord.getNews().setModificationDate(modificationDate);
        newsTORecord.getNews().setId(id);
        System.out.println("TOUPDATENEWS:"+newsTO);
        newsService.update(newsTORecord.);
        System.out.println("====== Edit News =======");
        return "redirect:/news";
    }
//    @RequestMapping(value="/news/add",method = RequestMethod.GET)
//    public String addNews(ModelMap modelMap){
//
//    }

}
