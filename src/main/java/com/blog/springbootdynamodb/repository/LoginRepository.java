package com.blog.springbootdynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.blog.springbootdynamodb.entity.Login;
import com.blog.springbootdynamodb.entity.Signup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class LoginRepository {

    @Autowired
    private DynamoDBMapper mapper;

    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

    public Boolean checkIfUserExists(Login login) {

        List<Signup> signupList = mapper.scan(Signup.class, scanExpression);

        for (Signup signup : signupList) {
            if (signup.getEmail().equals(login.getEmail()) && signup.getPassword().equals(login.getPassword())) {
                log.info("One record found");
                return true;
            }
        }

        return false;
    }
}
