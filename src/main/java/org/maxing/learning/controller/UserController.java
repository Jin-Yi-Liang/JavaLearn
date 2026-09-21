package org.maxing.learning.controller;

import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/checkUsername")
    public boolean checkUsername(@RequestParam("username")String username){
        return userService.checkUsername(username);
    }

    @GetMapping("/query")
    public JdbcStudent findStudentById(@RequestParam("id") Long id){
        return userService.findStudentById(id);
    }
}
