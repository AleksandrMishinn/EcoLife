package com.ecolife.ecolife.jwt.service.impl;

import com.ecolife.ecolife.jwt.model.Role;
import com.ecolife.ecolife.jwt.model.Status;
import com.ecolife.ecolife.jwt.model.User;
import com.ecolife.ecolife.jwt.repository.RoleRepository;
import com.ecolife.ecolife.jwt.repository.UserRepository;
import com.ecolife.ecolife.jwt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {

        user.setStatus(Status.ACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_USER"));

        User registeredUser = userRepository.save(user);

        log.info("Successfully register user: " + registeredUser);

        return registeredUser;

    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);

        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }
}
