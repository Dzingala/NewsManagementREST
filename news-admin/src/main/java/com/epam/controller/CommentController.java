package com.epam.controller;

import by.epam.lab.task.entity.Comment;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/comment/create", method = RequestMethod.POST)
    public String createComment(@ModelAttribute @Validated Comment comment,
                                BindingResult bindingResult,
                                @RequestParam Long newsId) throws ServiceException {
        if (bindingResult.hasErrors()) {
            return "redirect:/news/" + newsId;
        }
        comment.setNewsId(newsId);
        comment.setCreationDate(new Timestamp(System.currentTimeMillis()));
        commentService.create(comment);
        return "redirect:/news/view/" + newsId;
    }

    @RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
    public String deleteComment(@ModelAttribute Comment comment, @RequestParam Long newsId) throws ServiceException {
        commentService.delete(comment);
        return "redirect:/news/view/" + newsId;
    }
}
