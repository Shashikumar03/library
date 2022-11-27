package com.example.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;



    private int bookId;
    private String bookName;

    private String bookAuthor;

    private int bookYear;

    private LocalDate DateOfIssue;

    private LocalDate DateOfSubmission;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "books_roll")
    private Student books;

    public Student getBooks() {
        return books;
    }

    public void setBooks(Student books) {
        this.books = books;
    }


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

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookyear) {
        this.bookYear = bookyear;
    }


    public Book(int id, int bookId, String bookName, String bookAuthor, int bookYear, LocalDate dateOfIssue, LocalDate dateOfSubmission, Student student, Student books) {
        this.id = id;
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookYear = bookYear;
        DateOfIssue = dateOfIssue;
        DateOfSubmission = dateOfSubmission;
        this.student = student;
        this.books = books;
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
                ", bookyear=" + bookYear +
                ", DateOfIssue=" + DateOfIssue +
                ", DateOfSubmission=" + DateOfSubmission +
                ", student=" + student +
                ", books=" + books +
                '}';
    }
}
