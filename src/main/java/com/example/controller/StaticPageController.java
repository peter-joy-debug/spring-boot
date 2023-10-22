package com.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class StaticPageController {
    @GetMapping("/GreetingPage")
    public String renderStaticPage() {
        return "GreetingPage"; // This matches the base name of the HTML file
    }
}
