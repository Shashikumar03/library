package com.example.service;

import com.example.entities.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

public interface BookService {


    public void saveBook(Book book);

    public List<Book> getBookByRoll(BigInteger roll);

    public boolean bookAlreadyIssued(int id);

    public Book getBookById(int id);

  public   String bookAssigned(BigInteger roll, HttpServletRequest request, Model model, HttpSession session);

 public String submitBook(int bookId, Model model, HttpServletRequest request, HttpSession session);

    String bookIssue(BigInteger roll, Model model);
}
