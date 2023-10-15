package com.vishnu.demo.controller;



import com.vishnu.demo.model.Post;
import com.vishnu.demo.model.User;
import com.vishnu.demo.service.AuthenticationService;
import com.vishnu.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("user/signup")
    public String userSignUp(@Valid @RequestBody User newUser)
    {

        return userService.userSignUp(newUser);
    }



    //sign in
    @PostMapping("user/signIn/{email}/{password}")
    public String userSignIn(@PathVariable String email,@PathVariable String password)
    {
        return userService.userSignIn(email,password);
    }


    @PutMapping("user")
    public String udateUser(@RequestParam String email,@RequestParam String tokenValue, @RequestBody User user) throws NoSuchAlgorithmException {
        return userService.updateUser(email,tokenValue,user);
    }



    @PostMapping("InstaPost")
    public String createInstaPost(@RequestParam String email,@RequestParam String tokenValue, @RequestBody Post instaPost)
    {
        return userService.createInstaPost(email,tokenValue,instaPost);
    }

    @GetMapping("InstaPost")
    public List<Post> getInstaPosts(@RequestParam String email, @RequestParam String tokenValue)
    {
        return userService.getInstaPosts(email,tokenValue);
    }


















}


