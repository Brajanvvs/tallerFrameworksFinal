package com.example.demo.service;

public interface AuthService {
    String authenticate(String email, String password);
    String getUserName(String email);
}
