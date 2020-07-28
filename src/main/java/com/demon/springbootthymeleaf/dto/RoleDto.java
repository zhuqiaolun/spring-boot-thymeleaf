package com.demon.springbootthymeleaf.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName: RoleDto
 * @Description: 角色 数据
 * @Author: Demon
 * @Date: 2020/7/20 11:25
 */
@Data
@Accessors(chain = true)
public class RoleDto implements Serializable {

    private Integer rId;

    /**
     * 角色名
     */
    private String rolename;

}
