package com.example.controller;

import com.example.helper.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @GetMapping("/Admin-register")
    public String adminPage(){
        return "admin";
    }
    @PostMapping("/admin-ver")
    public String verifyAdmin(HttpServletRequest request, HttpSession session){

        String adminName= request.getParameter("userName");
        String password=request.getParameter("password");
        try{
            if(adminName.equals("Shashi") && password.equals("Shashi@123")){
                return "adminprofile";
            }
            else{
                throw  new Exception("invalid password");
            }
        }catch(Exception e){
            session.setAttribute("message", new Message(" invalid credentials !! "
                    + e.getMessage(), "alter-danger"));
            return "redirect:/Admin-register";
        }

    }
}
