package com.example.serviceImp;

import com.example.entities.Book;
import com.example.entities.Student;
import com.example.helper.Message;
import com.example.repository.BookRepository;
import com.example.repository.StudentRepository;
import com.example.service.EmailSenderService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
 private   StudentRepository studentRepository;
//    @Autowired
//    private EmailSenderService emailService;

    @Autowired
   private BookRepository bookRepository;

    @Override
    public Student getStudentByName(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    public boolean nameMatch(String name) {
        return studentRepository.existsByName(name);
    }

    @Override
    public boolean matchingEmail(String email) {

       return studentRepository.existsByEmail(email);
    }
    @Override
    public boolean matchingPassword(String pwd) {
        return studentRepository.existsByPassword(pwd);
    }

    @Override
    public boolean mobileExits(String phone) {
        return studentRepository.existsByPhoneNumber(phone);
    }

    @Override
    public void saveStudent( Student student) {
        studentRepository.save(student);

    }

    @Override
    public Student search(BigInteger roll) {
   return studentRepository.search(roll);
    }

    @Override
    public boolean emailMatch(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public String registrationForm(Student student, BindingResult result, Model model, HttpSession session) {
        try {
            BigInteger roll = student.getRoll();
            if(roll.toString().length()<10){
                model.addAttribute("student", student);
                throw new Exception(" write correct roll number");
            }
            if (studentRepository.existsByName(student.getName())) {
                model.addAttribute("student", student);
                throw new Exception(" Name-already-exits");
            }
            if (studentRepository.existsByEmail(student.getEmail())) {
                model.addAttribute("student", student);
                throw new Exception(" Email-already-exits");
            }
            if(studentRepository.existsByPhoneNumber(student.getPhoneNumber())){
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

        studentRepository.save(student);
//        model.addAttribute("student", new Student());

        session.setAttribute("message", new Message("successfull", "alert-success"));
        return "redirect:/";
    }

    @Override
    public String userVerification(HttpServletRequest request, Model model, HttpSession session) {
        try{
            if (studentRepository.existsByEmail(request.getParameter("userName")) && studentRepository.existsByPassword(request.getParameter("password"))) {

                Student student= studentRepository.findByEmail(request.getParameter("userName"));
                model.addAttribute("userName",student.getName());
                List<Book> bookByRoll= bookRepository.findByStudentRoll(student.getRoll());
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

    @Override
    public String searchStudentByRoll(HttpServletRequest request, Model model, HttpSession session) {
        try {
            BigInteger roll = new BigInteger(request.getParameter("search"));
            Student student=studentRepository.search(roll);
            if(student==null){
                throw new Exception("user not Register");
            }

            model.addAttribute("student", student);
            List<Book>books = bookRepository.findByStudentRoll(roll);
            model.addAttribute("book",books);
            return "studentdetails";

        }catch (Exception e){

            session.setAttribute("message", new Message(" Server error !! "
                    + e.getMessage(), "alter-danger"));
            return "adminprofile";
        }
    }

    @Override
    public String seeStudentDetails(BigInteger roll, Model model, HttpSession session, HttpServletRequest request) {
        try{
            Student student= studentRepository.search(roll);
            model.addAttribute("student", student);
            List<Book>books= bookRepository.findByStudentRoll(roll);
            model.addAttribute("book",books);
        }catch (Exception e){
            session.setAttribute("message", new Message(" Server error !! "
                    + e.getMessage(), "alter-danger"));
            return "adminprofile";
        }
        return "studentdetails";
    }

}
