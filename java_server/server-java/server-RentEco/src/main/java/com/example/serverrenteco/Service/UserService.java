package com.example.serverrenteco.Service;

import com.example.serverrenteco.Model.User;
import com.example.serverrenteco.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;;
    public void save(User user) {
    }

    public User findByUsername(String username) {
       return userRepo.findByUsername(username);
    }
}
