package org.maxing.learning.controller;

import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public List<JdbcStudent> fidAllStudent(){
        return userService.findAllStudent();
    }

    @PostMapping("addStudent")
    public int addStudent(@RequestBody JdbcStudent student){
        return userService.addStudent(student);
    }

    @GetMapping("listCookieAndCookieValue")
    public void listCookieAndCookieValue(
            @RequestHeader("cookie")String cookie,
            @CookieValue(value="JSESSIONID") String cookieValue,
            @SessionAttribute("username")String username,
            @SessionAttribute("userId")Integer id,
            @SessionAttribute("permission")Set<String> permissions
            ){
        userService.listCookieAndCookieValue(cookie,cookieValue,username,id,permissions);
    }

    @GetMapping("listRequestHeader")
    public void listRequestHeader(@RequestHeader Map<String,String>headers){
        userService.listRequestHeader(headers);
    }
}
