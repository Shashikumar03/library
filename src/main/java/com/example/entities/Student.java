package com.example.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {


    @NotBlank(message = "name should not be blank")
    @Size(min = 3, max = 12, message = "name should be 3 to 12 character")
    private String name;

    private String email;

    @NotBlank(message = "password should not be blank")
    @Size(min = 3, max = 12, message = "password should be 3 to 12 character")
    private String password;

    @Id
    private BigInteger roll;

    @NotBlank(message = "Enter Valid Mobile Number")
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "books")
    private List<Book> book;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigInteger getRoll() {
        return roll;
    }

    public void setRoll(BigInteger roll) {
        this.roll = roll;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    public Student( String name, String email, String password, BigInteger roll, String phoneNumber, List<Book> book) {
//        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roll = roll;
        this.phoneNumber = phoneNumber;
        this.book = book;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +

                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roll=" + roll +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", book=" + book +
                '}';
    }
}
