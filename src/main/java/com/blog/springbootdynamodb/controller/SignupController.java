package com.blog.springbootdynamodb.controller;


import com.amazonaws.services.dynamodbv2.xspec.S;
import com.blog.springbootdynamodb.entity.Signup;
import com.blog.springbootdynamodb.repository.SignupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @PostMapping("/save")
    public Signup saveUser(@RequestBody Signup signup) {
        log.info("New User Data is:", signup);

        return signupRepository.addUser(signup);
    }

    @GetMapping("/getAllUsers")
    public List<Signup> getAll() {

        return signupRepository.getAllUsers();
    }

    @PostMapping("/deleteUser")
    public String deleteByUser(@RequestBody Signup signup) {

        return signupRepository.deleteUser(signup);
    }

    @GetMapping("/findId/{signupId}")
    public Signup findById(@PathVariable String signupId) {
        return signupRepository.findById(signupId);
    }

    @PutMapping("/editUser")
    public String editUser(@RequestBody Signup signup) {
        return signupRepository.updateUser(signup);
    }


}
