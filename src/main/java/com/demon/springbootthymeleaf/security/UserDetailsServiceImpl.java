package com.demon.springbootthymeleaf.security;

import com.demon.springbootthymeleaf.database.service.SystemMenuService;
import com.demon.springbootthymeleaf.database.service.SystemRoleService;
import com.demon.springbootthymeleaf.database.service.SystemUserService;
import com.demon.springbootthymeleaf.dto.MenuDto;
import com.demon.springbootthymeleaf.dto.RoleDto;
import com.demon.springbootthymeleaf.dto.UserDto;
import com.demon.springbootthymeleaf.util.EntityUtil;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserDetailServiceImpl
 * @Description:  自定义实现 UserDetailsService 接口，获取用户相关信息
 * @Author: Demon
 * @Date: 2020/6/1 21:46
 */
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SystemUserService systemUserService;
    @Resource
    private SystemRoleService systemRoleService;
    @Resource
    private SystemMenuService systemMenuService;

    @Override
    public UserDetailEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetailsService");
        // 根据用户名查找用户,以及用户权限 菜单
        try{
            Map<String, Object> map = systemUserService.getUserByUsername(username);
            UserDto userDto = EntityUtil.mapToEntity(map, UserDto.class);
            System.out.println(userDto);
            if (userDto != null && userDto.getUId() > 0) {
                // 根据用户id获取用户角色
                List<RoleDto> userRoles = systemRoleService.getUserRoleByUserId(String.valueOf(userDto.getUId()));
                // 根据角色获取用户菜单url
                List<MenuDto> roleMenus = systemMenuService.getMenusByRoles(userRoles);
                // 填充 用户 信息
                return new UserDetailEntity(userDto,userRoles,roleMenus);
            } else {
                System.out.println(username +" not found");
                throw new UsernameNotFoundException(username +" not found");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new UsernameNotFoundException(username +" not found");
        }
    }

}
