package com.example.hycare.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 화면 전환 Controller
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
        return "myPage/doctorMyInfo.html";
    }

    @RequestMapping("/login")
    public String loginWeb(Model model) {
        return "login/login.html";
    }

    @GetMapping("/api/auth/login/complete/{email}")
    public String loginGoogleGet(@PathVariable String email, @RequestParam String loginDiv) {
        if(loginDiv == "0" || loginDiv.equals("0")) {
            return "home/doctorHome.html";
        }
        return "home/patientHome.html";
    }
}
