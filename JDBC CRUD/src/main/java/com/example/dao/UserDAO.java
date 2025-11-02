package com.example.dao;

import com.example.model.User;

public class UserDAO {
    // Hardcoded login for simplicity
    public boolean validate(User user) {
        return "admin".equals(user.getUsername()) && "1234".equals(user.getPassword());
    }
}
