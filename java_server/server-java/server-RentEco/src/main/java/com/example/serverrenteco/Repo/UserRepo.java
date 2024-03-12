package com.example.serverrenteco.Repo;


import com.example.serverrenteco.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    User findByUsername(String username);

}
