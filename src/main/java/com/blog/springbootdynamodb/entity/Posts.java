package com.blog.springbootdynamodb.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "posts")
public class Posts {

    @DynamoDBHashKey(attributeName = "postId")
    @DynamoDBAutoGeneratedKey
    private String postId;
    @DynamoDBAttribute
    private String heading;
    @DynamoDBAttribute
    private String email;
    @DynamoDBAttribute
    private String content;
    @DynamoDBAttribute
    private String postedOn;
    @DynamoDBAttribute
    private List<String> tags;
}