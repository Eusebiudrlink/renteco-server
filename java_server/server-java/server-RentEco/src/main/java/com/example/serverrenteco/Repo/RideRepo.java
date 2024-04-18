package com.example.serverrenteco.Repo;

import com.example.serverrenteco.Model.Ride;
import com.example.serverrenteco.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepo extends JpaRepository<Ride, Long> {
}
