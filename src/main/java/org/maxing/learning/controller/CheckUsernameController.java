package org.maxing.learning.controller;

import org.maxing.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class CheckUsernameController {
    private final UserService userService;

    @Autowired
    public CheckUsernameController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/checkUsername")
    public boolean checkUsername(@RequestParam("username")String username){
        return userService.checkUsername(username);
    }
}
