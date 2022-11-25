package com.example.repository;

import com.example.entities.Book;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

public interface BookRepository extends CrudRepository<Book,Integer> {

    public List<Book> findByStudentRoll(BigInteger roll);

    public boolean existsByBookId(int id);

    public Book findByBookId(int id);
}
