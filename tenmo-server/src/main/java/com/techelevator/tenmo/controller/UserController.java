package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private JdbcUserDao userDao;

    @GetMapping(value = "users")
    public List<User> getUsers() {
        return userDao.findAll();
    }

}
