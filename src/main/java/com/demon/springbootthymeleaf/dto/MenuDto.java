package com.demon.springbootthymeleaf.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: MenuDto
 * @Description: 菜单 数据
 * @Author: Demon
 * @Date: 2020/7/20 11:26
 */
@Data
@Accessors(chain = true)
public class MenuDto implements Serializable {

    private Integer mId;
    private String menuName;
    private String menuUrl;
    private Integer menuType;
    private Integer menuPid;
    private Integer menuSort;
    private Boolean menuEnable;
    List<MenuDto> menuDtoList;

}
