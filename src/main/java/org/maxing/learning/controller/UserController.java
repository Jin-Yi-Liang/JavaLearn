package org.maxing.learning.controller;

import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("checkUsername")
    public boolean checkUsername(@RequestParam("username")String username){
        return userService.checkUsername(username);
    }

    @GetMapping("query")
    public JdbcStudent query(@RequestParam("id")Long id){
        return userService.findStudentById(id);
    }

    @GetMapping("queryAll")
    public List<JdbcStudent> fidAllStudent(@RequestHeader("cookie")String cookie){
        return userService.findAllStudent(cookie);
    }

    @PostMapping("addStudent")
    public int addStudent(@RequestBody JdbcStudent student){
        return userService.addStudent(student);
    }

    @GetMapping("listRequestHeader")
    public void listRequestHeader(@RequestHeader Map<String,String>headers){
        userService.listRequestHeader(headers);
    }
}
