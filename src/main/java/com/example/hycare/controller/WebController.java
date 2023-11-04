package com.example.hycare.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @RequestMapping("/")
    public String mainWeb(Model model) {
        return "home/home.html";
    }

    @RequestMapping("/consult")
    public String consultWeb(Model model) {
        return "consult/consultStart.html";
    }

    @RequestMapping("/my-page")
    public String myPageWeb(Model model) {
        return "myPage/myPage.html";
    }
}
