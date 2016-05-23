package com.epam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String init() {
        return "index";
    }

    @RequestMapping(value = "/page1", method = RequestMethod.GET)
    public String goToPage1(ModelMap model) {
        model.addAttribute("say","WORKS!!page1");
        return "page1";
    }

    @RequestMapping(value = "/page2", method = RequestMethod.GET)
    public String goToPage2(ModelMap model) {
        model.addAttribute("message","WORKS!!page2");
        return "page2";
    }
    @RequestMapping(value = "/helloWorld",method = RequestMethod.GET)
    public String printHello(ModelMap model){
        model.addAttribute("message","Hello, it works finally!");
        System.out.println("TTTTT");
        return "helloTest";
    }

}