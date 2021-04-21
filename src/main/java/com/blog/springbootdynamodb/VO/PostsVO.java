package com.blog.springbootdynamodb.VO;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostsVO extends BaseVo {

    private String heading;

    private String email;

    private String content;

    private String postedOn;

    private List<String> tags;
}
