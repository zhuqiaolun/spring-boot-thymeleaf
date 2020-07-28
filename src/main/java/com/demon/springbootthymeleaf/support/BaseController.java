package com.demon.springbootthymeleaf.support;

import com.demon.springbootthymeleaf.dto.MenuDto;
import com.demon.springbootthymeleaf.dto.UserDto;
import com.demon.springbootthymeleaf.security.UserDetailEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * @ClassName: BaseController
 * @Description:
 * @Author: Demon
 * @Date: 2020/7/21 17:07
 */
@Slf4j
public abstract class BaseController {

    private UserDetailEntity getUserDetailEntity(){
        return (UserDetailEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    protected UserDto getUserDto(){
        return getUserDetailEntity().getUserDto();
    }

    protected List<MenuDto> getRoleMenus(){
        return getUserDetailEntity().getRoleMenus();
    }


}
