package com.demon.springbootthymeleaf.security;

import com.demon.springbootthymeleaf.dto.MenuDto;
import com.demon.springbootthymeleaf.dto.RoleDto;
import com.demon.springbootthymeleaf.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName: UserEntity
 * @Description: 自定义实现 UserDetails 接口，扩展属性
 * @Author: Demon
 * @Date: 2020/6/1 21:53
 */
public class UserDetailEntity implements UserDetails {

    private static final long serialVersionUID = -9005214545793249372L;

    /** 用户数据 */
    private UserDto userDto;
    /** 用户权限集合 */
    private List<RoleDto> userRoles;
    /** 角色菜单集合 */
    private List<MenuDto> roleMenus;

    private List<String> menuUrls;

    private Collection<? extends GrantedAuthority> authorities;

    UserDetailEntity(UserDto userDto, List<RoleDto> userRoles,List<MenuDto> roleMenus) {
        this.userDto = userDto;
        // 填充权限
        Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(roleDto-> authorities.add(new SimpleGrantedAuthority("ROLE_"+roleDto.getRolename())));
        this.authorities = authorities;
        this.userRoles = userRoles;
        this.roleMenus = roleMenus;
        //分装URL
        List<String> menuUrls = new ArrayList<>();
        roleMenus.forEach(roleMenu->{
            menuUrls.add(roleMenu.getMenuUrl());
            roleMenu.getMenuDtoList().forEach(menuDto -> menuUrls.add(menuDto.getMenuUrl()));
        });
        this.menuUrls = menuUrls;
    }

    public Integer getId() {
        return userDto.getUId();
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public List<RoleDto> getUserRoles() {
        return userRoles;
    }

    public List<MenuDto> getRoleMenus() {
        return roleMenus;
    }

    public List<String> getMenuUrls() {
        return menuUrls;
    }

    @Override
    public String getUsername() {
        return userDto.getUsername();
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
