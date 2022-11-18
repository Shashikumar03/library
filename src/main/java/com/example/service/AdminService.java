package com.example.service;

import com.example.entities.Admin;

public interface AdminService {


     public boolean MatchingAdminName(String adminName);
     public boolean MatchingAdminPassword(String password);

     public  void save( Admin admin);
}
