package com.blog.springbootdynamodb.controller;


import com.blog.springbootdynamodb.VO.BaseVo;
import com.blog.springbootdynamodb.entity.Signup;
import com.blog.springbootdynamodb.repository.SignupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @PostMapping("/save")
    public ResponseEntity<BaseVo> saveUser(@RequestBody Signup signup) {
        try {
            log.info("New User Data is:{}", signup);
            return new ResponseEntity<>(signupRepository.addUser(signup), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseVo>(new BaseVo(400, "Unsuccessful in Adding User"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllUsers")
    public List<Signup> getAll() {

        return signupRepository.getAllUsers();
    }

    @PostMapping("/deleteUser")
    public String deleteByUser(@RequestBody Signup signup) {

        return signupRepository.deleteUser(signup);
    }

    @GetMapping("/findByusername/{email}")
    public ResponseEntity<BaseVo> findByusername(@PathVariable String email) {
        try {
            log.info("find By username is:{}", email);
            return new ResponseEntity<>(signupRepository.findByUserName(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseVo>(new BaseVo(400, "UserName Not Found!"), HttpStatus.BAD_REQUEST);
        }
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
