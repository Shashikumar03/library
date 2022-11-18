package com.example.repository;

import com.example.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {


    public Student findByName(String name);

    public boolean existsByName(String Name);

    public boolean existsByPhoneNumber(String number);

//    public boolean existsByEmail(String email);

}
