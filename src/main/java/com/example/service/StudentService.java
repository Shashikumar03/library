package com.example.service;

import com.example.entities.Student;

public interface StudentService {

    public Student getStudentByName(String name);

    public boolean nameMatch(String name);


    public  boolean mobileExits(String phone);

    public void saveStudent(Student student);




}
