package com.example.repository;

import com.example.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface StudentRepository extends CrudRepository<Student, Integer> {


    public Student findByName(String name);

    public boolean existsByName(String Name);

    public boolean existsByPhoneNumber(String number);

     public boolean existsByEmail(String email);

    public boolean existsByPassword(String pwd);

    @Query(value = "select * from student where roll=?1", nativeQuery = true)
    public Student search(BigInteger roll);

}
