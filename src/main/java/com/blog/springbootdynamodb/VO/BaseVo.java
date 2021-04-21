package com.blog.springbootdynamodb.VO;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseVo {
    private Integer code;
    private String message;
}
