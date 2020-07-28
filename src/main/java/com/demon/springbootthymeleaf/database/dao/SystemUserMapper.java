package com.demon.springbootthymeleaf.database.dao;

import com.demon.springbootthymeleaf.database.entity.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author demon
 * @since 2020-07-20
 */
@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {

}
