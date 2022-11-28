package com.example.service;

import com.example.entities.Student;

import java.math.BigInteger;

public interface StudentService {

    public Student getStudentByName(String name);

    public boolean nameMatch(String name);

    public boolean matchingEmail(String email);
    public boolean matchingPassword(String pwd);

    public  boolean mobileExits(String phone);

    public void saveStudent(Student student);

    public Student search(BigInteger roll);


    boolean emailMatch(String email);

    public Student getStudentByEmail(String email);
}
