package com.example.shardingreadwrite.controller;

import com.example.shardingreadwrite.po.User;
import com.example.shardingreadwrite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Object list() {
        return userService.list();
    }

    @GetMapping("/add")
    public Object add() {
        User user = new User();
        user.setCity("深圳");
        user.setName("李四");
        return userService.add(user);
    }
}
