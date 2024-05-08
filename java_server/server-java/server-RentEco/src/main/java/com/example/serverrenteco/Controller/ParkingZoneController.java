package com.example.serverrenteco.Controller;

import com.example.serverrenteco.Model.ParkingZone;
import com.example.serverrenteco.Model.Ride;
import com.example.serverrenteco.Model.User;
import com.example.serverrenteco.Service.ParkingZoneService;
import com.example.serverrenteco.Service.RideService;
import com.example.serverrenteco.Validators.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parkingzone")
public class ParkingZoneController {

    @Autowired
    private ParkingZoneService parkingZoneService;

    @Autowired
    private TokenValidator tokenValidator;
    @PostMapping("/findLocation")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String authorization,@RequestBody ParkingZone parkingZone) {
        String jwtToken = authorization.substring(7);
        if(tokenValidator.validateToken(jwtToken)==true){
            System.out.println("Token is valid");

            ParkingZone parkingZoneUpdated = parkingZoneService.findParkingZone(parkingZone);
            return ResponseEntity.ok(parkingZoneUpdated);
        }
        else{
            System.out.println("Token is invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
