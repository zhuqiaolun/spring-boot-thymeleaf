package com.demon.springbootthymeleaf.database.service.impl;

import com.demon.springbootthymeleaf.database.entity.SystemUserRoles;
import com.demon.springbootthymeleaf.database.dao.SystemUserRolesMapper;
import com.demon.springbootthymeleaf.database.service.SystemUserRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户权限表 服务实现类
 * </p>
 *
 * @author demon
 * @since 2020-07-20
 */
@Service
public class SystemUserRolesServiceImpl extends ServiceImpl<SystemUserRolesMapper, SystemUserRoles> implements SystemUserRolesService {

}
