package org.maxing.learning.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public boolean checkUsername(String username){
        return "admin".equals(username);
    }
}
