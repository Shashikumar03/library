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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigInteger;
import java.time.LocalDate;
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
    public String homePage(Model model){
//        model.addAttribute("student",new Student());
        return "base";
    }






    @GetMapping("/user-login")
    public String userLogin(){
        return "userlogin";
    }

    @PostMapping("/user-ver")
    public String userVerification(HttpServletRequest request, Model model, HttpSession session){
        try{
            if (studentService.matchingEmail(request.getParameter("userName")) && studentService.matchingPassword(request.getParameter("password"))) {

                Student student= studentService.getStudentByEmail(request.getParameter("userName"));
                model.addAttribute("userName",student.getName());
                List<Book> bookByRoll = bookService.getBookByRoll(student.getRoll());
                model.addAttribute("student", student);
                model.addAttribute("book",bookByRoll);

                return "studentprofile";

            }
            else{
                throw  new Exception("invalid credendials");
            }
        }catch (Exception e){
            session.setAttribute("message", new Message("invalid credentials !! "
                    + e.getMessage(), "alter-danger"));
        }
        return "redirect:/user-login";
    }
    @GetMapping("/search")
    public String search(HttpServletRequest request, Model model, HttpSession session){

        try {
            BigInteger roll = new BigInteger(request.getParameter("search"));
            Student student=studentService.search(roll);
            if(student==null){
                throw new Exception("user not Register");
            }

            model.addAttribute("student", student);
            List<Book>books= bookService.getBookByRoll(roll);
            model.addAttribute("book",books);
            return "studentdetails";

        }catch (Exception e){

            session.setAttribute("message", new Message(" Server error !! "
                    + e.getMessage(), "alter-danger"));
            return "adminprofile";
        }

    }



    @GetMapping("/details/{roll}")
    public String seeDetails(@PathVariable("roll") BigInteger roll, Model model,HttpSession session, HttpServletRequest request){

        try{
            Student student= studentService.search(roll);
            model.addAttribute("student", student);
            List<Book>books= bookService.getBookByRoll(roll);
            model.addAttribute("book",books);
        }catch (Exception e){
            session.setAttribute("message", new Message(" Server error !! "
                    + e.getMessage(), "alter-danger"));
           return "adminprofile";
        }
       return "studentdetails";
    }


}
