package com.epam.controller;

import by.epam.lab.task.entity.Author;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
@RequestMapping(value="/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String printAuthor(ModelMap model) throws ServiceException {
        ArrayList<Author> authorList = authorService.readAll();
        model.addAttribute("authorList", authorList);
        return "authorList";
    }
    /*@RequestMapping(value = "/create",method = RequestMethod.POST)
    public String createAuthor(@ModelAttribute Author author) throws ServiceException {
        authorService.create(author);
        return "redirect:/author/list";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteTag(@ModelAttribute Author author) throws ServiceException {
        authorService.delete(author);
        return "redirect:/author/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateAuthor(@ModelAttribute Author author) throws ServiceException {
        System.out.println(author.toString());
        authorService.update(author);
        return "redirect:/author/list";
    }*/
}
