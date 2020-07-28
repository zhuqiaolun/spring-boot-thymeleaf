package com.demon.springbootthymeleaf.database.service;

import com.demon.springbootthymeleaf.database.entity.SystemUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author demon
 * @since 2020-07-20
 */
public interface SystemUserService extends IService<SystemUser> {

    /**
     * 根据用户名查找用户
     *
     * @param username appId
     * @return 返回
     */
    Map<String, Object> getUserByUsername(String username);


}
