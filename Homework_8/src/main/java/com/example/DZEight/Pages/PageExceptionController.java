package com.example.DZEight.Pages;

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

    /**
     * Oops page string.
     *
     * @return the string
     */
    @GetMapping("/home/oops")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String oopsPage() {
        return "oops.html";
    }

    /**
     * Handle no such element exception string.
     *
     * @param e the e
     * @return the string
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(NoSuchElementException e) {
        return "not-found.html";
    }

    /**
     * Handle exception string.
     *
     * @param e the e
     * @return the string
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return "redirect:/home/oops";
    }
}


