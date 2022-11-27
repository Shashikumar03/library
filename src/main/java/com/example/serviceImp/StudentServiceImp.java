package com.example.serviceImp;

import com.example.entities.Student;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
 private    StudentRepository studentRepository;

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
}
