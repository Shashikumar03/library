package com.example.controller;

import com.example.entities.Admin;
import com.example.entities.Student;
import com.example.helper.Message;
import com.example.service.AdminService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.http.HttpRequest;

@Controller
public class MainController {

    @Autowired
   private StudentService studentService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String homePage(Model model){
//        model.addAttribute("student",new Student());
        return "base";
    }

    @GetMapping("/new-user")
    public String newUser(Model model){
        model.addAttribute("student", new Student());
        return "register";
    }
    @PostMapping("/registered")
    public String registrationForm(@Valid @ModelAttribute("student") Student student, BindingResult result,
    Model model, HttpSession session) {

        try {
            if (studentService.nameMatch(student.getName())) {
                model.addAttribute("student", student);
                System.out.println("hhh");
                throw new Exception(" Name-already-exits");
            }
//            if (studentService.emailMatch(student.getEmail())) {
//                model.addAttribute("student", student);
//                throw new Exception(" Email-already-exits");
//            }
            if(studentService.mobileExits(student.getPhoneNumber())){
                model.addAttribute("student", student);
                throw new Exception("mobile already Exists");
            }
            if (result.hasErrors()) {
                model.addAttribute("student", student);
                return "register";
            }




        } catch (Exception e) {
            model.addAttribute("student", student);
            session.setAttribute("message", new Message(" Server error !! "
                    + e.getMessage(), "alter-danger"));
            return "register";
        }
        studentService.saveStudent(student);
//        model.addAttribute("student", new Student());
        session.setAttribute("message", new Message("successfull", "alert-success"));
        return "redirect:/";
    }

    @GetMapping("/Admin-register")
    public String adminPage(){
//        Admin a = new Admin();
//        a.setAdminName("Vikash");
//        a.setAdminPassword("Shashi@123");
//        adminService.save(a);
//        System.out.println("succesfull");
        return "admin";
    }
    @PostMapping("/admin-ver")
    public String verifyAdmin(HttpServletRequest request){

       String adminName= request.getParameter("userName");
       String password=request.getParameter("password");
       if(adminService.MatchingAdminName(adminName) && adminService.MatchingAdminPassword(password)){
           System.out.println("succesfull");
           return "check";
       }

        return "redirect:/Admin-register";
    }
}
