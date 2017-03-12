package com.library.controller;

import javassist.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Katarina on 3/5/2017.
 */

@ControllerAdvice
@EnableWebMvc
public class ExceptionHandlingController {

    private final String error_message_404 = "Page not found.";
    private final String default_error_message = "Something went wrong. Please try again later.";

    @ExceptionHandler(value = NotFoundException.class)
    public ModelAndView handleException(HttpServletRequest req, NotFoundException ex) {
        //logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", error_message_404);
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(HttpServletRequest req, Exception ex) {
        //logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", default_error_message);
        mav.setViewName("error");
        return mav;
    }
}
