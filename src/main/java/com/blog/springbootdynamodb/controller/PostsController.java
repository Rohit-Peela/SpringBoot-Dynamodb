package com.blog.springbootdynamodb.controller;

import com.blog.springbootdynamodb.VO.BaseVo;
import com.blog.springbootdynamodb.entity.Posts;
import com.blog.springbootdynamodb.entity.Signup;
import com.blog.springbootdynamodb.repository.PostsRepository;
import com.blog.springbootdynamodb.repository.SignupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@Slf4j
@CrossOrigin(origins = "*")
public class PostsController {

    @Autowired
    private PostsRepository postsRepository;

    @PostMapping("/save")
    public Posts saveUser(@RequestBody Posts posts) {
        log.info("New Posts Data is:{}", posts);

        return postsRepository.addPost(posts);
    }

    @GetMapping("/getAllPosts")
    public List<Posts> getAllPosts() {
        try {
            log.info("Entered getAllPosts method");

            return postsRepository.allPosts();
        } catch (Exception e) {
            log.error("Error occured while fetching all posts");
            return new ArrayList<Posts>();
        }

    }

    @PutMapping("/editPost")
    public ResponseEntity<BaseVo> editPost(@RequestBody Posts posts) {
        try {
            log.info("Edited Post is",posts);
            return new ResponseEntity<>(postsRepository.updatePost(posts), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occured while calling edit post api");
            return new ResponseEntity<BaseVo>(new BaseVo(400,"Unsuccessful in Editing Post"), HttpStatus.BAD_REQUEST);
        }

    }

}
