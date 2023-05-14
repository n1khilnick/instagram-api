package com.geekster.instagramapi.controllers;

import com.geekster.instagramapi.models.Post;
import com.geekster.instagramapi.services.PostService;
import com.geekster.instagramapi.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    TokenService tokenService;


    @PostMapping("email/{userEmail}/token/{token}")
    public ResponseEntity<String> addPost(@PathVariable String userEmail, @PathVariable String token,@RequestBody Post post){
        //authenticate
        HttpStatus status;
        String response = null;
        if(tokenService.authenticateForPost(userEmail,token)){
            postService.savePost(post);
            response = "Your post has been uploaded successfully !!";
            status = HttpStatus.OK;

        }else{
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<>(response,status);

    }

    @GetMapping("/")
    public Iterable<Post>getAllPosts(){
        return postService.fetchAllPosts();
    }


}
