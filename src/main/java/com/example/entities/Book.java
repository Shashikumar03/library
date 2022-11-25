package com.example.entities;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;


    private int bookId;
    private String bookName;

    private String bookAuthor;

    private LocalDate DateOfIssue;

    private LocalDate DateOfSubmission;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public LocalDate getDateOfIssue() {
        return DateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        DateOfIssue = dateOfIssue;
    }

    public LocalDate getDateOfSubmission() {
        return DateOfSubmission;
    }

    public void setDateOfSubmission(LocalDate dateOfSubmission) {
        DateOfSubmission = dateOfSubmission;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book(int id, int bookId, String bookName, String bookAuthor, LocalDate dateOfIssue, LocalDate dateOfSubmission, Student student) {
        this.id = id;
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        DateOfIssue = dateOfIssue;
        DateOfSubmission = dateOfSubmission;
        this.student = student;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", DateOfIssue=" + DateOfIssue +
                ", DateOfSubmission=" + DateOfSubmission +
                ", student=" + student +
                '}';
    }
}
