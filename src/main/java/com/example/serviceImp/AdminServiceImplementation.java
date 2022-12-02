package com.example.serviceImp;

import com.example.entities.Admin;
import com.example.entities.Student;
import com.example.helper.Message;
import com.example.repository.AdminRepository;
import com.example.repository.StudentRepository;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;

@Service
public class AdminServiceImplementation  implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    StudentRepository studentRepository;
    @Override
    public boolean MatchingAdminName(String adminName) {
        return adminRepository.existsByAdminName(adminName);
    }

    @Override
    public boolean MatchingAdminPassword(String password) {

        return adminRepository.existsByAdminPassword(password);
    }

    @Override
    public void save(Admin admin) {
    adminRepository.save(admin);
    }

    @Override
    public String verifyAdmin(HttpServletRequest request, HttpSession session) {

        try{
            String adminName= request.getParameter("userName");
            String password=request.getParameter("password");
            if(adminRepository.existsByAdminName(adminName) && adminRepository.existsByAdminPassword(password)){
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

    @Override
    public String reset(HttpServletRequest request, HttpSession session) {

        try{
            BigInteger roll= new BigInteger(request.getParameter("roll"));
            Student student = studentRepository.findByRoll(roll);
            if(student==null){
                throw  new Exception("student not register");
            }
            student.setPassword(request.getParameter("password"));

        }catch(Exception e){
            session.setAttribute("message", new Message(" invalid credentials !! "
                    + e.getMessage(), "alter-danger"));

            return "redirect:/reset";
        }
        session.setAttribute("message", new Message("successfull changed", "alert-success"));
        return "adminprofile";
    }
}
