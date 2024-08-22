package com.example.DZNine.Pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;


/**
 * The type Page exception controller.
 */
@Controller
@ControllerAdvice(basePackageClasses = PageExceptionController.class)
public class PageExceptionController {

    private static final Logger logger =  LoggerFactory.getLogger(PageExceptionController.class);

    @GetMapping("/home/oops")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String oopsPage() {
        return "oops";
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(NoSuchElementException e) {
        logger.error("Resource not found: ", e);
        return "not-found";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        logger.error("An error occurred: ", e);
        return "redirect:/home/oops";
    }
}


