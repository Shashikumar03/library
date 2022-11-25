package com.example.service;

import com.example.entities.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

public interface BookService {


    public void saveBook(Book book);

    public List<Book> getBookByRoll(BigInteger roll);

    public boolean bookAlreadyIssued(int id);

    public Book getBookById(int id);

}
