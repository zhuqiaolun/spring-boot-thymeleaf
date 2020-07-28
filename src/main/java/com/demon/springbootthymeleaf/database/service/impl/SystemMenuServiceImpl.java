package com.demon.springbootthymeleaf.database.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demon.springbootthymeleaf.database.entity.SystemMenu;
import com.demon.springbootthymeleaf.database.dao.SystemMenuMapper;
import com.demon.springbootthymeleaf.database.entity.SystemRoleMenu;
import com.demon.springbootthymeleaf.database.service.SystemMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demon.springbootthymeleaf.database.service.SystemRoleMenuService;
import com.demon.springbootthymeleaf.dto.MenuDto;
import com.demon.springbootthymeleaf.dto.RoleDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author demon
 * @since 2020-07-23
 */
@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements SystemMenuService {

    @Resource
    private SystemRoleMenuService systemRoleMenuService;

    @Override
    public List<MenuDto> getMenusByRoles(List<RoleDto> userRoles) {
        List<MenuDto> menuDtoList = new ArrayList<>();
        if (userRoles != null && userRoles.size() > 0) {
            List<Integer> roleList = new ArrayList<>();
            for (RoleDto roleDto : userRoles) {
                roleList.add(roleDto.getRId());
            }
            QueryWrapper<SystemRoleMenu> systemRoleMenuQueryWrapper = new QueryWrapper<>();
            systemRoleMenuQueryWrapper.in("role_id", roleList);
            List<SystemRoleMenu> systemRoleMenuList = systemRoleMenuService.list(systemRoleMenuQueryWrapper);
            if (systemRoleMenuList != null && systemRoleMenuList.size() > 0) {
                List<Integer> systemRoleMenus = new ArrayList<>();
                for (SystemRoleMenu systemRoleMenu : systemRoleMenuList) {
                    systemRoleMenus.add(systemRoleMenu.getMenuId());
                }
                QueryWrapper<SystemMenu> systemMenuQueryWrapper = new QueryWrapper<>();
                systemMenuQueryWrapper.in("m_id", systemRoleMenus);
                systemMenuQueryWrapper.eq("menu_enable",1);
                systemMenuQueryWrapper
                        .orderByAsc("menu_pid").orderByAsc("menu_sort").orderByAsc("menu_createtime");
                List<SystemMenu> systemMenus = this.list(systemMenuQueryWrapper);

                systemMenus.forEach(systemMenu ->{
                    MenuDto menuDto = new MenuDto()
                            .setMId(systemMenu.getMId())
                            .setMenuName(systemMenu.getMenuName())
                            .setMenuUrl(systemMenu.getMenuUrl())
                            .setMenuType(systemMenu.getMenuType())
                            .setMenuPid(systemMenu.getMenuPid())
                            .setMenuSort(systemMenu.getMenuSort())
                            .setMenuEnable(systemMenu.getMenuEnable())
                            .setMenuDtoList(new ArrayList<>())
                    ;
                    if (systemMenu.getMenuPid() == 0) {
                        menuDtoList.add(menuDto);
                    } else {
                        if (menuDtoList.size() > 0) {
                            menuDtoList.forEach(menuDto1 -> {
                                if (String.valueOf(menuDto1.getMId()).equals(String.valueOf(menuDto.getMenuPid()))) {
                                    menuDto1.getMenuDtoList().add(menuDto);
                                }
                            });
                        }
                    }
                });
            }
        }
        return menuDtoList;
    }
}
