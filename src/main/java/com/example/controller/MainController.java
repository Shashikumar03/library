package com.example.controller;

import com.example.entities.Book;
import com.example.entities.Student;
import com.example.helper.Message;
import com.example.repository.BookRepository;
import com.example.service.AdminService;
import com.example.service.BookService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String homePage(Model model) {
//        model.addAttribute("student",new Student());
        return "base";
    }
    @GetMapping("/user-login")
    public String userLogin() {
        return "userlogin";
    }




}