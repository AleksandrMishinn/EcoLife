package com.ecolife.ecolife.jwt.service;

import com.ecolife.ecolife.jwt.model.User;

import java.util.List;

public interface UserService {

    User register (User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void deleteById(Long id);
}
