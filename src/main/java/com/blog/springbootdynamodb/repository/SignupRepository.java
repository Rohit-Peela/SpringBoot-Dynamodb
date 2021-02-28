package com.blog.springbootdynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.blog.springbootdynamodb.entity.Signup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class SignupRepository {

    @Autowired
    private DynamoDBMapper mapper;

    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();


    public Signup addUser(Signup signup) {
        mapper.save(signup);
        log.info("signup user name  is:",signup.getEmail());

        return signup;
    }

    public List<Signup> getAllUsers() {

        List<Signup> signupList = mapper.scan(Signup.class, scanExpression);

        log.info("The size of signUp list is:", signupList.size());

        return signupList;
    }

    public String deleteUser(Signup signup) {
        mapper.delete(signup);
        return "Delete Succesfull";
    }

    public Signup findById(String signupId) {
        return mapper.load(Signup.class, signupId);
    }

    public String updateUser(Signup signup) {

        mapper.save(signup,editUser(signup));

        return "User Value Edit Success";

    }

    private DynamoDBSaveExpression editUser(Signup signup) {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> map = new HashMap<>();
        map.put("signupId", new ExpectedAttributeValue(new AttributeValue().withS(signup.getSignupId())));
        dynamoDBSaveExpression.setExpected(map);

        return dynamoDBSaveExpression;
    }
}
