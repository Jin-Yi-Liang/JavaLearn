package org.maxing.learning.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class CheckUsernameController {
    @PostMapping("/checkUsername")
    public boolean checkUsername(@RequestParam("username")String username){
        return "admin".equals(username);
    }
}
