package co.develhope.interceptormiddleware02.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping("")
    private String greetUser(){
        return "Welcome to the Interceptor Middleware exercise 02.";
    }
}
