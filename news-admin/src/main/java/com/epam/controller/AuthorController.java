package com.epam.controller;

import by.epam.lab.task.entity.Author;
import by.epam.lab.task.entity.User;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.service.AuthorService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public String printAuthor(ModelMap model) throws ServiceException {
        List<Author> authorList = authorService.readAll();
        model.addAttribute("authorList", authorList);
        model.addAttribute("author", new Author());
        return "authors_index";
    }


    @RequestMapping(value = "/authors/create", method = RequestMethod.POST)
    public String createAuthor(@ModelAttribute Author author) throws ServiceException {
        authorService.create(author);
        return "redirect:/authors";
    }


    @RequestMapping(value = "/authors/edit",method = RequestMethod.POST)
    public String updateAuthor(@ModelAttribute Author author) throws ServiceException {
        authorService.update(author);
        return "redirect:/authors";
    }


    @RequestMapping(value = "/authors/delete", method = RequestMethod.POST)
    public String deleteAuthor(@ModelAttribute Author author) throws ServiceException {
        authorService.delete(author);
        return "redirect:/authors";
    }

}
