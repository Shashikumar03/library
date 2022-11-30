package com.example.service;

import com.example.entities.Student;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;

public interface StudentService {

    public Student getStudentByName(String name);

    public boolean nameMatch(String name);

    public boolean matchingEmail(String email);
    public boolean matchingPassword(String pwd);

    public boolean mobileExits(String phone);

    public void saveStudent(Student student);

    public Student search(BigInteger roll);


    boolean emailMatch(String email);

    public Student getStudentByEmail(String email);

    String registrationForm(Student student, BindingResult result, Model model, HttpSession session);

    String userVerification(HttpServletRequest request, Model model, HttpSession session);

    String searchStudentByRoll(HttpServletRequest request, Model model, HttpSession session);

    String seeStudentDetails(BigInteger roll, Model model, HttpSession session, HttpServletRequest request);
}
