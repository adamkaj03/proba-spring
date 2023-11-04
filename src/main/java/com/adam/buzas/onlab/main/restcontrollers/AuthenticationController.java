package com.adam.buzas.onlab.main.restcontrollers;

import com.adam.buzas.onlab.main.config.AuthenticationRequest;
import com.adam.buzas.onlab.main.config.AuthenticationResponse;
import com.adam.buzas.onlab.main.config.RegisterRequest;
import com.adam.buzas.onlab.main.repository.UserRepository;
import com.adam.buzas.onlab.main.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterRequest request){
        if(userRepository.findByEmail(request.getEmail()).isEmpty()){
            if(userRepository.findByUsername(request.getUsername()).isEmpty()){
                return ResponseEntity.ok(authenticationService.register(request));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This username is already exist!");
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This email is already exist!");
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate (@RequestBody AuthenticationRequest request){
        if(!userRepository.findByUsername(request.getUsername()).isEmpty()){
            return ResponseEntity.ok(authenticationService.authenticate(request));
        }
        else{
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no user with this username!");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody String token){
        authenticationService.logout(token);
        return ResponseEntity.ok("Logout was succesful!");
    }
}
