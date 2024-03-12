package com.example.serverrenteco.Service;

import com.example.serverrenteco.Model.AutoVehicle;
import com.example.serverrenteco.Repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class VehicleService {

    @Autowired
    VehicleRepo vehicleRepo;
    public List<AutoVehicle> findAllByUserId(String userId) {
        return null;
    }

    public void save(AutoVehicle vehicle) {
    }

    public List<AutoVehicle> findAll() {
    return vehicleRepo.findAll();
    }
}
