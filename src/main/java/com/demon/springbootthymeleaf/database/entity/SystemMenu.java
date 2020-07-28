package com.demon.springbootthymeleaf.database.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author demon
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemMenu extends Model<SystemMenu> {

    private static final long serialVersionUID=1L;

    /**
     * 主键，自增长
     */
    @TableId(value = "m_id", type = IdType.AUTO)
    private Integer mId;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 菜单url（Controller 请求路径）
     */
    @TableField("menu_url")
    private String menuUrl;

    /**
     * 1-模块，2-菜单，3-操作
     */
    @TableField("menu_type")
    private Integer menuType;

    /**
     * 父ID
     */
    @TableField("menu_pid")
    private Integer menuPid;

    /**
     * 备注
     */
    @TableField("menu_remark")
    private String menuRemark;

    /**
     * 创建时间
     */
    @TableField("menu_createtime")
    private Date menuCreatetime;

    /**
     * 排序
     */
    @TableField("menu_sort")
    private Integer menuSort;

    /**
     * 可用
     */
    @TableField("menu_enable")
    private Boolean menuEnable;


    @Override
    protected Serializable pkVal() {
        return this.mId;
    }

}
