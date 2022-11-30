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
import java.time.LocalDate;
import java.util.List;

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
        Student student=studentService.search(roll);
        model.addAttribute("student", student);
        return "bookissue";
    }
    @PostMapping("/bookassigned/{roll}")
    public String bookAssigned(@PathVariable("roll") BigInteger roll, HttpServletRequest request, Model model, HttpSession session){
        Book book = new Book();
        Student student = studentService.search(roll);
        try {

            if (bookService.bookAlreadyIssued(Integer.parseInt(request.getParameter("bookId").trim())) ) {

                Book bookAlreadyIssue= bookService.getBookById(Integer.parseInt(request.getParameter("bookId")));
                if(bookAlreadyIssue.getDateOfSubmission()==null){
                    Student student1 =bookAlreadyIssue.getStudent();
                    throw new Exception("This book is already issue to "+ student1.getName());
                }
                if(bookAlreadyIssue.getDateOfSubmission()!=null){
//                    bookAlreadyIssue.setBookId(Integer.parseInt(request.getParameter("bookId").trim()));
                    bookAlreadyIssue.setBookAuthor(request.getParameter("bookAuthor"));
                    bookAlreadyIssue.setBookName(request.getParameter("bookName"));
                    bookAlreadyIssue.setDateOfIssue(LocalDate.now());
                    bookAlreadyIssue.setStudent(student);
                    bookAlreadyIssue.setDateOfSubmission(null);
                    bookService.saveBook(bookAlreadyIssue);

                }
                if(student==null)
                    throw  new Exception("invalid input");
            }
            else{
                book.setBookId(Integer.parseInt(request.getParameter("bookId").trim()));
                book.setBookAuthor(request.getParameter("bookAuthor"));
                book.setBookName(request.getParameter("bookName"));
                System.out.println("shashi");
                book.setDateOfIssue(LocalDate.now());
                if(Integer.parseInt(request.getParameter("bookYear"))>4 || Integer.parseInt(request.getParameter("bookYear"))<1){
                    throw  new Exception("year must be 1 or 2 or 3 or 4");
                }
                book.setBookYear(Integer.parseInt(request.getParameter("bookYear")));
                book.setStudent(student);
                bookService.saveBook(book);
            }
        } catch (Exception e) {
            session.setAttribute("message", new Message(" book  will not issue !! "
                    + e.getMessage(), "alter-danger"));
            return "redirect:/bookissue/{roll}";
        }
        model.addAttribute("student", student);
        List<Book> books= bookService.getBookByRoll(roll);
        model.addAttribute("book",books);
        session.setAttribute("message", new Message("successfull issue, Do u want to issue another book", "alert-success"));
        return "redirect:/bookissue/{roll}";

    }
    @GetMapping("/submit/{bookId}")
    public String submitBook( @PathVariable("bookId") int bookId, Model model,HttpServletRequest request, HttpSession session) {

        Book book = bookService.getBookById(bookId);
        book.setDateOfSubmission(LocalDate.now());
        Student student= book.getStudent();
        BigInteger roll=  student.getRoll();
        bookService.saveBook(book);
        model.addAttribute("student", student);
        List<Book>books= bookService.getBookByRoll(roll);
        model.addAttribute("bookId",bookId);
        model.addAttribute("roll",roll);
        return "submitted";
    }
}
