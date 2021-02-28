package com.blog.springbootdynamodb.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "login")
public class Login implements Serializable {
    @DynamoDBHashKey(attributeName = "loginId")
    @DynamoDBAutoGeneratedKey
    private String loginId;
    @DynamoDBAttribute
    private String email;
    @DynamoDBAttribute
    private String password;

}
