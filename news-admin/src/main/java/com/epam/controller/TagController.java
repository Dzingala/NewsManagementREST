package com.epam.controller;


import by.epam.lab.task.entity.Tag;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.service.NewsService;
import by.epam.lab.task.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("tag")
public class TagController {
    @Autowired
    TagService tagService;

    @Autowired
    NewsService newsService;

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String printNews(ModelMap model) throws ServiceException {
        List<Tag> tagList = tagService.readAll();
        model.addAttribute("tagList", tagList);
        model.addAttribute("tag", new Tag());
        return "tag_index";
    }


    @RequestMapping(value = "/tag/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute Tag tag) throws ServiceException {
        deleteTag(tag);
        return "redirect:/tags";
    }
    @RequestMapping(value = "/tag/delete", method = RequestMethod.DELETE)
    public void deleteTag(@RequestBody Tag tag) throws ServiceException {
        newsService.deleteTag(tag);
    }
    @RequestMapping(value = "/tag/create", method = RequestMethod.POST)
    public String createTag(@ModelAttribute Tag tag) throws ServiceException {
        tagService.create(tag);
        return "redirect:/tags";
    }
    @RequestMapping(value = "/tag/update",method = RequestMethod.POST)
    public String update(@ModelAttribute Tag tag)throws ServiceException{
        updateTag(tag);
        return "redirect:/tags";
    }
    @RequestMapping(value = "/tag/update",method = RequestMethod.PUT)
    public  void updateTag(@RequestBody Tag tag)throws ServiceException{
        tagService.update(tag);
    }
}
