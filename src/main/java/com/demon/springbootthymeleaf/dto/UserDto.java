package com.demon.springbootthymeleaf.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName: UserDto
 * @Description: 用户 数据
 * @Author: Demon
 * @Date: 2020/6/8 19:11
 */
@Data
@Accessors(chain = true)
public class UserDto implements Serializable {

    private Integer uId;

    private String username;

    private String password;

    private String nickname;

}
