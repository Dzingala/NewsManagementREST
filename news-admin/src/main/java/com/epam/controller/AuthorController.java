package com.epam.controller;

import by.epam.lab.task.entity.Author;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
@RequestMapping(value="/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "/authors",method= RequestMethod.GET)
    public String readAll(ModelMap modelMap) throws ServiceException, DAOException {
        ArrayList<Author> authors=authorService.readAll();
        modelMap.addAttribute("authors",authors);
        //modelMap.addAttribute("author",new Author());
        return "authors";
    }
}
