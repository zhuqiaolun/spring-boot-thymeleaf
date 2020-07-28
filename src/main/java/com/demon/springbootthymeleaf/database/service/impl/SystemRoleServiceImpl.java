package com.demon.springbootthymeleaf.database.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demon.springbootthymeleaf.database.dao.SystemRoleMapper;
import com.demon.springbootthymeleaf.database.entity.SystemRole;
import com.demon.springbootthymeleaf.database.entity.SystemUserRoles;
import com.demon.springbootthymeleaf.database.service.SystemRoleService;
import com.demon.springbootthymeleaf.database.service.SystemUserRolesService;
import com.demon.springbootthymeleaf.dto.RoleDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author demon
 * @since 2020-07-20
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements SystemRoleService {

    @Resource
    private SystemUserRolesService systemUserRolesService;

    @Override
    public List<RoleDto> getUserRoleByUserId(String userId) {
        List<RoleDto> roleDtoList = new ArrayList<>();
        QueryWrapper<SystemUserRoles> systemUserRolesQueryWrapper = new QueryWrapper<>();
        systemUserRolesQueryWrapper.setEntity(new SystemUserRoles().setUserId(userId));
        List<SystemUserRoles> systemUserRoles = systemUserRolesService.list(systemUserRolesQueryWrapper);
        if (systemUserRoles != null && systemUserRoles.size() > 0) {
            List<Integer> userRoleList = new ArrayList<>();
            for (SystemUserRoles userRoles : systemUserRoles) {
                userRoleList.add(userRoles.getRoleId());
            }
            QueryWrapper<SystemRole> systemRoleQueryWrapper = new QueryWrapper<>();
            systemRoleQueryWrapper.in("r_id", userRoleList);
            List<SystemRole> systemRoles = this.list(systemRoleQueryWrapper);
            systemRoles.forEach(systemRole -> roleDtoList.add(new RoleDto().setRId(systemRole.getRId()).setRolename(systemRole.getRoleName())));
        }
        return roleDtoList;
    }
}
