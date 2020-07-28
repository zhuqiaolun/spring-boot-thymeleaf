package com.demon.springbootthymeleaf.database.dao;

import com.demon.springbootthymeleaf.database.entity.SystemRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author demon
 * @since 2020-07-20
 */
@Mapper
public interface SystemRoleMapper extends BaseMapper<SystemRole> {

}
