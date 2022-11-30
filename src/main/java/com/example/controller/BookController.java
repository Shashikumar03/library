package com.example.controller;

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

@Controller
public class BookController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/bookissue/{roll}")
    public String bookIssue(@PathVariable("roll") BigInteger roll, Model model){
        return bookService.bookIssue(roll, model);

    }
    @PostMapping("/bookassigned/{roll}")
    public String bookAssigned(@PathVariable("roll") BigInteger roll, HttpServletRequest request, Model model, HttpSession session){
        return bookService.bookAssigned(roll, request, model, session);
    }
    @GetMapping("/submit/{bookId}")
    public String submitBook( @PathVariable("bookId") int bookId, Model model,HttpServletRequest request, HttpSession session) {
        return bookService.submitBook(bookId, model, request, session);
    }
}
