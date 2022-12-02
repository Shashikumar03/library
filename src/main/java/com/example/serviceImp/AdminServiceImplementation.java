package com.example.serviceImp;

import com.example.entities.Admin;
import com.example.helper.Message;
import com.example.repository.AdminRepository;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class AdminServiceImplementation  implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
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
        String adminName= request.getParameter("userName");
        String password=request.getParameter("password");
        try{
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
}
