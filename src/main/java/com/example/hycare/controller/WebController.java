package com.example.hycare.controller;

import ch.qos.logback.core.model.Model;
import com.example.hycare.entity.ResultEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

// 화면 전환 Controller
@Slf4j
@Controller
public class WebController {
    @Value("${server.host.api}")
    private String baseUrl;

    @RequestMapping("/")
    public String mainWeb(Model model, HttpServletRequest request) {
        // 세션 유무/ loginDiv 값에 따라 적절한 화면 return
        HttpSession session = request.getSession();
        if (session.getAttribute("email") != null && session.getAttribute("loginDiv").equals("0")) {
            return "home/doctorHome.html";
        } else if (session.getAttribute("email") != null && session.getAttribute("loginDiv").equals("1")) {
            return "home/patientHome.html";
        }
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

    @RequestMapping("/api/auth/login/complete")
    public String loginGoogleGet(@RequestParam String email, @RequestParam String loginDiv, org.springframework.ui.Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute("loginDiv", loginDiv);
        session.setAttribute("email", email);

        if(loginDiv == "0" || loginDiv.equals("0")) {
            return "/home/doctorHome.html";
        }
        return "/home/patientHome.html";
    }

    @RequestMapping("/session-remove")
    public String sessionRemove(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        log.info("session remove success");
        return "home/home.html";
    }

    @GetMapping("/getSessionValues")
    public ResponseEntity<Map<String, Object>> getSessionValues(HttpSession session) {
        Map<String, Object> sessionValues = new HashMap<>();
        sessionValues.put("loginDiv", session.getAttribute("loginDiv"));
        sessionValues.put("email", session.getAttribute("email"));
        return ResponseEntity.ok(sessionValues);
    }
}
