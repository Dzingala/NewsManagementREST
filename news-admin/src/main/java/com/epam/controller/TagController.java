package com.epam.controller;


import by.epam.lab.task.entity.Tag;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.service.NewsService;
import by.epam.lab.task.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class TagController {
    @Autowired
    TagService tagService;

    @Autowired
    NewsService newsService;

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String printNews(ModelMap model) throws ServiceException {
        ArrayList<Tag> tagList = tagService.readAll();
        model.addAttribute("tagList", tagList);
        model.addAttribute("tag", new Tag());
        return "tag_index";
    }


    @RequestMapping(value = "/tag/delete", method = RequestMethod.POST)
    public String deleteTag(@ModelAttribute Tag tag) throws ServiceException {
        newsService.deleteTag(tag);
        return "redirect:/tags";
    }

    @RequestMapping(value = "/tag/create", method = RequestMethod.POST)
    public String createTag(@ModelAttribute Tag tag) throws ServiceException {
        tagService.create(tag);
        return "redirect:/tags";
    }

    @RequestMapping(value = "/tag/update", method = RequestMethod.POST)
    public String updateTag(@ModelAttribute Tag tag) throws ServiceException {
        tagService.update(tag);
        return "redirect:/tags";
    }



}
