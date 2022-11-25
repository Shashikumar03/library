package com.example.controller;

import com.example.entities.Book;
import com.example.entities.Student;
import com.example.helper.Message;
import com.example.repository.BookRepository;
import com.example.service.AdminService;
import com.example.service.BookService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
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
import java.time.LocalDate;
import java.util.ArrayList;
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
           return "adminprofile";
       }

        return "redirect:/Admin-register";
    }

    @GetMapping("/user-login")
    public String userLogin(){
        return "userlogin";
    }

    @PostMapping("/user-ver")
    public String userVerification(HttpServletRequest request, Model model){

        if (studentService.matchingEmail(request.getParameter("userName")) && studentService.matchingPassword(request.getParameter("password"))) {
              model.addAttribute("userName",request.getParameter("userName"));
            return "studentprofile";

        }
        return "redirect:/user-login";
    }
    @PostMapping("/search")
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
    @GetMapping("/bookissue/{roll}")
    public String bookIssue(@PathVariable("roll") BigInteger roll, Model model){
        Student student=studentService.search(roll);
//         Book book= new Book();
//         book.setBookId(1456);
//         book.setBookAuthor("amit");
//         book.setBookName("python");
//         book.setDateOfIssue(LocalDate.now());
//         book.setStudent(student);
//         bookService.save(book);
//          model.addAttribute("roll", roll);
//        List<Book>books= bookService.getBookByRoll(roll);
//        model.addAttribute("book",books);
        model.addAttribute("student", student);
        System.out.println("shashi");

        return "bookissue";
    }
    @PostMapping("/bookassigned/{roll}")
    public String bookAssigned(@PathVariable("roll") BigInteger roll, HttpServletRequest request, Model model, HttpSession session){
        Book book= new Book();
        Student student= studentService.search(roll);
        book.setBookId(Integer.parseInt(request.getParameter("bookId").trim()));
        book.setBookAuthor(request.getParameter("bookAuthor"));
        book.setBookName(request.getParameter("bookName"));
        book.setDateOfIssue(LocalDate.now());
          book.setStudent(student);



        bookService.saveBook(book);



        model.addAttribute("student", student);
        List<Book>books= bookService.getBookByRoll(roll);
        model.addAttribute("book",books);
         return "studentdetails";
    }

    @GetMapping("/submit/{bookId}")
    public String submitBook( @PathVariable("bookId") int bookId, Model model){

      Book book= bookService.getBookById(bookId);
       // System.out.println(book);


        return "base";
    }
}
