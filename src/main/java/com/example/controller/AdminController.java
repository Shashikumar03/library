package com.example.controller;

import com.example.helper.Message;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/Admin-register")
    public String adminPage(){
        return "admin";
    }
    @PostMapping("/admin-ver")
    public String verifyAdmin(HttpServletRequest request, HttpSession session){

      return adminService.verifyAdmin( request,session);

    }
}
