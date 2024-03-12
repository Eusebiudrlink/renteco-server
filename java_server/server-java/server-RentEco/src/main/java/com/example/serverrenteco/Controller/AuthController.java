package com.example.serverrenteco.Controller;

import com.example.serverrenteco.JwtConfig;
import com.example.serverrenteco.Model.UserCredentials;
import com.example.serverrenteco.Service.UserService;
import com.example.serverrenteco.Model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtConfig jwtConfig;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User credentials) {
        System.out.println("Login");
        User user = userService.findByUsername(credentials.getUsername());
        System.out.println("Finded user: " + user.getUsername() + " " + user.getPassword() + " " + user.getId()  );
        if (user != null && user.getPassword().equals(credentials.getPassword())) {
            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getJwtExpiration() * 1000))
                    .signWith(SignatureAlgorithm.HS512, jwtConfig.getJwtSecret())
                    .compact();
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
