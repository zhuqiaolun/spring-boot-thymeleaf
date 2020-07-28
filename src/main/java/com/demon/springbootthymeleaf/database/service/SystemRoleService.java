package com.demon.springbootthymeleaf.database.service;

import com.demon.springbootthymeleaf.database.entity.SystemRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demon.springbootthymeleaf.dto.RoleDto;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author demon
 * @since 2020-07-20
 */
public interface SystemRoleService extends IService<SystemRole> {

    /**
     * 根据用户id获取用户角色
     *
     * @param userId userId
     * @return 返回
     */
    List<RoleDto> getUserRoleByUserId(String userId);

}
