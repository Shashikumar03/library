package com.example.service;

import com.example.entities.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface AdminService {


     public boolean MatchingAdminName(String adminName);
     public boolean MatchingAdminPassword(String password);

     public  void save( Admin admin);

    String verifyAdmin(HttpServletRequest request, HttpSession session);
}
