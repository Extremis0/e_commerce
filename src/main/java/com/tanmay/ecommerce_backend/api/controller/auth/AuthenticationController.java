package com.tanmay.ecommerce_backend.api.controller.auth;

import com.tanmay.ecommerce_backend.Exception.UserAlreadyExistsException;
import com.tanmay.ecommerce_backend.Service.UserService;
import com.tanmay.ecommerce_backend.api.model.RegistrationBody;
import com.tanmay.ecommerce_backend.model.DAO.LocalUserDAO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser( @Valid @RequestBody RegistrationBody registrationBody){
        try {
            userService.registeruser(registrationBody);
            return  ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
