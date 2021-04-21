package com.blog.springbootdynamodb.repository;

import ch.qos.logback.classic.db.names.TableName;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.xspec.B;
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.blog.springbootdynamodb.VO.BaseVo;
import com.blog.springbootdynamodb.VO.SignUpVO;
import com.blog.springbootdynamodb.entity.Signup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class SignupRepository implements UserDetailsService {

    @Autowired
    private DynamoDBMapper mapper;

    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();


    public BaseVo addUser(Signup signup) throws Exception {
        try {
            mapper.save(signup);
            log.info("signup user name  is:",signup.getEmail());
            SignUpVO signUpVO = new SignUpVO();
            signUpVO.setFirstName(signup.getFirstName());
            signUpVO.setLastName(signup.getLastName());
            signUpVO.setEmail(signup.getEmail());
            signUpVO.setPassword(signup.getPassword());
            signUpVO.setConfirmPassword(signup.getConfirmPassword());
            signUpVO.setCode(200);
            signUpVO.setMessage("Succesfully Added The User");

            return signUpVO;
        } catch (Exception e) {
            log.error("Error occured while adding the User");
            throw new Exception("Error Occured while calling addUser function in SignUpRepository "+e.getMessage());
        }
    }

    public List<Signup> getAllUsers() {

        List<Signup> signupList = mapper.scan(Signup.class, scanExpression);

        log.info("The size of signUp list is:{}", signupList.size());

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("The entered email is:{}",email);
        List<Signup> signupList = mapper.scan(Signup.class, scanExpression);
        for(Signup signup: signupList) {
            if(signup.getEmail().equals(email)) {
                log.info("Username found");
                return new org.springframework.security.core.userdetails.User(signup.getEmail(), signup.getPassword(), new ArrayList<>());
            }
        }
       throw new UsernameNotFoundException("No User Found");
    }

    public BaseVo findByUserName(String email) {
//        try {
//            Signup signup = new Signup();
//            signup.setEmail(email);
//
//            DynamoDBQueryExpression<Signup> queryExpression =
//                    new DynamoDBQueryExpression<Signup>()
//                    .withHashKeyValues(signup)
//                    .withIndexName("email");
//            List<Signup> queryList = mapper.query(Signup.class, queryExpression);
//
//            log.info("The Queryed result is {}",queryList);
//
//
//            return new BaseVo();

//        } catch (Exception e) {
//            log.error("Error occured while getting details the User by username");
//            return new BaseVo(400, "UserName Not Found!");
//        }

        List<Signup> signupList = mapper.scan(Signup.class, scanExpression);
        log.info("Signup list is: {}",signupList);

        for(Signup signup: signupList) {
            if(signup.getEmail().equals(email)) {
                log.info("The firstName is {}",signup.getFirstName());
                log.info("the last name is {}", signup.getLastName());
                SignUpVO signup1 = new SignUpVO();
                signup1.setFirstName(signup.getFirstName());
                signup1.setLastName(signup.getLastName());
                signup1.setCode(200);
                signup1.setMessage("Success");

                return signup1;
            }
        }


        throw new IllegalArgumentException("No username found");


    }
}
