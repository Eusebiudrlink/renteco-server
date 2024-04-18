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
        userRepo.save(user);
    }

    public User findByEmail(String username) {
        try{
            return userRepo.findByEmail(username);
        } catch (Exception e) {
            System.out.println("User not found  ");
            return null;
        }
    }
}
