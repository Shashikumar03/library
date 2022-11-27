package com.example.repository;

import com.example.entities.Book;
import com.example.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

public interface BookRepository extends CrudRepository<Book,Integer> {

    public List<Book> findByStudentRoll(BigInteger roll);

    public boolean existsByBookId(int id);

    public Book findByBookId(int id);

   // public Student findByBookId( int id);

//    @Query("select student from Student student join student.book books where books.")
//    public Student searchBy(String title);


}
