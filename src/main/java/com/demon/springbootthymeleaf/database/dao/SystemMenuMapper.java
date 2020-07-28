package com.demon.springbootthymeleaf.database.dao;

import com.demon.springbootthymeleaf.database.entity.SystemMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author demon
 * @since 2020-07-23
 */
@Mapper
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {

}
