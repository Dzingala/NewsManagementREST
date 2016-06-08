package com.epam.controller;

import by.epam.lab.task.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;


}
