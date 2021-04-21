package com.blog.springbootdynamodb.controller;

import com.blog.springbootdynamodb.VO.BaseVo;
import com.blog.springbootdynamodb.entity.AuthRequest;
import com.blog.springbootdynamodb.entity.AuthResponse;
import com.blog.springbootdynamodb.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String Init() {

        return "Hello World";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<BaseVo> generateToken(@RequestBody AuthRequest authRequest) throws Exception {

        AuthResponse authResponse = new AuthResponse();
        try {
            log.info("The username entered is:{}",authRequest.getUsername());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            log.error("Invalid Credentials Entered");
            populateAuthResponse(authResponse,"");
            return new ResponseEntity<BaseVo>(authResponse, HttpStatus.UNAUTHORIZED);
        }
        log.info("Authentication Success");
        populateAuthResponse(authResponse, jwtUtil.generateToken(authRequest.getUsername()));
        return new ResponseEntity<BaseVo>(authResponse, HttpStatus.OK);

    }

    private void populateAuthResponse(AuthResponse authResponse, String s) {
        if(s.length() == 0) {
            authResponse.setToken("");
            authResponse.setCode(401);
            authResponse.setMessage("Invalid Credentials");
        } else {
            authResponse.setToken(s);
            authResponse.setCode(200);
            authResponse.setMessage("Success");
        }
    }
}
