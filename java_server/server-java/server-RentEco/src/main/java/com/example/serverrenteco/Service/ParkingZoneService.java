package com.example.serverrenteco.Service;

import com.example.serverrenteco.GPSUtils;
import com.example.serverrenteco.Model.ParkingZone;
import com.example.serverrenteco.Model.Ride;
import com.example.serverrenteco.Repo.ParkingZoneRepo;
import com.example.serverrenteco.Repo.RideRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingZoneService {
    @Autowired
    private ParkingZoneRepo parkingZoneRepo;;

    public ParkingZone findParkingZone(ParkingZone vehicleParkingZone) {
        List<ParkingZone> parkingZones= parkingZoneRepo.findAll();
        double radius = 0.1; //100 metri
        for(ParkingZone parkingZone1:parkingZones){
            Boolean isWithinPerimeter = GPSUtils.isWithinRadius(
                    parkingZone1.getLatitude(),parkingZone1.getLongitude(),
                    vehicleParkingZone.getLatitude(), vehicleParkingZone.getLongitude(), radius
            );
            if(isWithinPerimeter) {
                System.out.println("Vehicle is within parking zone");
                return parkingZone1;
            }
        }
        System.out.println("Vehicle is not within parking zone");
        return vehicleParkingZone;
    }
}
