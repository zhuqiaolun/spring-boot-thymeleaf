package com.demon.springbootthymeleaf.database.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限菜单表
 * </p>
 *
 * @author demon
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemRoleMenu extends Model<SystemRoleMenu> {

    private static final long serialVersionUID=1L;

    /**
     * 主键，自增长
     */
    @TableId(value = "rm_id", type = IdType.AUTO)
    private Integer rmId;

    /**
     * 角色表id
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 模块表id
     */
    @TableField("menu_id")
    private Integer menuId;


    @Override
    protected Serializable pkVal() {
        return this.rmId;
    }

}
