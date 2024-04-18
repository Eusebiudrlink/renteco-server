package com.example.serverrenteco.Controller;


import com.example.serverrenteco.JwtConfig;
import com.example.serverrenteco.Model.AutoVehicle;
import com.example.serverrenteco.Model.Ride;
import com.example.serverrenteco.Model.User;
import com.example.serverrenteco.Service.RideService;
import com.example.serverrenteco.Service.UserService;
import com.example.serverrenteco.Validators.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @Autowired
    private TokenValidator tokenValidator;

    @PostMapping("/")
    public ResponseEntity<?> addRide(@RequestHeader("Authorization") String authorization,@RequestBody Ride ride) {
        String jwtToken = authorization.substring(7);
        if(tokenValidator.validateToken(jwtToken)==true) {
            System.out.println("Token is valid for Ride");
            rideService.save(ride);
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        }
        else{
            System.out.println("Token is invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<Ride>> findAll(@RequestHeader("Authorization") String authorization) {
        System.out.println("Get all rides");
        String jwtToken = authorization.substring(7);
        if(tokenValidator.validateToken(jwtToken)==true){
            System.out.println("Token is valid");
            List<Ride> rides = rideService.findAll();
            System.out.println(rides);
            return ResponseEntity.ok(rides);
        }
        else{
            System.out.println("Token is invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
