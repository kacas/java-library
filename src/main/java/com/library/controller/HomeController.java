package com.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Katarina on 2/11/2017.
 */
@Controller
public class HomeController {

    @RequestMapping(path = { "", "/", "home" }, method = RequestMethod.GET)
    public String getHomePage() {
        return "home";
    }
}