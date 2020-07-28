package com.demon.springbootthymeleaf.database.service;

import com.demon.springbootthymeleaf.database.entity.SystemMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demon.springbootthymeleaf.dto.MenuDto;
import com.demon.springbootthymeleaf.dto.RoleDto;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author demon
 * @since 2020-07-23
 */
public interface SystemMenuService extends IService<SystemMenu> {

    /**
     * 根据角色获取用户菜单url
     * @param userRoles 用户角色
     * @return 返回
     */
    List<MenuDto> getMenusByRoles(List<RoleDto> userRoles);

}
