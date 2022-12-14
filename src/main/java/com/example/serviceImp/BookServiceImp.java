package com.example.serviceImp;

import com.example.entities.Book;
import com.example.entities.Student;
import com.example.helper.Message;
import com.example.repository.BookRepository;
import com.example.repository.StudentRepository;
import com.example.service.BookService;
import com.example.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private EmailSenderService emailService;

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getBookByRoll(BigInteger roll) {
        return bookRepository.findByStudentRoll(roll);
    }

    @Override
    public boolean bookAlreadyIssued(int id) {
        return bookRepository.existsByBookId(id);
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.findByBookId(id);
    }

    @Override
    public String bookAssigned(BigInteger roll, HttpServletRequest request, Model model, HttpSession session) {
        Book book = new Book();
        Student student = studentRepository.search(roll);
        try {
            if (student == null) {
                throw new Exception("not register");
            }
            if (bookRepository.existsByBookId(Integer.parseInt(request.getParameter("bookId").trim()))) {

                Book bookAlreadyIssue = bookRepository.findByBookId(Integer.parseInt(request.getParameter("bookId")));
                if (bookAlreadyIssue.getDateOfSubmission() == null) {
                    Student student1 = bookAlreadyIssue.getStudent();
                    throw new Exception("This book is already issue to " + student1.getName());
                }
                if (bookAlreadyIssue.getDateOfSubmission() != null) {
//                    bookAlreadyIssue.setBookId(Integer.parseInt(request.getParameter("bookId").trim()));
                    bookAlreadyIssue.setBookAuthor(request.getParameter("bookAuthor"));
                    bookAlreadyIssue.setBookName(request.getParameter("bookName"));
                    bookAlreadyIssue.setDateOfIssue(LocalDate.now());
                    bookAlreadyIssue.setStudent(student);
                    bookAlreadyIssue.setDateOfSubmission(null);
                    bookRepository.save(bookAlreadyIssue);

                }
                if (student == null)
                    throw new Exception("invalid input");
            } else {
                book.setBookId(Integer.parseInt(request.getParameter("bookId").trim()));
                book.setBookAuthor(request.getParameter("bookAuthor"));
                book.setBookName(request.getParameter("bookName"));
                System.out.println("shashi");
                book.setDateOfIssue(LocalDate.now());
                if (Integer.parseInt(request.getParameter("bookYear")) > 8 ) {
                    throw new Exception("semester must be less than 9");
                } else if ( Integer.parseInt(request.getParameter("bookYear")) < 1) {
                    throw  new Exception("semester must be greater than 0");

                }else{
                    book.setBookYear(Integer.parseInt(request.getParameter("bookYear")));
                    book.setStudent(student);
                    bookRepository.save(book);
                }

            }
        } catch (Exception e) {
            session.setAttribute("message", new Message(" book  will not issue !! "
                    + e.getMessage(), "alter-danger"));
            return "redirect:/bookissue/{roll}";
        }
           emailService.sendSimpleEmail(student.getEmail(),"mail","mail");
           emailService.sendSimpleEmail(student.getEmail(),"This Book Id  "+request.getParameter("bookId")+","+request.getParameter("bookName")+System.lineSeparator()+" Author Name- "+request.getParameter("bookAuthor")+",   Issue On "+LocalDate.now(),"book issue");
//            emailService.sendSimpleEmail("nirajkushwaha8195@gmail.com",
//                  request.getParameter("name")+" "+request.getParameter("phone"),"sawari");


        model.addAttribute("student", student);
        List<Book> books = bookRepository.findByStudentRoll(roll);
        model.addAttribute("book", books);
        session.setAttribute("message", new Message("successfull issue,Do issue another book and see your register email for notification", "alert-success"));
        return "redirect:/bookissue/{roll}";

    }

    @Override
    public String submitBook(int bookId, Model model, HttpServletRequest request, HttpSession session) {
        Book book = bookRepository.findByBookId(bookId);
        book.setDateOfSubmission(LocalDate.now());
        Student student = book.getStudent();
        BigInteger roll = student.getRoll();
        bookRepository.save(book);
        emailService.sendSimpleEmail("shashikumarkushwaha3@gmail.com","hii","email");
        emailService.sendSimpleEmail(student.getEmail(),"This Book id - "+book.getBookId()+",book Name-"+book.getBookName()+System.lineSeparator()+" Author Name- "+book.getBookAuthor()+",   Submitted on- "+LocalDate.now(),"book submitted");
        model.addAttribute("student", student);
        List<Book> books = bookRepository.findByStudentRoll(roll);
        model.addAttribute("bookId", bookId);
        model.addAttribute("roll", roll);
        return "submitted";
    }

    @Override
    public String bookIssue(BigInteger roll, Model model) {
        Student student = studentRepository.search(roll);
        model.addAttribute("student", student);
        return "bookissue";
    }
}
