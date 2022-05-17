package com.rentalkarsspringmvc.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login/form")
public class LoginController {


    @GetMapping
    public String getLogin(Model model) {
        return "login";
    }

    @PostMapping
    public String getLoginPost(HttpServletRequest request, HttpServletResponse response) {

        String[] test = request.getParameterValues("logout");

        if (test != null) {
            Cookie cookieWithSlash = new Cookie("JSESSIONID", null);
            //Tomcat adds extra slash at the end of context path (e.g. "/foo/")
            cookieWithSlash.setPath(request.getContextPath() + "/");
            cookieWithSlash.setMaxAge(0);

            Cookie cookieWithoutSlash = new Cookie("JSESSIONID", null);
            //JBoss doesn't add extra slash at the end of context path (e.g. "/foo")
            cookieWithoutSlash.setPath(request.getContextPath());
            cookieWithoutSlash.setMaxAge(0);

            //Remove cookies on logout so that invalidSessionURL (session timeout) is not displayed on proper logout event
            response.addCookie(cookieWithSlash); //For cookie added by Tomcat
            response.addCookie(cookieWithoutSlash); //For cookie added by JBoss

        }

        return "redirect:/login/form?logout";
    }
}
