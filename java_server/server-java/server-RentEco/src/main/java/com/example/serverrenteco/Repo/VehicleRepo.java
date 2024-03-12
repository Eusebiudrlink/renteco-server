package com.example.serverrenteco.Repo;

import com.example.serverrenteco.Model.AutoVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<AutoVehicle, Long> {

     List<AutoVehicle> findAll();

}
