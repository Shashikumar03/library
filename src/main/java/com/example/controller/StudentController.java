package com.example.controller;

import com.example.entities.Student;
import com.example.repository.BookRepository;
import com.example.service.AdminService;
import com.example.service.BookService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigInteger;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/new-user")
    public String newUser(Model model){
        model.addAttribute("student", new Student());
        return "register";
    }
    @GetMapping("/search")
    public String search(HttpServletRequest request, Model model, HttpSession session){

        return studentService.searchStudentByRoll(request, model, session);


    }
    @PostMapping("/registered")
    public String registrationForm(@Valid @ModelAttribute("student") Student student, BindingResult result,
                                   Model model, HttpSession session) {
        return studentService.registrationForm(student, result, model, session);

    }
    @PostMapping("/user-ver")
    public String userVerification(HttpServletRequest request, Model model, HttpSession session) {
        return studentService.userVerification(request, model, session);

    }
    @GetMapping("/details/{roll}")
    public String seeDetails(@PathVariable("roll") BigInteger roll, Model model, HttpSession session, HttpServletRequest request) {

        return studentService.seeStudentDetails(roll, model, session, request);


    }
}
