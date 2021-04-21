package com.blog.springbootdynamodb.entity;

import com.blog.springbootdynamodb.VO.BaseVo;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse extends BaseVo {
    private String token;
}
