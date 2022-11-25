package com.example.serviceImp;

import com.example.entities.Book;
import com.example.repository.BookRepository;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class BookServiceImp  implements BookService {

    @Autowired
    BookRepository bookRepository;
    @Override
    public void saveBook(Book book) {
       bookRepository.save(book);
    }

    @Override
    public List<Book> getBookByRoll(BigInteger roll) {
        return bookRepository.findByStudentRoll(roll);
    }

    @Override
    public boolean bookAlreadyIssued(int id) {
         return bookRepository.existsByBookId(id);
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.findByBookId(id);
    }
}
