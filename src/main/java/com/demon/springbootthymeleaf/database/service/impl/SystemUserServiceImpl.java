package com.demon.springbootthymeleaf.database.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demon.springbootthymeleaf.database.dao.SystemUserMapper;
import com.demon.springbootthymeleaf.database.entity.SystemUser;
import com.demon.springbootthymeleaf.database.service.SystemUserService;
import com.demon.springbootthymeleaf.dto.UserDto;
import com.demon.springbootthymeleaf.util.EntityUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author demon
 * @since 2020-07-20
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

    @Override
    public Map<String, Object> getUserByUsername(String username) {
        QueryWrapper<SystemUser> systemUserQueryWrapper = new QueryWrapper<>();
        systemUserQueryWrapper.setEntity(new SystemUser().setUsername(username));
        SystemUser systemUser = this.baseMapper.selectOne(systemUserQueryWrapper);
        UserDto userDto = new UserDto();
        userDto.setUId(systemUser.getUId()).setUsername(systemUser.getUsername()).setPassword(systemUser.getPassword())
                .setNickname(systemUser.getNickname());
        return EntityUtil.entityToMap(userDto);
    }
}
