package com.blog.springbootdynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.blog.springbootdynamodb.VO.BaseVo;
import com.blog.springbootdynamodb.VO.PostsVO;
import com.blog.springbootdynamodb.entity.Posts;
import com.blog.springbootdynamodb.entity.Signup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class PostsRepository {

    @Autowired
    private DynamoDBMapper mapper;

    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

    public Posts addPost(Posts posts) {
        mapper.save(posts);

        return posts;
    }

    public List<Posts> allPosts() {

        List<Posts> postsList = mapper.scan(Posts.class, scanExpression);

        log.info("The size of signUp list is:{}", postsList.size());

        return postsList;
    }

    public BaseVo updatePost(Posts posts) throws Exception {
        try {
            mapper.save(posts,editPost(posts));
            return populateToPostsVo(posts);
        } catch (Exception e) {
            log.error("Error occured while updating the User");
            throw new Exception("Error Occured while calling updatePost function in PostsRepository "+e.getMessage());
        }
    }

    public DynamoDBSaveExpression editPost(Posts posts) throws Exception {
        try {
            DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
            Map<String, ExpectedAttributeValue> map = new HashMap<>();
            map.put("postId", new ExpectedAttributeValue(new AttributeValue().withS(posts.getPostId())));
            dynamoDBSaveExpression.setExpected(map);

            return dynamoDBSaveExpression;
        } catch (Exception e) {
            log.error("Error occured while editing the User");
            throw new Exception("Error Occured while calling editPost function in PostsRepository "+e.getMessage());
        }

    }

    private PostsVO populateToPostsVo(Posts posts) {
        PostsVO postsVO = new PostsVO();
        postsVO.setCode(200);
        postsVO.setMessage("Succesfully Edited the Post");
        postsVO.setHeading(posts.getHeading());
        postsVO.setContent(posts.getHeading());
        postsVO.setEmail(posts.getEmail());
        postsVO.setPostedOn(posts.getPostedOn());
        postsVO.setTags(posts.getTags());

        return postsVO;
    }
}
