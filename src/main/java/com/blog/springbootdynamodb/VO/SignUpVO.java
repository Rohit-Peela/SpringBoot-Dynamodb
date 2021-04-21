package com.blog.springbootdynamodb.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignUpVO extends BaseVo {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String confirmPassword;
}
