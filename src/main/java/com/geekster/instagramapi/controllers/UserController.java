package com.geekster.instagramapi.controllers;

import com.geekster.instagramapi.dto.SignInInput;
import com.geekster.instagramapi.dto.SignInOutput;
import com.geekster.instagramapi.dto.SignUpInput;
import com.geekster.instagramapi.dto.SignUpOutput;
import com.geekster.instagramapi.models.Post;
import com.geekster.instagramapi.models.User;
import com.geekster.instagramapi.services.TokenService;
import com.geekster.instagramapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;


    @PostMapping(value = "/signup")
    public SignUpOutput signUpOutput(@RequestBody SignUpInput userSignUp){
        return userService.signUp(userSignUp);
    }

    @PostMapping(value = "/signin")
    public SignInOutput signInInput(@RequestBody SignInInput userSignIn){
        return  userService.signIn(userSignIn);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updateUserNameById(@RequestParam Long userId,@RequestParam Long userPhoneNumber,@RequestParam String token ){

        //authenticate
        HttpStatus status;
        String response = null;
        if(tokenService.authenticate(token)){
            userService.updateUserPhoneNumberById(userId,userPhoneNumber);
            response = "User's contact has been updated successfully !!";
            status = HttpStatus.OK;

        }else{
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<>(response,status);
    }

}
